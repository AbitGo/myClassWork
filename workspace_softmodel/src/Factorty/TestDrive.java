package Factorty;

public class TestDrive {

    public static void main(String[] args) {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        Pizza pizza = store.orderPizza("cheese");
        System.out.println("We ordered a " + pizza.getName() + "\n");
        System.out.println(pizza);

        pizza = store.orderPizza("test");
        System.out.println("We ordered a " + pizza.getName() + "\n");
        System.out.println(pizza);

        JuiceFactory juiceFactory = new JuiceFactory();
        JuiceStore juiceStore = new JuiceStore(juiceFactory);
        Juice orange = juiceStore.orderJuice("orange");
        System.out.println(orange.toString());
    }
}

