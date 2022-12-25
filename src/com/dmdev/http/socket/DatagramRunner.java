package com.dmdev.http.socket;

import org.postgresql.Driver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {
        var inetAddress = Inet4Address.getByName("localhost");
        try (var datagramSocket = new DatagramSocket()) {
            byte [] bytes = "Hello from UDP com.dmdev.http.client".getBytes();
            var packet = new DatagramPacket(bytes,bytes.length, inetAddress, 5555);
            datagramSocket.send(packet);
        }


    }
}
