package ru.otus.java.http.server;

import ru.otus.java.http.server.app.ItemRepository;
import ru.otus.java.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    Map<String, RequestProcessor> routes;
    private RequestProcessor defaultNotFoundProcessor;

    public Dispatcher() {
        ItemRepository itemRepository = new ItemRepository();
        this.routes = new HashMap<>();
        this.routes.put("GET /hello", new HelloRequestProcessor());
        this.routes.put("GET /calc", new CalcRequestProcessor());
        this.routes.put("GET /items", new GetItemsRequestProcessor(itemRepository));
        this.routes.put("POST /items", new CreateItemRequestProcessor(itemRepository) );
        this.defaultNotFoundProcessor = new DefaultNotFoundRequestProcessor();
    }
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!routes.containsKey(request.getRoutingKey())) {
            defaultNotFoundProcessor.execute(request, output);
        }
        routes.get(request.getRoutingKey()).execute(request, output);

    }
}
