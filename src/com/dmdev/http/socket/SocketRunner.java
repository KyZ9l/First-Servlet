package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {

        var inetAddress = Inet4Address.getByName("localhost");
        try (var socket = new Socket(inetAddress, 5555);
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var scanner = new Scanner(System.in)) {

            while (scanner.hasNextLine())
            {
                String zapros = scanner.nextLine();
                outputStream.writeUTF(zapros);
                System.out.println("Server get answer = " + inputStream.readUTF());
            }




        }


    }
}
