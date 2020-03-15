package com.remifo.storages;

import com.remifo.banknotes.Denomination;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class StorageSet extends TreeSet<Storage> {

    private StorageSet() {
        super();
    }

    private StorageSet(Comparator<? super Storage> comparator) {
        super(comparator);
    }

    private StorageSet(@NotNull Collection<? extends Storage> c) {
        super(c);
    }

    private StorageSet(SortedSet<Storage> s) {
        super(s);
    }

    @NotNull
    @Contract(" -> new")
    public static StorageSet getInstance() {
        StorageSet storageSet = new StorageSet( Comparator.comparing( Storage::getDenomination ) );
        Denomination[] denominations = Denomination.values();
        for ( Denomination denomination : denominations )
            storageSet.add( new StorageImpl( denomination, 0 ) );
        return storageSet;
    }

}
