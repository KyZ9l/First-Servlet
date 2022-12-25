package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {

        try (var serverSocket = new ServerSocket(5555);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {

            String zaprosOtKlienta = inputStream.readUTF();
            while (!"stop".equals(zaprosOtKlienta))
            {

                System.out.println("Zapros ot Klienta = " + zaprosOtKlienta);
                var answer = scanner.nextLine();
                outputStream.writeUTF(answer);
                zaprosOtKlienta = inputStream.readUTF();

            }





        }


    }
}
