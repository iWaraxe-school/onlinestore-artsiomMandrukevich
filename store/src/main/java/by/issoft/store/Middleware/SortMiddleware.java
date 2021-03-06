package by.issoft.store.Middleware;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class SortMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public SortMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.SORT.toString())){
            httpHelper.httpClientHitEndpoint("/get-sort-products");
            return false;
        }
        return checkNext(consoleCommand);
    }

}
