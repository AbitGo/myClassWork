package Decorator;

//咖啡
public class Coffee extends Beverage{

    public Coffee(){
        description = "咖啡";
    }
    @Override
    public double cost() {
        return 4.5;
    }
}
