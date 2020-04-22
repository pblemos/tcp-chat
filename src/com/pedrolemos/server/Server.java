package com.pedrolemos.server;

import com.pedrolemos.Services;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(65002);
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected! IP:" + socket.getInetAddress());

            Services serverServices = new Services(socket);
            serverServices.start();
        } catch (Exception e) {
            System.err.println("An Error Occurred Starting the Server.");
        }
    }
}
