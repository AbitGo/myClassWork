package com.company;

public class Exp2_50_1 {
    public static String reversalString(String paramString)
    {
        StringBuffer returnString = new StringBuffer(paramString.length());
        char[] paramChar = paramString.toCharArray();
        for(int i=paramString.length()-1;i>=0;i--){
            returnString.append(paramChar[i]);
        }
        return returnString.toString();
    }
    public static void main(String[] args) {
        System.out.println(reversalString("abcd"));
    }
}
