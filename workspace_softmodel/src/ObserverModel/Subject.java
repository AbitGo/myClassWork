package ObserverModel;

public interface Subject {
    //注册观察者
    public void registerObserver(Observer param);
    //溢出观察者
    public void removeObserver(Observer observer);
    //订阅观察者
    public void notifyObservers();
}
