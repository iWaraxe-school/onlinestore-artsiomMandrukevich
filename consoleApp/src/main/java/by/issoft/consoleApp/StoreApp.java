package by.issoft.consoleApp;

import by.issoft.store.Helper.StoreHelper;
import by.issoft.store.Store;
import by.issoft.tools.sort.StoreComparator;

public class StoreApp {

    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper sh = new StoreHelper(store);
        StoreComparator storeComparator = new StoreComparator();

        sh.fillOutProductList();
        store.printProductFromCategory();

        store.printProduct(storeComparator.sortProduct(store.getAllProducts()));
        store.printProduct(storeComparator.top5ProductPrice(store.getAllProducts()));

    }
}
