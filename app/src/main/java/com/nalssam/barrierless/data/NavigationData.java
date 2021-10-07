package com.nalssam.barrierless.data;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class NavigationData {
    private final List<LatLng> latLngList = new ArrayList<>();

    public boolean addLatLng(LatLng latLng) {
        return this.latLngList.add(latLng);
    }

    public List<LatLng> getLatLngList() {
        return new ArrayList<>(this.latLngList);
    }
}
