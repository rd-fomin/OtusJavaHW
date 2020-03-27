package com.remifo.test;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @OneToOne(mappedBy = "street")
    @Column(name = "street")
    private AddressDataSet addressDataSet;
    @OneToMany(mappedBy = "number")
    @Column(name = "number")
    private PhoneDataSet phoneDataSet;

    public User() {

    }

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(long id, String name, int age, AddressDataSet addressDataSet, PhoneDataSet phoneDataSet) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addressDataSet = addressDataSet;
        this.phoneDataSet = phoneDataSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public AddressDataSet getAddressDataSet() {
        return addressDataSet;
    }

    public void setAddressDataSet(AddressDataSet addressDataSet) {
        this.addressDataSet = addressDataSet;
    }

    public PhoneDataSet getPhoneDataSet() {
        return phoneDataSet;
    }

    public void setPhoneDataSet(PhoneDataSet phoneDataSet) {
        this.phoneDataSet = phoneDataSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "first=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
