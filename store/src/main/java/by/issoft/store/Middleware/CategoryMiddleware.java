package by.issoft.store.Middleware;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class CategoryMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public CategoryMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.CATEGORY.toString())){
            httpHelper.httpClientHitEndpoint("/get-category");
            return false;
        }
        return checkNext(consoleCommand);
    }
}
