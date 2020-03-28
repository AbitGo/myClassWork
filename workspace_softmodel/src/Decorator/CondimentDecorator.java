package Decorator;

//调味品装饰
public abstract class CondimentDecorator {
    //饮料
    Beverage beverage;
    public abstract String getDescription();
    public abstract double cost();
}
