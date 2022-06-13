package by.issoft.store.Middleware;

import by.issoft.store.Helper.SQLHelper;
import by.issoft.store.Store;

public class TopMiddleware extends Middleware{

    Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public TopMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.TOP.toString())){
//            store.printTopProducts();
            sqlHelper.selectTop5FromProductTable();
            return false;
        }
        return checkNext(consoleCommand);
    }

}
