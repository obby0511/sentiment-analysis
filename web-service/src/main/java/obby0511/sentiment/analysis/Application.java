package obby0511.sentiment.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringBootApplication
public class Application {

    @Bean
    public RouterFunction<ServerResponse> route(Handler handler) {
        return RouterFunctions.route(
                POST("/").and(accept(MediaType.TEXT_PLAIN)), handler::handle);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
