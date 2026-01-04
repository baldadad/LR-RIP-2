package ru.student.service_a_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.student.service_a_client.service.ImageClientService;

@RestController
public class ClientController {

    private final ImageClientService service;

    public ClientController(ImageClientService service) {
        this.service = service;
    }

    @GetMapping("/client/images/{id}/metadata")
    public Mono<String> getMetadata(@PathVariable String id) {
        return service.getMetadata(id);
    }

    @GetMapping("/client/images/metadata/stream")
    public Flux<String> streamMetadata() {
    return service.streamMetadata();
}
}
