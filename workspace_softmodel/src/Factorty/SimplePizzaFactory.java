package Factorty;

public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("test")) {
            pizza = new TestAdd();
        }
        return pizza;
    }
}
