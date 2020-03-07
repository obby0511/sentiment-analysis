package obby0511.sentiment.analysis;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class Handler {
    private final LanguageService language = new LanguageServiceImpl();

    public Mono<ServerResponse> handle(ServerRequest request) {
        return ok().body(fromPublisher(request.formData().map(data ->
                language.analyzeSentiment(LanguageService.LanguageRequest.builder()
                        .text(data.getFirst("text"))
                        .encodingType(StandardCharsets.UTF_8)
                        .build()
                )), LanguageService.LanguageResponse.class))
                .switchIfEmpty(notFound().build());
    }
}
