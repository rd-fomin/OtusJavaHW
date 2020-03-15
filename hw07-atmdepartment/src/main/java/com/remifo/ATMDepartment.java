package com.remifo;

import com.remifo.atm.ATM;
import com.remifo.atm.ATMImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ATMDepartment {

    private List<ATMImpl> atmList = new ArrayList<>();
    private List<ATMImpl> atmMementos = new ArrayList<>();

    public ATMDepartment() { }

    public List<ATMImpl> getAtmList() {
        return this.atmList;
    }

    public void addATM( ATMImpl atmPrev ) {
        var atm = new ATMImpl(  );
        if (atmPrev != null)
            atmPrev.setNext( atm );
        this.atmList.add( atm );
        this.atmMementos.add( atm );
    }

    public int getBalance() {
        int balance = atmList.get(0).getStorageBalance();
        if ( atmList.get(0).hasNext() )
            balance += atmList.get(0).getNext().getStorageBalance();
        return balance;
    }

    public void printBalance() {
        System.out.println( getBalance() );
    }

    public void reset() {
        atmList.sort( Comparator.comparing( ATM::getID ) );
        atmMementos.sort( Comparator.comparing( ATM::getID ) );
        atmMementos.forEach( atm -> atmList.forEach( atm1 -> atm1 = atm ) );
    }

}
