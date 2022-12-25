package com.dmdev.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final ExecutorService pool;
    private final int PORT;
    private boolean stooped;

    public HttpServer(int PORT, int poolSize) {
        this.PORT = PORT;
        this.pool = Executors.newFixedThreadPool(poolSize);

    }

    public void setStooped(boolean stooped) {
        this.stooped = stooped;
    }

    public void run()
    {
        try {
            var server = new ServerSocket(PORT);

            while (!stooped) {
                var socket = server.accept();
                System.out.println("Socket accepted");
                pool.submit(()-> processSocket(socket));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  void processSocket(Socket socket) {
        try(socket;
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream())) {
            //step 1 handle request
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));
            //step 2 handle responce
            Thread.sleep(10000);
            var body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: test/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException e) {
            //TODE 10/24/22

            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
