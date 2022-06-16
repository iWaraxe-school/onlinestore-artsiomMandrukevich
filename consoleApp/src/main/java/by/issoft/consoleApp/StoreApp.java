package by.issoft.consoleApp;

import by.issoft.store.HTTP.HTTPServer;
import by.issoft.store.Helper.StoreHelper;
import by.issoft.store.Store;
import by.issoft.store.Helper.StoreInteraction;
import lombok.SneakyThrows;

public class StoreApp {
    @SneakyThrows
    public static void main(String[] args) {

        Store store = Store.getInstance();

        StoreHelper sh = new StoreHelper(store);
        StoreInteraction storeInteraction = new StoreInteraction(store);
        sh.fillOutProductList();

        HTTPServer httpServer = new HTTPServer();
        httpServer.startHttpServer();

        storeInteraction.ConsoleInteraction();
        httpServer.stopHTTPServer();

    }
}
