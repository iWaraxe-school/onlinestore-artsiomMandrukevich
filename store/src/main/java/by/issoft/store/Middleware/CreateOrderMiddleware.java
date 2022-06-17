package by.issoft.store.Middleware;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class CreateOrderMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public CreateOrderMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.CREATE_RANDOM_PURCHASE.toString())){
            httpHelper.httpClientHitEndpoint("/create-random-purchase");
            return false;
        }
        return checkNext(consoleCommand);
    }

}
