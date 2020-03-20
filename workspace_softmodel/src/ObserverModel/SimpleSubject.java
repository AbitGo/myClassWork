package ObserverModel;

import java.util.LinkedList;
import java.util.List;

public class SimpleSubject implements Subject {
    private List<Observer> members;
    //当前需要订阅的值
    private int value;

    public SimpleSubject(){
        members = new LinkedList<>();
    }
    @Override
    public void registerObserver(Observer param) {
        //进行消息观察者注册，只需要在成员链表中加入成员即可
        this.members.add(param);
    }

    @Override
    public void removeObserver(Observer observer) {
        //移除当前的成员时候，需要进行以下操作
        //1.长度是否大于0
        //2.当前是否该成员
        if(this.members.size()==0){
            return;
        }
        int index = this.members.indexOf(observer);
        if(index>=0){//当前存在，则直接移除即可
            this.members.remove(index);
        }

    }

    @Override
    public void notifyObservers() {
        //通知观察者
        for (Observer param : members) {
            param.update(this.value);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }
}
