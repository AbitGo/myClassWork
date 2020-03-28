package Decorator;

public class OrangeJuice extends CondimentDecorator {
    private Beverage beverage;
    public OrangeJuice(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return this.beverage.getDescription()+",橙汁";
    }
    public double cost(){
        return this.beverage.cost()+2.5;
    }
}
