package com.example.mydatabase;

public class CustomerModel {
    int id;
    String name;
    int age;
    boolean isActiveCustomer;

    public CustomerModel(int id, String name, int age, boolean isActiveCustomer) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActiveCustomer = isActiveCustomer;
    }

    public CustomerModel(){

    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActiveCustomer=" + isActiveCustomer +
                '}';
    }

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

    public boolean isActiveCustomer() {
        return isActiveCustomer;
    }

    public void setActiveCustomer(boolean activeCustomer) {
        isActiveCustomer = activeCustomer;
    }
}
