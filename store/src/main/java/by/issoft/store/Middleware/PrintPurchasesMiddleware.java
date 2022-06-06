package by.issoft.store.Middleware;
import by.issoft.store.Store;

public class PrintPurchasesMiddleware extends Middleware {

    Store store;

    public PrintPurchasesMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if (consoleCommand.toUpperCase().equals(CommandValues.PRINT_PURCHASES.toString())) {
            store.printPurchaseCollection();
            return false;
        }
        return checkNext(consoleCommand);
    }
}