package Factorty;

public abstract class Juice {
    String name;
    double price;
    String size;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "you order a:"+getName()+" size is:"+getSize()+" and price is: "+getPrice();
    }
}
