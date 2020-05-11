package Command;

import com.sun.org.apache.xpath.internal.operations.Or;

public class CommandTest {
    public static void main(String[] args) {
        Request request = new Request("test",3);
        BuyRequest buyRequest = new BuyRequest(request);
        SellRequest sellRequest = new SellRequest(request);

        CommandInvocation commandInvocation = new CommandInvocation();
        commandInvocation.takeOrder(buyRequest);
        commandInvocation.takeOrder(sellRequest);

        commandInvocation.placeOrders();
    }
}
