package obby0511.sentiment.analysis

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class Handler {

    val language: LanguageService = DefaultLanguageService()

    fun analyze(request: ServerRequest): Mono<ServerResponse> {
        val scores = fromPublisher(request
                .bodyToMono(LanguageRequest::class.java)
                .map { text -> language.analyzeSentiment(text) },
                LanguageResponse::class.java)
        return ServerResponse.ok().body(scores)
    }
}

