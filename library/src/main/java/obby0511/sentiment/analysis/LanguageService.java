package obby0511.sentiment.analysis;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.nio.charset.Charset;

public interface LanguageService {

    LanguageResponse analyzeSentiment(LanguageRequest request);

    @Value
    @Builder
    class LanguageRequest {
        private String text;
        private Charset encodingType;
    }

    @Getter
    class LanguageResponse {
        private final float score;
        private final double magnitude;

        public LanguageResponse(double magnitude) {
            this.magnitude = magnitude;
            this.score = magnitude < 0 ? -1
                    : magnitude > 0 ? 1
                    : 0;
        }
    }
}
