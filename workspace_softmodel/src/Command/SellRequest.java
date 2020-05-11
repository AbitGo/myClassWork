package Command;

public class SellRequest implements Order {
    private Request request;

    //构造方法
    public SellRequest(Request request) {
        this.request = request;
    }

    @Override
    public void execute() {
        this.request.sell();
    }
}

