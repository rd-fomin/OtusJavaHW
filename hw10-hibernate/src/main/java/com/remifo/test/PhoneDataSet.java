package com.remifo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class PhoneDataSet {
    @Column(name = "number")
    private String number;
}
