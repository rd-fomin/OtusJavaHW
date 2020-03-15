package com.remifo;

import com.remifo.atm.ATM;
import com.remifo.atm.ATMImpl;
import com.remifo.banknotes.Denomination;
import com.remifo.storages.Storage;
import com.remifo.storages.StorageImpl;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Storage> set = new TreeSet<>(Comparator.comparing(Storage::getDenomination));
        set.add(new StorageImpl(Denomination.Hundred, 10));
        set.add(new StorageImpl(Denomination.FiveHundred, 7));
        set.add(new StorageImpl(Denomination.Thousand, 5));
        var atmDepartment = new ATMDepartment();
        atmDepartment.addATM( null );
        atmDepartment.addATM( atmDepartment.getAtmList().get(0) );
        atmDepartment.addATM( atmDepartment.getAtmList().get(1) );
        ATM atm1 = atmDepartment.getAtmList().get( 0 );
        ATM atm2 = atmDepartment.getAtmList().get( 1 );
        ATM atm3 = atmDepartment.getAtmList().get( 2 );
        printAll( atmDepartment );
        atm1.putMoney( set );
        printAll( atmDepartment );
        atm1.getMoney(7600);
        printAll( atmDepartment );
        atm1.putMoney( set );
        printAll( atmDepartment );
        atm1.getMoney(3700);
        printAll( atmDepartment );
        atm1.putMoney(set);
        printAll( atmDepartment );
        atm1.putMoney(set);
        printAll( atmDepartment );
        atm1.getMoney(11500);
        printAll( atmDepartment );
        atm1.getMoney(13200);
        printAll( atmDepartment );
        atm2.putMoney( set );
        printAll( atmDepartment );
        atm2.getMoney(7600);
        printAll( atmDepartment );
        atm2.putMoney(set);
        printAll( atmDepartment );
        atm2.putMoney(set);
        printAll( atmDepartment );
        atm2.getMoney(17200);
        printAll( atmDepartment );
        atm2.getMoney(7600);
        printAll( atmDepartment );
        atm2.putMoney(set);
        printAll( atmDepartment );

    }

    private static void printAll(ATMDepartment atmDepartment) {
        atmDepartment.getAtmList().forEach( ATMImpl::printBalance );
        atmDepartment.printBalance();
    }

}
