package com.example.pc.myapplication;

import java.io.Serializable;
//数据对象需要系列化
public class User implements Serializable{
    private String name;
    private String pwd;
    private int age;

    public User(){}
    public User(String name,String pwd,int age){
        this.name=name;
        this.pwd=pwd;
        this.age=age;
    }

    public String toString(){
        return "name: "+name+",age: "+age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
