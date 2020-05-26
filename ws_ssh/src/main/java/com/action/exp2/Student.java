package com.action.exp2;

public class Student {
    private String name;
    private String student_id;
    private String password;
    private boolean ismale;
    private int age;
    private String nativePalce;
    private String info;
    private String[] className;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String[] getClassName() {
        return className;
    }

    public void setClassName(String[] className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsmale() {
        return ismale;
    }

    public void setIsmale(boolean ismale) {
        this.ismale = ismale;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNativePalce() {
        return nativePalce;
    }

    public void setNativePalce(String nativePalce) {
        this.nativePalce = nativePalce;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toString(){
        return getName()+" "+getStudent_id()+" "+getAge()+" "+getNativePalce()+" "+getClassName()+" "+getInfo()+" "+getPassword();
    }
}
