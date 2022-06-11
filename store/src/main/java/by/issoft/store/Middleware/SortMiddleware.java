package by.issoft.store.Middleware;

import by.issoft.store.Helper.SQLHelper;
import by.issoft.store.Store;

public class SortMiddleware extends Middleware{

    Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public SortMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.SORT.toString())){
//            store.printSortProducts();
            sqlHelper.selectSortFromProductTable();
            return false;
        }
        return checkNext(consoleCommand);
    }

}
