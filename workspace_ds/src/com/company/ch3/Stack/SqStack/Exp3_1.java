package com.company.ch3.Stack.SqStack;

public class Exp3_1 {
    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int OTHER = 2;

    public int verifyFlag(String str) {
        if ("(".equals(str) || "{".equals(str) || "[".equals(str) || "/*".equals(str)) {
            return LEFT;
        }
        if (")".equals(str) || "}".equals(str) || "]".equals(str) || "*/".equals(str)) {
            return RIGHT;
        } else
            return OTHER;
    }

    public boolean matches(String str1, String str2) {
        if (("(".equals(str1) && ")".equals(str2))
                || ("{".equals(str1) && "}".equals(str2))
                || ("[".equals(str1) && "]".equals(str2))
                || ("/*".equals(str1) && "*/".equals(str2))) {
            return true;
        } else
            return false;
    }

    public boolean isLegal(String str) throws Exception {
        //当字符串不为空或者不为空指针的时候
        if (!"".equals(str) && str != null) {
            SqStack sqStack = new SqStack(40);
            int len = str.length();
            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);
                String temp = String.valueOf(c);
                if (i != len) {
                    if ((c == '/' && '*' == str.charAt(i + 1)) || (c == '*' && '/' == str.charAt(i + 1))){
                        temp = temp.concat(String.valueOf(str.charAt(i+1)));
                        ++i;
                    }
                }
                if(LEFT==verifyFlag(temp)){
                    sqStack.push(temp);
                }else if(RIGHT==verifyFlag(temp)){
                    if(sqStack.isEmpty() || !matches(sqStack.pop().toString(),temp)){
                        System.out.println("Java语法不合法");
                        return false;
                    }
                }
            }
            //当栈不为空的时候就证明还有 没有被抵消的符号
            if(!sqStack.isEmpty()){
                System.out.println("Java语法不合法");
                return false;
            }
            return true;
        }
        else{
            System.out.println("Java语法不合法");
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        Exp3_1 exp3_1 = new Exp3_1();
        String exp_String1 = "a=a+b*(c+a)";
        String exp_String2 = "a=(b+c/(d*e)*f";
        if(exp3_1.isLegal(exp_String1)){
            System.out.println("合法");
        }else{
            System.out.println("不合法");
        }
        if(exp3_1.isLegal(exp_String2)){
            System.out.println("合法");
        }else{
            System.out.println("不合法");
        }
    }
}
