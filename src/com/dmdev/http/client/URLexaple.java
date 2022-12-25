package com.dmdev.http.client;

import java.io.IOException;
import java.net.URL;

public class URLexaple {
    public static void main(String[] args) throws IOException {

        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();


        System.out.println();

    }



    }

