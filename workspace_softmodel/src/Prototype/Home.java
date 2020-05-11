package Prototype;

public abstract class Home {
    //房梁
    abstract void beam();
    //地基
    abstract void base();
    //水管
    abstract void pipe();

    //模板
    public final void build(){
        base();
        beam();
        pipe();
    }
}
