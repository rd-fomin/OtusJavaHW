package com.remifo.atm;

import com.remifo.storages.Storage;

import java.util.List;
import java.util.Set;

public interface ATM {

    ATMImpl getNext();

    void setNext(ATMImpl next);

    boolean hasNext();

    int getID();

    List<Integer> getMoney(int amount) throws NumberFormatException;

    void putMoney(Set<Storage> storages);

    void printBalance();

}

