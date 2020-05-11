package Command;

import java.util.ArrayList;
import java.util.List;

//命令调用类
public class CommandInvocation {
    private List<Order> orderList = new ArrayList<>();
    public void takeOrder(Order order){
        this.orderList.add(order);
    }

    public void placeOrders(){
        for(Order order:orderList){
            order.execute();
        }
        orderList.clear();
    }
}
