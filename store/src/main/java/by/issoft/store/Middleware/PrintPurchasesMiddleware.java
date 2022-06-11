package by.issoft.store.Middleware;
import by.issoft.store.Helper.SQLHelper;
import by.issoft.store.Store;

public class PrintPurchasesMiddleware extends Middleware {

    Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public PrintPurchasesMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if (consoleCommand.toUpperCase().equals(CommandValues.PRINT_PURCHASES.toString())) {
//            store.printPurchaseCollection();
            sqlHelper.selectFromPuchaseTable();
            return false;
        }
        return checkNext(consoleCommand);
    }
}