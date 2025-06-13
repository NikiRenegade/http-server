package ru.otus.java.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.http.server.HttpRequest;
import ru.otus.java.http.server.app.Item;
import ru.otus.java.http.server.app.ItemRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetItemsRequestProcessor implements RequestProcessor {
    private final ItemRepository itemRepository;
    public GetItemsRequestProcessor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        List<Item> items = itemRepository.getAllItems();
        String json = gson.toJson(items);
        String response =
                "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: application/json\r\n" +
                        "\r\n" +
                        json;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }

}
