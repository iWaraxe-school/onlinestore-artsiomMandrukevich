package by.issoft.store.Middleware;
import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class PrintPurchasesMiddleware extends Middleware {

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public PrintPurchasesMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if (consoleCommand.toUpperCase().equals(CommandValues.PRINT_PURCHASES.toString())) {
            httpHelper.httpClientHitEndpoint("/get-purchases");
            return false;
        }
        return checkNext(consoleCommand);
    }
}