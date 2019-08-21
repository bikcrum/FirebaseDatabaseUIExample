package com.bikcrum.firebasedatabasewithauthexample;

public class PatientModel {
    private String name;
    private String address;

    public PatientModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public PatientModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
