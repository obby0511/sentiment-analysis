package obby0511.sentiment.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

import static obby0511.sentiment.analysis.LanguageService.LanguageRequest;
import static obby0511.sentiment.analysis.LanguageService.LanguageResponse;

@RestController
@SpringBootApplication
public class Application {
    private final LanguageService language = new LanguageServiceImpl();

    @PostMapping
    public ResponseEntity<LanguageResponse> analyze(String text) {
        LanguageResponse res = language.analyzeSentiment(LanguageRequest.builder()
                .text(text)
                .encodingType(StandardCharsets.UTF_8)
                .build());

        return ResponseEntity.ok(res);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
