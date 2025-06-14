package ru.otus.java.http.server;

import ru.otus.java.http.server.app.ItemRepository;
import ru.otus.java.http.server.exceptions_handling.BadRequestException;
import ru.otus.java.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    Map<String, RequestProcessor> routes;
    private RequestProcessor defaultNotFoundProcessor;
    private RequestProcessor defaultStaticResourcesProcessor;

    public Dispatcher() {
        ItemRepository itemRepository = new ItemRepository();
        this.routes = new HashMap<>();
        this.routes.put("GET /hello", new HelloRequestProcessor());
        this.routes.put("GET /calc", new CalcRequestProcessor());
        this.routes.put("GET /items", new GetItemsRequestProcessor(itemRepository));
        this.routes.put("POST /items", new CreateItemRequestProcessor(itemRepository) );
        this.defaultNotFoundProcessor = new DefaultNotFoundRequestProcessor();
        this.defaultStaticResourcesProcessor = new DefaultStaticResourcesProcessor();
    }
    public void execute(HttpRequest request, OutputStream output) throws IOException {

        Path staticPath = Paths.get("./static", request.getUri().substring(1));
        if (Files.exists(staticPath) && Files.isRegularFile(staticPath)) {
            defaultStaticResourcesProcessor.execute(request, output);
            return;
        }
        if (!routes.containsKey(request.getRoutingKey())) {
            defaultNotFoundProcessor.execute(request, output);
            return;
        }
        try {
            routes.get(request.getRoutingKey()).execute(request, output);
        }
        catch (BadRequestException e) {
            String response = "HTTP/1.1 400 Bad Request\r\n" +
                    "Content-Type: text/htmL; charset=utf-8\r\n" +
                    "\r\n" +
            "<html><body><ch1>Bad Request</hl><p>" + e.getCode() + ": " + e.getDescription() + "</p</body></htmL>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e) {
            String response = "HTTP/1.1 500 Internal Server Error\r\n" +
                    "Content-Type: text/htmL; charset=utf-8\r\n" +
                    "\r\n" +
                    "<html><body><ch1><h1>500 Internal Server Error</h1><h2>Ой......Кажется что-то сломалось</h2></body></htmL>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}

