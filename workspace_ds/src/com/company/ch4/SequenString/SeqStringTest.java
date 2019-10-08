package com.company.ch4.SequenString;

public class SeqStringTest {
    public static void main(String[] args) {
        char[] charArray={'w','o','r','l','d'};
        SeqString s1 = new SeqString();
        SeqString s2 = new SeqString("hello");
        SeqString s3 = new SeqString(charArray);
        s1.insert(0,s2);
        s1.disPlay();
        s1.insert(1,s3);
        s1.disPlay();

        s1.delete(1,4);
        s1.disPlay();

        System.out.println(s1.subString(2,5));

    }
}
