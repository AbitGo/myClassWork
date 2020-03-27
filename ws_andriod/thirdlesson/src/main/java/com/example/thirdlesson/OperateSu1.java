package com.example.thirdlesson;
import java.util.LinkedList;
import java.util.Stack;


public class OperateSu1 {
    static Stack<InnerOperateChar> op = new Stack<>();

    public static Float getv(char op, Float f1, Float f2) {
        if (op == '+') return f2 + f1;
        else if (op == '-') return f2 - f1;
        else if (op == '*') return f2 * f1;
        else if (op == '/') return f2 / f1;
        else return Float.valueOf(-0);
    }

    public static float calrp(LinkedList<InnerOperateChar> rp) {
        Stack<Float> v = new Stack<>();
        int len = rp.size();
        for (int i = 0; i < len; i++) {
            InnerOperateChar ch =rp.get(i);
            //如果是数字则压栈
            if (ch.isNum()) v.push(Float.parseFloat(ch.getNumber()));
            //非数字进行操作压栈
            else v.push(getv(ch.getOperater(), v.pop(), v.pop()));
        }
        return v.pop();
    }


    public static LinkedList<InnerOperateChar> getrp(LinkedList<InnerOperateChar> s) {
        int len = s.size();
        LinkedList<InnerOperateChar> result = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            InnerOperateChar ch = s.get(i);
            if (ch.isNum()) {
                result.add(ch);
            } else if (!ch.isNum()) {
                //如果是运算符的话
                char oppo = ch.getOperater();
                if (oppo == '+' || oppo == '-') {
                    while (!op.empty()) {
                        result.add(op.pop());
                    }
                    op.push(ch);
                }
                if (oppo == '*' || oppo == '/') {
                    while (!op.empty() && op.peek().getOperater() == '*') {
                        result.add(op.pop());
                    }
                    op.push(ch);
                }
            }
        }
        while (!op.empty()) {
            InnerOperateChar temp = op.pop();
            result.add(temp);
        }
        return result;
    }


    //    public static void item(String[] args) {
//        LinkedList<InnerOperateChar> linkedList = new LinkedList<>();
//        linkedList.add(new InnerOperateChar("1"));
//        linkedList.add(new InnerOperateChar('+'));
//        linkedList.add(new InnerOperateChar("11"));
//        linkedList.add(new InnerOperateChar('*'));
//        linkedList.add(new InnerOperateChar("3"));
//        linkedList.add(new InnerOperateChar('-'));
//        linkedList.add(new InnerOperateChar("8"));
//        LinkedList<InnerOperateChar> paramString = getrp(linkedList);
//        //1+22*33-8
////        System.out.println(paramString);
//
//        System.out.println(calrp(paramString));
//    }
}