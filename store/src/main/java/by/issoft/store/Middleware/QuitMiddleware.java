package by.issoft.store.Middleware;

public class QuitMiddleware extends Middleware{

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.QUIT.toString())){
            System.out.println("!!!! EXIT !!!");
            return true;
        }
        return checkNext(consoleCommand);
    }

}
