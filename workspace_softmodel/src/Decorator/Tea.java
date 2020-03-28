package Decorator;

//茶
public class Tea extends Beverage {

    public Tea(){
        this.description ="茶";
    }
    @Override
    public double cost() {
        return 4.0;
    }
}
