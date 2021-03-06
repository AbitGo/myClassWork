package com.company.ch2.interfaceFile;

public interface SequenceTableInterface {
    void clear();
    boolean isEmpty();
    int getLenght();
    Object get(int i) throws Exception;
    boolean insert(int index,Object x) throws Exception;
    boolean remove(int i);
    int indexOf(Object x);
    void display();
}
