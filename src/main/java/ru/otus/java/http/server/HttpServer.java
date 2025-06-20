package ru.otus.java.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private int port;


    private Dispatcher dispatcher;
    public HttpServer(int port) {
        this.port = port;
        dispatcher = new Dispatcher();
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    byte[] buffer = new byte[8192];
                    int n = socket.getInputStream().read(buffer);
                    if (n < 1) {
                        return;
                    }
                    String rawRequest = new String(buffer, 0, n);
                    HttpRequest request = new HttpRequest(rawRequest);
                    request.info(true);
                    dispatcher.execute(request, socket.getOutputStream());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
