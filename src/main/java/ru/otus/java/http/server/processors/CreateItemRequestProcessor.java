package ru.otus.java.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.http.server.HttpRequest;
import ru.otus.java.http.server.app.Item;
import ru.otus.java.http.server.app.ItemRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateItemRequestProcessor implements RequestProcessor {
    private final ItemRepository itemRepository;
    public CreateItemRequestProcessor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {;
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);
        itemRepository.createItem(item);
        String json = gson.toJson(item);
        String response =
                "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: application/json\r\n" +
                        "\r\n" +
                        json;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
