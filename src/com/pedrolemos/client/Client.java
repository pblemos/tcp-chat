package com.pedrolemos.client;

import com.pedrolemos.Services;
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.start("localhost", 65002);
    }

    public void start(String serverIP, int serverPort) {
        try {
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to Server!");

            Services clientServices = new Services(socket);
            clientServices.start();
        } catch (IOException e) {
            System.err.println("Error connecting to Server.");
        }
    }
}
