package ru.otus.java.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) {
        new HttpServer(8189).start();
    }
}
