package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "contact")
public class Address {

    @XmlAttribute
    private  String address;

    public Address() {

    }

    public Address(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{"
                + "address='" + address + '\''
                + '}';
    }
}
