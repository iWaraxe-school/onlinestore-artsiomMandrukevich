package by.issoft.store.MultiThreading;

import by.issoft.store.Helper.SQLHelper;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateOrderThread implements Runnable{

    static Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public CreateOrderThread(Store store) {
        CreateOrderThread.store = store;
    }

    @SneakyThrows
    @Override
    public void run() {
        int i = (int) (1 + Math.random()*29);
        log.info("New good will be added to the purchases in " + i + " seconds.");
        TimeUnit.SECONDS.sleep(i);
//        store.setPurchaseGoods();
        sqlHelper.insertRandomProductIntoPurchaseTable();
    }
}

