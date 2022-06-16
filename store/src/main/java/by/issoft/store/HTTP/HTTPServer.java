package by.issoft.store.HTTP;

import by.issoft.store.Helper.SQLHelper;
import com.sun.net.httpserver.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import static by.issoft.store.HTTP.HTTPVariables.*;

public class HTTPServer {

    HTTPHelper httpServerHelper = new HTTPHelper();
    SQLHelper sqlHelper = new SQLHelper();

    HttpServer server;

    public HTTPServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(PORT), 0);
    }

    BasicAuthenticator authenticator = new BasicAuthenticator("auth") {
        @Override
        public boolean checkCredentials(String username, String password) {
            return username.equals(USER) && password.equals(PASSWORD);
        }
    };



    @SneakyThrows
    public void startHttpServer(){

        final HttpContext getCategory = server.createContext("/get-category", new GetCategoryFromDB());
        final HttpContext getProducts = server.createContext("/get-products", new GetProductsFromCategory());
        final HttpContext getSortProducts = server.createContext("/get-sort-products", new GetSortProductsFromCategory());
        final HttpContext getTopProducts = server.createContext("/get-top-products", new GetTopProductsFromCategory());
        final HttpContext postPurchase = server.createContext("/post-purchase", new PostPurchase());
        final HttpContext postRandomPurchase = server.createContext("/create-random-purchase", new PostRandomPurchase());
        final HttpContext getPurchases = server.createContext("/get-purchases", new GetPurchases());
        final HttpContext clearPurchases = server.createContext("/clear-purchases", new ClearPurchases());

        getCategory.setAuthenticator(authenticator);
        getProducts.setAuthenticator(authenticator);
        getSortProducts.setAuthenticator(authenticator);
        getTopProducts.setAuthenticator(authenticator);
        postPurchase.setAuthenticator(authenticator);
        postRandomPurchase.setAuthenticator(authenticator);
        getPurchases.setAuthenticator(authenticator);
        clearPurchases.setAuthenticator(authenticator);

        server.start();

    }

    public void stopHTTPServer(){
        server.stop(0x1);
    }

    class GetCategoryFromDB implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.selectCategoryFromCategoryTable());
        }
    }

    class GetProductsFromCategory implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.selectProductsFromProductTable());
        }
    }

    class GetSortProductsFromCategory implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.selectSortFromProductTable());
        }
    }

    class GetTopProductsFromCategory implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.selectTop5FromProductTable());
        }
    }

    class PostPurchase implements HttpHandler {
        @SneakyThrows
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "POST"))
                httpServerHelper.responseMethod(exchange, sqlHelper.insertProductIntoPurchaseFromPostRequest(exchange));
        }
    }

    class PostRandomPurchase implements HttpHandler {
        @SneakyThrows
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.insertRandomProductIntoPurchaseTable());
        }
    }

    class GetPurchases implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.selectFromPuchaseTable());
        }
    }

    class ClearPurchases implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            if(!httpServerHelper.checkTypeMethod(exchange, "GET"))
                httpServerHelper.responseMethod(exchange, sqlHelper.deleteFromPurchaseTable());
        }
    }

}
