package ru.otus.java.http.server.processors;

import ru.otus.java.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HelloRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response =
                "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n" +
                        "<html><body><h1>Hello World</h1><p>Hello</p><h2>HelLo World</h2></body></html›";;
        output.write(response.getBytes(StandardCharsets.UTF_8));

    }
}
