package Decorator;

//牛奶
//装饰者
public class Milk extends CondimentDecorator{
    //导入被装饰者
    private Beverage beverage;
    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",牛奶";
    }

    public double cost(){
        return 1.0+beverage.cost();
    }

}
