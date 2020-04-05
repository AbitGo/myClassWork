package Factorty;

public class JuiceStore {
    JuiceFactory juiceFactory;
    public JuiceStore(JuiceFactory juiceFactory) {
        this.juiceFactory =juiceFactory;
    }
    public Juice orderJuice(String type){
        return this.juiceFactory.createJuice(type);
    }
}
