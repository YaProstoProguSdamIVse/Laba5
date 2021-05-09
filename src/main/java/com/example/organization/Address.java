package com.example.organization;

import java.util.Comparator;
import java.util.Objects;

public class Address implements Comparable<Address> {

    private String street; //Поле не может быть null
    private String zipCode; //Длина строки должна быть не меньше 4, Поле не может быть null

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return street.equals(address.street) && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode);
    }

    @Override
    public String toString() {
        return "[" + street + "; " + zipCode + "]";
    }

    @Override
    public int compareTo(Address o) {
        return Comparator.comparing(Address::getStreet)
                .compare(this, o);
    }
}