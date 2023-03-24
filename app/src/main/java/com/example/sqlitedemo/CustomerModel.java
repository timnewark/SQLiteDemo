package com.example.sqlitedemo;



public class CustomerModel{

    private int id;
    private String name;
    private int age;
    private boolean isActive;



    //CONSTRUCTORS GO HERE
    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;

    }
        //NON PARAMETERISED CONSTRUCTOR, NOT SURE IF WE WILL USE IT OR NOT
    public CustomerModel(int id) {

    }

    // toString is necessary for printing the contents of a class object. its a very common method.
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }



    //GETTERS AND SETTERS BELOW - four of each
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
