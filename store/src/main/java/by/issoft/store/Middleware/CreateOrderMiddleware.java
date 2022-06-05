package by.issoft.store.Middleware;

import by.issoft.store.MultiThreading.CreateOrderThread;
import by.issoft.store.Store;

public class CreateOrderMiddleware extends Middleware{

    Store store;

    public CreateOrderMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.CREATE_ORDER.toString())){
            final CreateOrderThread createOrderThread = new CreateOrderThread(store);
            new Thread(createOrderThread).start();
            return false;
        }
        return checkNext(consoleCommand);
    }

}
