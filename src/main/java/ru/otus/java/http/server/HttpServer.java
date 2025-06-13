package ru.otus.java.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            ExecutorService executor = Executors.newFixedThreadPool(3);
            while (true){
                executor.execute(() -> {
                    try {
                        handleRequest(serverSocket.accept());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleRequest(Socket socket) {
        try (socket) {
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            if (n < 1) return;

            String rawRequest = new String(buffer, 0, n);
            HttpRequest request = new HttpRequest(rawRequest);
            request.info(true);
            dispatcher.execute(request, socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
