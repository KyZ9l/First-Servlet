package com.dmdev.http.DlyMiStypid;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

public class HTTPRunner {
    public static void main(String[] args) throws IOException, InterruptedException {

        var httpClient = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://javarush.ru/"))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(Path.of("resources","response.txt")));
        System.out.println(response.headers());
        System.out.println("----------------------");
        System.out.println(response.body());

    }
}
