package ru.student.service_a_client.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImageClientService {

    private final WebClient webClient;

    public ImageClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getMetadata(String id) {
    return webClient.get()
            .uri("/images/{id}/metadata", id)
            .retrieve()
            .bodyToMono(String.class)
            .timeout(Duration.ofSeconds(2))
            .retry(2)
            .onErrorResume(e ->
                    Mono.just("CLIENT ERROR: failed to get metadata"));
    }

    public Flux<String> streamMetadata() {
    return webClient.get()
            .uri("/images/metadata/stream")
            .retrieve()
            .bodyToFlux(String.class);
    }
}
