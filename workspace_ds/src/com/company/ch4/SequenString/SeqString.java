package com.company.ch4.SequenString;

import com.company.ch3.IQueue;
import com.company.ch4.IString;

public class SeqString implements IString {
    private char[] strValue;
    private int curLen;

    public SeqString() {
        this.curLen = 0;
        strValue = new char[0];
    }

    public SeqString(String str) {
        char[] tempString = str.toCharArray();
        strValue = new char[str.length()];
        for (char a : tempString) {
            strValue[this.curLen] = a;
            this.curLen++;
        }
    }

    public SeqString(char[] str) {
        strValue = new char[str.length];
        for (char a : str) {
            strValue[this.curLen] = a;
            this.curLen++;
        }
    }

    @Override
    public void clear() {
        this.curLen = 0;
    }

    @Override
    public boolean isEmpty() {
        if (this.curLen == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int length() {
        return curLen;
    }

    @Override
    public Object charAt(int index) {
        if (this.isEmpty()) {
            System.out.println("此字符串为空");
            return null;
        } else {
            if (index >= curLen || index < 0) {
                System.out.println("位置不合法");
                return null;
            }
        }
        return strValue[index];
    }

    @Override
    public char[] subString(int begin, int end) {
        if (this.isEmpty()) {
            System.out.println("此字符串为空");
            return null;
        } else {
            if (begin < 0 || end < 0 || end < begin || end >= curLen || begin >= curLen) {
                System.out.println("位置不合法");
                return null;
            } else if(begin==end){
                char[] result = new char[1];
                result[0] = this.strValue[end];
                return result;
            } else{
                //创建长度韦end-begin长度的字符串
                char[] result = new char[end-begin];
                //使用for循环为长度为end-begin长度的字符串赋值
                for (int i = 0; i < result.length; i++) {
                    result[i] = this.strValue[i+begin];
                }
                return result;
            }
        }
    }

    @Override
    public IString insert(int offset, IString str) {
        if(offset<0||offset>this.curLen){
            System.out.println("插入位置不正确");
            return null;
        }
        int len = str.length();
        int needLen = len+this.curLen;
        if(needLen>strValue.length){
            alloacte(needLen);
        }

        //从offset向后移动len长度
        for(int i=this.curLen-1;i>=offset;i--){
            strValue[len+i]=strValue[i];
        }
        for(int i =0;i<len;i++) {
            strValue[offset + i] = (char) str.charAt(i);
        }
        this.curLen = needLen;
        return this;
    }

    public IString delete(int begin,int end){
        if(begin<0||end>this.curLen || begin>end){
            System.out.println("删除位置不正确");
            return null;
        }

        for(int i=0;i<curLen-end;i++){
            strValue[begin+i] = strValue[end+i];
        }

//        int temp_start = begin;
//        for(int i=end;i<curLen;i++){
//            strValue[temp_start++] = strValue[i];
//        }
        curLen = curLen-(end-begin);
        return this;
    }


    public void alloacte(int newCapacity){
        char[] temp = strValue;
        strValue = new char[newCapacity];
        for(int i =0;i<temp.length;i++){
            strValue[i] = temp[i];
        }
    }

    public int compareTo(SeqString string){
        int len1 = curLen;
        int len2 = string.curLen;
        int n = Math.min(len1,len2);
        char[] s1 = strValue;
        char[] s2 = string.strValue;
        int k = 0;
        while (k<n){
            char ch1 = s1[k];
            char ch2 = s2[k];
            if(ch1!=ch2){
                return ch1-ch2;
            }
            k++;
        }
        return len1-len2;
    }
    @Override
    public IString concat(IString str) {
        return insert(curLen,str);
    }

    @Override
    public int indexOf(IString str, int begin) {
        return 0;
    }

    public void disPlay(){
        for(int i=0;i<curLen;i++){
            System.out.print(strValue[i]);
        }
        System.out.println();
    }
}
