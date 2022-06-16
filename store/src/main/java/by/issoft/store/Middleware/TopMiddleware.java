package by.issoft.store.Middleware;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class TopMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public TopMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.TOP.toString())){
            httpHelper.httpClientHitEndpoint("/get-top-products");
            return false;
        }
        return checkNext(consoleCommand);
    }

}
