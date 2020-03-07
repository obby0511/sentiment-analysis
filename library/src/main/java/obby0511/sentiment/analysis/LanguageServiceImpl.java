package obby0511.sentiment.analysis;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class LanguageServiceImpl implements LanguageService {
    private Map<String, Float> scores;

    public LanguageServiceImpl() {
        try {
            loadDictionary("/pn.csv.m3.120408.trim.csv");
        } catch (IOException e) {
            throw new LanguageServiceException(e.getMessage(), e);
        }
    }

    public LanguageServiceImpl(final String dictionary) {
        try {
            loadDictionary(dictionary);
        } catch (IOException e) {
            throw new LanguageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public LanguageResponse analyzeSentiment(final LanguageRequest request) {
        Tokenizer tokenizer = new Tokenizer.Builder().build();
        final double score = tokenizer.tokenize(request.getText()).parallelStream()
                .map(Token::getSurface)
                .mapToDouble(s -> scores.getOrDefault(s, 0f))
                .sum();

        return new LanguageResponse(score);
    }

    void loadDictionary(final String dictionary) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(dictionary);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            scores = br.lines().map(s -> s.split("\t"))
                    .filter(s -> s.length > 1)
                    .map(s -> {
                        final String word = s[0].trim();
                        final String sentiment = s[1].trim(); // [p|e|n]
                        final float score = sentiment.equals("p") ? 1
                                : sentiment.equals("n") ? -1
                                : 0f;
                        return new SimpleEntry<>(word, score);
                    })
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1));
        }
    }
}
