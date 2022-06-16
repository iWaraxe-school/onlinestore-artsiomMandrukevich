package by.issoft.store.HTTP;

import by.issoft.store.Helper.JSONHelper;
import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;
import org.json.JSONArray;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static by.issoft.store.HTTP.HTTPVariables.*;

public class HTTPHelper {

    private final String encodedAuth = Base64.getEncoder().encodeToString((USER + ":" + PASSWORD).getBytes(StandardCharsets.UTF_8));

    @SneakyThrows
    public Boolean checkTypeMethod(HttpExchange exchange, String typeMethod){
        JSONHelper jsonHelper = new JSONHelper();
        boolean isMethodCorrect = false;

        if (!typeMethod.equals(exchange.getRequestMethod())) {
            isMethodCorrect = true;

            String response = String.valueOf(jsonHelper.prepareJSONArrayRequestMessage("Must be " + typeMethod));
            exchange.getResponseHeaders().set("Allow", typeMethod);
            exchange.getResponseHeaders().set("Content-type", "application/json");
            exchange.sendResponseHeaders(405, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        return isMethodCorrect;
    }

    @SneakyThrows
    public void responseMethod(HttpExchange exchange, JSONArray jsonArray){

        String response = String.valueOf(jsonArray);
        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    @SneakyThrows
    public void httpClientHitEndpoint(String pathURL){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + PORT + pathURL))
                .header("Authorization", "Basic " + encodedAuth)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
