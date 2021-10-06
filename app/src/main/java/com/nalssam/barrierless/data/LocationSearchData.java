package com.nalssam.barrierless.data;

public class LocationSearchData {
    private final String name;
    private final String address;

    public LocationSearchData(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
}
