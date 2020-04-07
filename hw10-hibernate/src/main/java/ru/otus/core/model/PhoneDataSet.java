package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    public PhoneDataSet() {  }

    public PhoneDataSet(long id, String number) {
        this.id = id;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }
}
