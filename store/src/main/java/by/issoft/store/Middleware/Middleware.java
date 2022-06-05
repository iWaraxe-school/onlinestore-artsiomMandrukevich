package by.issoft.store.Middleware;

public abstract class Middleware {

    private Middleware next;

    public Middleware linkWith(Middleware next){
        this.next = next;
        return next;
    }

    public abstract boolean check (String consoleCommand);

    protected boolean checkNext(String consoleCommand){
        if(next == null){
            return true;
        }
        return next.check(consoleCommand);
    }
}
