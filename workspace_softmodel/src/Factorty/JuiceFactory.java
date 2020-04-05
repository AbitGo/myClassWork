package Factorty;

public class JuiceFactory {
    public Juice createJuice(String type){
        Juice result = null;
        if(type.equalsIgnoreCase("orange")){
            result = new OrangeJuice();
        }
        return result;
    }
}
