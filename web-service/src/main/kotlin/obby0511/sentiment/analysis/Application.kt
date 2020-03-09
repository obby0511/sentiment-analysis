package obby0511.sentiment.analysis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class Application {

    @Bean
    fun route(handler: Handler) = router {
        accept(APPLICATION_JSON).nest {
            POST("/", handler::analyze)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
