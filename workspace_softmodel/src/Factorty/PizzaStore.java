package Factorty;

public class PizzaStore {
    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }
    public Pizza orderPizza(String type){
        Pizza pizza = simplePizzaFactory.createPizza(type);
        return pizza;
    }
}
