package Command;

public class Request {
    private String name;
    private int price;

    public Request(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void bug(){
        System.out.println("Request:buy"+this.name+" price:"+this.price);
    }
    public void sell(){
        System.out.println("Request:sell"+this.name+" price:"+this.price);
    }

}
