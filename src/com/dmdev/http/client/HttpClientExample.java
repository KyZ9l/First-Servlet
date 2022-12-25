package com.dmdev.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        var httpClient = HttpClient.newBuilder().
                version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://google.com"))
                        .GET().build();
        var response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println();
        System.out.println(response.body());
        System.out.println("--------------------------------");
        System.out.println(response.headers());

    }
}
