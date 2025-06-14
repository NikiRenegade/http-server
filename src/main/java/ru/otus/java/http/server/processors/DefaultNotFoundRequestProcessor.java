package ru.otus.java.http.server.processors;

import ru.otus.java.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
       String response = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>PAGE NOT FOUND!! !!!!!!!!!!!!!</h1> <img src='https://syhzhuelbxgnhopnwjgc.supabase.co/storage/v1/object/public/media/blog/404_page_cover.jpg' alt='404 Not Found'></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
