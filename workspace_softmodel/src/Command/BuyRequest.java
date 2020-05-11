package Command;

public class BuyRequest implements Order {
    private Request request;

    //构造方法
    public BuyRequest(Request request) {
        this.request = request;
    }

    @Override
    public void execute() {
        this.request.bug();
    }
}
