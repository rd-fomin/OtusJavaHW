package com.remifo.atm;

import com.remifo.storages.Storage;
import com.remifo.storages.StorageSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ATMImpl implements ATM, StorageBalance {

    private ATMImpl next;
    private final int ID = ATMIDGenerator.getID();
    private StorageSet storageATM = StorageSet.getInstance();

    public ATMImpl() { }

    @Override
    public ATMImpl getNext() {
        return next;
    }

    @Override
    public void setNext(ATMImpl next) {
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public int getStorageBalance() {
        return storageATM.stream()
                .mapToInt( storage -> storage.getQuantity() * storage.getDenomination().getValue() )
                .sum();
    }

    @Override
    public List<Integer> getMoney(int amount) throws NumberFormatException {
        storageATM.forEach( Storage::setNullToCurrentQuantity );
        if ( amount % 100 == 0 ) {
            for ( Storage storage : storageATM ) {
                int denomination = storage.getDenomination().getValue();
                int newAmount = Math.min( amount / denomination, storage.getQuantity() );
                storage.plusCurrentQuantity( newAmount );
                amount -= denomination * newAmount;
            }
            if ( amount != 0 )
                throw new NumberFormatException( "There is no so many money in the ATM" );
            storageATM.forEach( Storage::minusQuantity );
            return storageATM.stream()
                            .map( Storage::getCurrentQuantity )
                            .collect( Collectors.toList() );
        }
        throw new NumberFormatException( "Amount has to be multiply to 100" );
    }

    @Override
    public void putMoney(Set<Storage> storages) {
        Optional.of( storages ).get().forEach(
                storage -> storageATM.stream()
                        .filter( storage::equals )
                        .forEach( storageATM -> storageATM.plusQuantity( storage.getQuantity() ) )
        );
    }

    @Override
    public void printBalance() {
        System.out.printf( "All money in the ATM - %d: %s - %d\n", ID, storageATM, getStorageBalance() );
    }

}
