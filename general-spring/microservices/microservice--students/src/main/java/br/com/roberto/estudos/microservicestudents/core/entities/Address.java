package br.com.roberto.estudos.microservicestudents.core.entities;

public class Address {
    private String street;
    private String city;
    private BrasilStateEnum brasilState;
    private String zip;
    private StateEnum state;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BrasilStateEnum getBrasilState() {
        return brasilState;
    }

    public void setBrasilState(BrasilStateEnum brasilState) {
        this.brasilState = brasilState;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}
