package ObserverModel;

public class ObserverTest {
    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();
        //注册两个观察者，并注册
        SimpleObserver simpleObserver1 = new SimpleObserver(simpleSubject,"观察者1");
        SimpleObserver simpleObserver2 = new SimpleObserver(simpleSubject,"观察者2");
        System.out.println("设置广播值100观察数据的变化");
        simpleSubject.setValue(100);
        simpleSubject.notifyObservers();
        simpleObserver1.showMessage();
        simpleObserver2.showMessage();

        System.out.println("取消一个观察者1的权限，即剔除");
        simpleSubject.removeObserver(simpleObserver1);

        System.out.println("设置广播值50观察数据的变化");
        simpleSubject.setValue(50);
        simpleSubject.notifyObservers();

        simpleObserver1.showMessage();
        simpleObserver2.showMessage();

    }
}
