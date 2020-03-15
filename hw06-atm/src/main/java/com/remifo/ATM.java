package com.remifo;

import com.remifo.storages.Storage;

import java.util.List;
import java.util.Set;

public interface ATM {

    void start();

    List<Integer> getMoney(int amount) throws NumberFormatException;

    void putMoney(Set<Storage> storages);

    void printAllMoneyInATM();
}
