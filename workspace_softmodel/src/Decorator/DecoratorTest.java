package Decorator;

public class DecoratorTest {
    public static void main(String[] args) {

        //咖啡+奶
        Beverage coffee = new Coffee();
        System.out.println("咖啡+奶");
        CondimentDecorator milk = new Milk(coffee);
        System.out.println("您点的是:"+milk.getDescription());
        System.out.println("价格为:"+milk.cost());

        //茶+奶
        Beverage tea = new Tea();
        System.out.println("茶+奶");
        CondimentDecorator milk1 = new Milk(tea);
        System.out.println("您点的是:"+milk1.getDescription());
        System.out.println("价格为:"+milk1.cost());

        //茶+橙汁
        System.out.println("茶+橙汁");
        CondimentDecorator orangeJuice = new OrangeJuice(tea);
        System.out.println("您点的是:"+orangeJuice.getDescription());
        System.out.println("价格为:"+orangeJuice.cost());
    }

}
