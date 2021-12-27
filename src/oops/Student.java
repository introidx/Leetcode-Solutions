package oops;

import java.util.Collections;

public class Student extends School {


    //    @Override
//    public void setName(String name) {
//        super.setName(name);
//    }
//
//    @Override
//    public void calculateAge(int age) {
//        super.calculateAge(age);
//    }
}

class Person{
    String name;
    int age;

    public void setName(String name){
        this.name =name;
    }

    public void calculateAge(int age){
        this.age = age;
    }
}

abstract class School{
    public void schoolName(){
        System.out.println("abs");
    }
}

interface College{
     default public void schoolName(){

    }
}
