package ru.otus.java.http.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String rawRequest;
    private String method;
    private String uri;
    private Map<String, String> parameters;
    private String body;
    public String getMethod() {
        return method;
    }
    public String getUri() {
        return uri;
    }
    public String getParameter(String key) {
        return parameters.get(key);
    }
    public HttpRequest(String rawRequest) {
        this.parameters = new HashMap<>();
        this.rawRequest = rawRequest;
        parse(rawRequest);
    }
    public String getBody() {
        return body;
    }
    public String getRoutingKey() {
        return method + " " + uri;
    }
    public boolean containsParameter(String key) {
        return parameters.containsKey(key);
    }

    public void info(boolean showRawRequest){
        if(showRawRequest){
            System.out.println(rawRequest);
        }
        System.out.println("METHOD:" + method);
        System.out.println("URI:" + uri);
        System.out.println("PARAMETERS:" + parameters);
    }
    private void parse(String rawRequest) {
        String[] parts = rawRequest.split(" ");
        this.method = parts[0];
        this.uri = parts[1];
        if (uri.contains("?")) {
            String[] elements = uri.split("[?]");
            uri = elements[0];
            String[] ketsValues = elements[1].split("[&]");
            for (String ketsValue : ketsValues) {
                String[] kets = ketsValue.split("[=]");
                parameters.put(kets[0], kets[1]);
            }

        }
        this.body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);

    }

}
