package com.pedrolemos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Services {
    Socket socket;
    Scanner scanner;

    public Services(Socket socket) {
        this.socket = socket;
        scanner = new Scanner(System.in);
    }

    public void start() {
        startInputService();
        startOutputService();
    }

    public void startOutputService() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (socket.isConnected()) {
                printWriter.println(scanner.nextLine());
                printWriter.flush();
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred.");
        }
    }

    public void startInputService() {
        new Thread(() -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (socket.isConnected()) {
                    String message = bufferedReader.readLine();
                    if (message == null || message.equals("-1")) {
                        System.out.println("Socket Closed...");
                        System.exit(-1);
                    }
                    System.out.println("Received: " + message);
                }
                socket.close();
            } catch (IOException e) {
                System.err.println("An Error Occurred Receiving Messages.");
            }
        }).start();
    }
}
