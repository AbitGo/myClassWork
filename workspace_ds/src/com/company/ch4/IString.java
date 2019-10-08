package com.company.ch4;

import com.company.ch4.SequenString.SeqString;

public interface IString{
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object charAt(int index);
    public char[] subString(int begin, int end);
    public IString insert(int offset,IString str);
    public IString concat(IString str);
    public int indexOf(IString str,int begin);
}
