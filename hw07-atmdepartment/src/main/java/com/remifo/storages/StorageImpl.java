package com.remifo.storages;

import com.remifo.banknotes.Denomination;

import java.util.Objects;

public class StorageImpl implements Storage {

    private final Denomination denomination;
    private int quantity;
    private int currentQuantity = 0;

    public StorageImpl(Denomination denomination, int quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    @Override
    public void plusQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public void minusQuantity() throws NumberFormatException {
        if (this.quantity < this.currentQuantity)
            throw new NumberFormatException();
        this.quantity -= this.currentQuantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getCurrentQuantity() {
        return currentQuantity;
    }

    @Override
    public void setNullToCurrentQuantity() {
        this.currentQuantity = 0;
    }

    @Override
    public void plusCurrentQuantity(int quantity) {
        this.currentQuantity += quantity;
    }

    @Override
    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageImpl storage = (StorageImpl) o;
        return denomination == storage.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }

    @Override
    public String toString() {
        return String.format("%s - %3d", denomination, quantity);
    }

    @Override
    public int compareTo(Storage o) {
        return this.denomination.compareTo( o.getDenomination() );
    }
}
