package com.company.ch4;

public interface IString{
    public void clear();
    public boolean isEmpty();
    public int length();
    public char charAt(int index);
    public IString subString(int begin,int end);
    public IString insert(int offset,IString str);
    public IString concat(IString str);
    public int indxOf(IString str,int begin);
}
