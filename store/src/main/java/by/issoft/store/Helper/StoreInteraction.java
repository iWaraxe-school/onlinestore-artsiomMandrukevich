package by.issoft.store.Helper;

import by.issoft.store.Middleware.*;
import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class StoreInteraction {

    static Store store;
    private static MiddlewareServer middlewareServer;

    public StoreInteraction(Store store) {
        StoreInteraction.store = store;
    }

    public String InputString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private static void initMiddleware() {
        middlewareServer = new MiddlewareServer();

        Middleware middleware = new SortMiddleware(store);
        middleware.linkWith(new TopMiddleware(store))
                .linkWith(new CreateOrderMiddleware(store))
                .linkWith(new PrintPurchasesMiddleware(store))
                .linkWith(new QuitMiddleware())
                .linkWith(new UnknownMiddleware());

        middlewareServer.setMiddleware(middleware);
    }

    @SneakyThrows
    public void ConsoleInteraction() {
        initMiddleware();
        boolean isQuit = false;

        while(!isQuit){
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.print("Enter command (sort, top, create_order, print_purchases, quit): ");
            isQuit = middlewareServer.processingMiddleware(InputString());
        }
    }

}
