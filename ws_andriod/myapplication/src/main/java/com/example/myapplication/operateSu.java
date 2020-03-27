package com.example.myapplication;
import java.util.Stack;


class operateSu {
    static Stack<Character> op = new Stack<>();

    public static Float getv(char op, Float f1, Float f2){
        if(op == '+') return f2 + f1;
        else if(op == '-') return f2 - f1;
        else if(op  == '*') return f2 * f1;
        else if(op == '/') return f2 / f1;
        else return Float.valueOf(-0);
    }

    public static float calrp(String rp){
        Stack<Float> v = new Stack<>();
        char[] arr = rp.toCharArray();
        int len = arr.length;
        for(int i = 0; i < len; i++){
            Character ch = arr[i];

            // if is operand, push to the stack
            if(ch >= '0' && ch <= '9') v.push(Float.valueOf(ch - '0'));
                // if is operator, calculate the result
                // with top 2 operands in the stack,
                // push the result into the stack
            else v.push(getv(ch, v.pop(), v.pop()));
        }
        return v.pop();
    }

    /**
     * from infix to postfix
     * @param s - String in the form of infix
     * @return String in the form of postfix
     */
    public static String getrp(String s){
        char[] arr = s.toCharArray();
        int len = arr.length;
        String out = "";

        for(int i = 0; i < len; i++){
            char ch = arr[i];
            if(ch == ' ') continue;

            // if is operand, add to
            // the output stream directly
            if(ch >= '0' && ch <= '9') {
                out+=ch;
                continue;
            }

            //if is '(', push to the stack directly
            if(ch == '(') op.push(ch);

            //if is '+' or '-', pop the operator
            // from the stack until '(' and add to
            // the output stream
            //push the operator to the stack
            if(ch == '+' || ch == '-'){
                while(!op.empty() && (op.peek() != '('))
                    out+=op.pop();
                op.push(ch);
                continue;
            }

            //if is '*' or '/', pop the operator stack and
            // add to the output stream
            // until lower priority or '('
            //push the operator to the stack
            if(ch == '*' || ch == '/'){
                while(!op.empty() && (op.peek() == '*' || op.peek() == '/'))
                    out+=op.pop();
                op.push(ch);
                continue;
            }

            //if is ')' pop the operator stack and
            // add to the output stream until '(',
            // pop '('
            if(ch == ')'){
                while(!op.empty() && op.peek() != '(')
                    out += op.pop();
                op.pop();
                continue;
            }
        }
        while(!op.empty()) out += op.pop();
        return out;
    }
}