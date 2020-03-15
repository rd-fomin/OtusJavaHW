package com.remifo;

import com.remifo.banknotes.BanknoteDenomination;
import com.remifo.storages.Storage;
import com.remifo.storages.StorageImpl;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Storage> storages = new TreeSet<>(Comparator.comparing(Storage::getBanknoteDenomination));
        storages.add(new StorageImpl(BanknoteDenomination.Hundred, 100));
        storages.add(new StorageImpl(BanknoteDenomination.FiveHundred, 50));
        storages.add(new StorageImpl(BanknoteDenomination.Thousand, 10));
        Set<Storage> set = new TreeSet<>(Comparator.comparing(Storage::getBanknoteDenomination));
        set.add(new StorageImpl(BanknoteDenomination.Hundred, 10));
        set.add(new StorageImpl(BanknoteDenomination.FiveHundred, 7));
        set.add(new StorageImpl(BanknoteDenomination.Thousand, 4));
        ATM atm = new ATMImpl(storages);
        atm.start();
        System.out.println(atm.getMoney(7600));
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(3700));
        atm.printAllMoneyInATM();
        atm.putMoney(set);
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(11500));
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(17200));
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(7600));
        atm.printAllMoneyInATM();
        atm.putMoney(set);
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(17200));
        atm.printAllMoneyInATM();
        System.out.println(atm.getMoney(7600));
        atm.printAllMoneyInATM();
        atm.putMoney(set);
        atm.printAllMoneyInATM();

    }
}
