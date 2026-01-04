package ru.student.service_b_server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ImageController {

    @GetMapping("/images/{id}/metadata")
    public Mono<String> getMetadata(@PathVariable String id) {
        return Mono.fromCallable(() -> heavyMetadataProcessing(id))
                .onErrorResume(e ->
                        Mono.just("ERROR while processing image " + id + ": " + e.getMessage())
                );
    }

    private String heavyMetadataProcessing(String id) throws IOException, InterruptedException {

        // Загрузка в память
        File file = new File("pic.jpg");
        byte[] imageBytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(imageBytes);
        }

        // Тупняк на 0.2с.
        Thread.sleep(200);

        Map<String, String> exif = new HashMap<>();
        exif.put("camera", "Canon");
        exif.put("author", "Student");
        exif.put("width", "4000");
        exif.put("height", "3000");

        // Кусок антиоптимизации
        String serialized = "";
        for (int i = 0; i < 5; i++) {
            serialized = exif.toString();
            exif = new HashMap<>();
            serialized = serialized.replace("{", "").replace("}", "");
            for (String pair : serialized.split(",")) {
                String[] kv = pair.split("=");
                if (kv.length == 2) {
                    exif.put(kv[0].trim(), kv[1].trim());
                }
            }
        }

        return "Image " + id +
                " metadata=" + exif +
                " (image size=" + imageBytes.length + " bytes)";
    }

    // Реактивный стрим
    @GetMapping("/images/metadata/stream")
    public Flux<String> streamMetadata() {
        return Flux.just(
                "camera=nokia3310",
                "author=Ryan Gosling",
                "width=8800",
                "height=5553"
        ).delayElements(Duration.ofMillis(200));
    }
}
