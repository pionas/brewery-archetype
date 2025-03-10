package ${package}.infrastructure.rest.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.DefaultResponseErrorHandler;

@TestConfiguration
class RestTemplateConfiguration {

    @LocalServerPort
    int localServerPort;

    @Bean
    TestRestTemplate restTemplate() {
        return new TestRestTemplate(restTemplateBuilder());
    }

    public RestTemplateBuilder restTemplateBuilder() {
        final var rootUri = "http://localhost:" + localServerPort;
        return new RestTemplateBuilder()
                .errorHandler(new DefaultResponseErrorHandler())
                .rootUri(rootUri);
    }
}
