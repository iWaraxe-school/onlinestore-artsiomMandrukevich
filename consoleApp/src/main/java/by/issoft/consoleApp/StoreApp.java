package by.issoft.consoleApp;

import by.issoft.store.Helper.StoreHelper;
import by.issoft.store.MultiThreading.CleanUpThread;
import by.issoft.store.Store;
import by.issoft.store.Helper.StoreInteraction;
import lombok.SneakyThrows;

public class StoreApp {
    @SneakyThrows
    public static void main(String[] args) {

        Store store = Store.getInstance();

        StoreHelper sh = new StoreHelper(store);
        StoreInteraction storeInteraction = new StoreInteraction(store);

        sh.fillOutProductList();

        final CleanUpThread cleanUpThread = new CleanUpThread(store);
        new Thread(cleanUpThread).start();

        storeInteraction.ConsoleInteraction();
        cleanUpThread.finish();

    }
}
