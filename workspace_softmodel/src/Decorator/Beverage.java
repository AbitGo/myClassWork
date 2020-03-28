package Decorator;

//饮料
//被装饰者
public abstract class Beverage {
    String description = "饮料描述";

    public String getDescription() {
        return description;
    }

    //价格
    public abstract double cost();
}
