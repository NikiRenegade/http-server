package ru.otus.java.http.server.processors;

import ru.otus.java.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultStaticResourcesProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String filename = request.getUri() .substring(1);
        Path filePath = Paths.get ("static/", filename);
        String fileType = filename.substring ( filename.lastIndexOf(".") + 1);
        byte[] fileData = Files.readAllBytes(filePath);
        String contentDisposition = "";
        if (fileType. equals ("pdf")) {
            contentDisposition = "Content-Disposition: attachment: filename=" + filename + "\r\n";
        }
        String response = "HTTP/1.1 200 0K\r\n" +
                "Content-Length: " + fileData.length + "\r\n" +
                "\r\n";
        output.write(response.getBytes());
        output.write(fileData);
    }
}
