package com.remifo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressDataSet {
    @Column(name = "street")
    private String street;
}
