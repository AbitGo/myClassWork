package ObserverModel;

public class SimpleObserver implements Observer {
    private int value;
    private Subject subject;
    private String name;

    public SimpleObserver(Subject subject,String name) {
        this.name = name;
        this.subject = subject;
        //观察者向主体注册观察服务
        subject.registerObserver(this);
    }

    @Override
    public void update(int value) {
        this.value = value;
        receiveSubjectMessage();
    }

    public void receiveSubjectMessage(){
        //接收到主体发送过来的数据
        System.out.println("当前的观察者名字为"+this.name +" 当前收到主体的数据通知:"+this.value);
    }

    public void showMessage(){
        //进行打印即可
        System.out.println("当前的观察者名字为"+this.name +" 当前的Value为:"+this.value);
    }
}
