package by.issoft.store.Middleware;

import by.issoft.store.Store;

public class SortMiddleware extends Middleware{

    Store store;

    public SortMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.SORT.toString())){
            store.printSortProducts();
            return false;
        }
        return checkNext(consoleCommand);
    }

}
