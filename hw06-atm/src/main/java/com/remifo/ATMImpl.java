package com.remifo;

import com.remifo.storages.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ATMImpl implements ATM {

    private Set<Storage> numberBanknotesInATM;

    public ATMImpl(Set<Storage> numberBanknotesInATM) {
        this.numberBanknotesInATM = numberBanknotesInATM;
    }

    @Override
    public void start() {

    }

    @Override
    public List<Integer> getMoney(int amount) throws NumberFormatException {
        numberBanknotesInATM.forEach(Storage::setNullToCurrentQuantity);
        if ( amount % 100 == 0 ) {
            for ( Storage storage : numberBanknotesInATM ) {
                int denomination = storage.getBanknoteDenomination().getDenomination();
                int newAmount = Math.min(amount / denomination, storage.getQuantity());
                storage.plusCurrentQuantity( newAmount );
                amount -= denomination * newAmount;
            }
            if ( amount != 0 )
                throw new NumberFormatException( "There is no so many money in the ATM" );
            List<Integer> result = new ArrayList<>();
            for ( Storage storage : numberBanknotesInATM ) {
                storage.minusQuantity();
                result.add(storage.getCurrentQuantity());
            }
            return result;
        }
        throw new NumberFormatException( "Banknote can't be less than 100" );
    }

    @Override
    public void putMoney(Set<Storage> storages) {
        if ( storages != null ) {
            storages.forEach(storage -> {
                for (Storage storage1 : numberBanknotesInATM)
                    if (storage.equals(storage1)) storage1.plusQuantity(storage.getQuantity());
            });
        }
    }

    @Override
    public void printAllMoneyInATM() {
        System.out.print("All money in the ATM: ");
        System.out.println(numberBanknotesInATM);
        System.out.println();
    }

}
