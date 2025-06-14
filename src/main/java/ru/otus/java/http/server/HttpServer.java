package ru.otus.java.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    public static final Logger logger = LogManager.getLogger(HttpServer.class);
    public HttpServer(int port) {
        this.port = port;
        dispatcher = new Dispatcher(logger);
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("Server started");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    byte[] buffer = new byte[65536];
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
