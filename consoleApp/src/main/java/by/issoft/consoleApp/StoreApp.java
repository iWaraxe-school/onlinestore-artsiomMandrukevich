package by.issoft.consoleApp;

import by.issoft.store.Helper.StoreHelper;
import by.issoft.store.Store;
import by.issoft.store.Helper.StoreInteraction;
import java.io.IOException;


public class StoreApp {

    public static void main(String[] args) throws IOException {

        Store store = new Store();
        StoreHelper sh = new StoreHelper(store);
        StoreInteraction storeInteraction = new StoreInteraction(store);

        sh.fillOutProductList();
//        store.printProductFromCategory();

        storeInteraction.ConsoleInteraction();

    }
}
