package by.issoft.store.Helper;

import by.issoft.store.Middleware.*;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                .linkWith(new QuitMiddleware()).linkWith(new UnknownMiddleware());

        middlewareServer.setMiddleware(middleware);
    }

    public void ConsoleInteraction() throws IOException {
        initMiddleware();
        boolean isQuit = false;

        while(!isQuit){
            System.out.print("Enter command (sort, top, quit): ");
            isQuit = middlewareServer.processingMiddleware(InputString());
        }
    }

}
