package com.remifo.storages;

import com.remifo.banknotes.Denomination;

public interface Storage extends Comparable<Storage> {

    void plusQuantity(int quantity);

    void minusQuantity() throws NumberFormatException;

    int getQuantity();

    int getCurrentQuantity();

    void setNullToCurrentQuantity();

    void plusCurrentQuantity(int quantity);

    Denomination getDenomination();

}
