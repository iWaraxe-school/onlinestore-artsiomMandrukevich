package by.issoft.store.MultiThreading;

import by.issoft.store.Helper.SQLHelper;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CleanUpThread implements Runnable{

    static Store store;
    public boolean needRun = true;
    SQLHelper sqlHelper = new SQLHelper();

    public CleanUpThread(Store store) {
        CleanUpThread.store = store;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (needRun){
            TimeUnit.MINUTES.sleep(2);
            log.info("The purchased table was clean up.");
//            store.cleanUpPurchasedCollection();
            sqlHelper.deleteFromPurchaseTable();
            sqlHelper.closeSQLConnection();
        }
    }

    public void finish(){
        log.info("The purchased table will be clean up very soon, and application will be stopped.");
        needRun = false;
    }
}

