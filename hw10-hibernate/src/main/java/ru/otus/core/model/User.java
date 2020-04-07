package ru.otus.core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private List<PhoneDataSet> phoneDataSets;

    public User() {  }

    public User(long id, String name, int age, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSets) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addressDataSet = addressDataSet;
        this.phoneDataSets = phoneDataSets;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public AddressDataSet getAddressDataSet() {
        return addressDataSet;
    }

    public List<PhoneDataSet> getPhoneDataSets() {
        return phoneDataSets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addressDataSet=" + addressDataSet +
                ", phoneDataSets=" + phoneDataSets +
                '}';
    }

}
