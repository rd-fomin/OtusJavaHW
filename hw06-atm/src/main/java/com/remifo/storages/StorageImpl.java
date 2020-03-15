package com.remifo.storages;

import com.remifo.banknotes.BanknoteDenomination;

import java.util.Objects;

public class StorageImpl implements Storage {

    private final BanknoteDenomination banknoteDenomination;
    private int quantity;
    private int currentQuantity = 0;

    public StorageImpl(BanknoteDenomination banknoteDenomination, int quantity) {
        this.banknoteDenomination = banknoteDenomination;
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public BanknoteDenomination getBanknoteDenomination() {
        return banknoteDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageImpl storage = (StorageImpl) o;
        return banknoteDenomination == storage.banknoteDenomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknoteDenomination);
    }

    @Override
    public String toString() {
        return banknoteDenomination + " - " + quantity;
    }
}
