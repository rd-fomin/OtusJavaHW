package com.remifo.storages;

import com.remifo.banknotes.BanknoteDenomination;

public interface Storage {

    void plusQuantity(int quantity);

    void minusQuantity() throws NumberFormatException;

    int getQuantity();

    void setQuantity(int quantity);

    int getCurrentQuantity();

    void setNullToCurrentQuantity();

    void plusCurrentQuantity(int quantity);

    BanknoteDenomination getBanknoteDenomination();

}
