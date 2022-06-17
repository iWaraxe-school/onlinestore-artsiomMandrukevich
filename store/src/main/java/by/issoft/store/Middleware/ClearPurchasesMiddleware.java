package by.issoft.store.Middleware;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class ClearPurchasesMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public ClearPurchasesMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.CLEAR_PURCHASES.toString())){
            httpHelper.httpClientHitEndpoint("/clear-purchases");
            return false;
        }
        return checkNext(consoleCommand);
    }
}