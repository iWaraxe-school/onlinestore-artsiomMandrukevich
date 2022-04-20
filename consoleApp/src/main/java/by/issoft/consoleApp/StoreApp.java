package by.issoft.consoleApp;

import by.issoft.store.Helper.StoreHelper;
import by.issoft.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper sh = new StoreHelper(store);

        sh.fillOutProductList();
        store.printProductFromCategory();

    }
}
