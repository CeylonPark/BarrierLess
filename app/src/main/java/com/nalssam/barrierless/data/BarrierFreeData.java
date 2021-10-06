package com.nalssam.barrierless.data;

import com.naver.maps.geometry.LatLng;

import java.util.*;

public class BarrierFreeData {
    private final static BarrierFreeData barrierFreeData = new BarrierFreeData();

    public static BarrierFreeData getInstance() {
        return BarrierFreeData.barrierFreeData;
    }

    private final Map<LatLng, String> barrierFrees = new HashMap<>();

    public BarrierFreeData() {
        // 임시 데이터 TOILET
        this.barrierFrees.put(new LatLng(36.313145920576645, 127.37918978788326), "TOILET");
        this.barrierFrees.put(new LatLng(36.313444112444884, 127.37961359132368), "TOILET");
        this.barrierFrees.put(new LatLng(36.31355950006274, 127.38019404582923), "TOILET");
        this.barrierFrees.put(new LatLng(36.31264436326467, 127.38071960827881), "TOILET");
        this.barrierFrees.put(new LatLng(36.312412120673855, 127.37977031912874), "TOILET");
        // 임시 데이터 SLOPE
        this.barrierFrees.put(new LatLng(36.31216345930625, 127.38046531714951), "SLOPE");
        this.barrierFrees.put(new LatLng(36.314245134494186, 127.38005944452243), "SLOPE");
        this.barrierFrees.put(new LatLng(36.31391160574809, 127.37878665503388), "SLOPE");
        this.barrierFrees.put(new LatLng(36.31200707404612, 127.3791753033488), "SLOPE");
        this.barrierFrees.put(new LatLng(36.3114289097978, 127.38034298777632), "SLOPE");
        // 임시 데이터 ELEVATOR
        this.barrierFrees.put(new LatLng(36.31334436425122, 127.37956306049796), "ELEVATOR");
        this.barrierFrees.put(new LatLng(36.312382275092645, 127.38125287513685), "ELEVATOR");
        this.barrierFrees.put(new LatLng(36.3121903337986, 127.37931719235053), "ELEVATOR");
        // 임시 데이터 SWING_DOOR
        this.barrierFrees.put(new LatLng(36.312725500487446, 127.38026993539552), "SWING_DOOR");
        this.barrierFrees.put(new LatLng(36.31462660060005, 127.38153482502292), "SWING_DOOR");
    }

    public List<LatLng> getBarrierFreeList(String name) {
        List<LatLng> barrierFree = new ArrayList<>();
        for(LatLng latLng : this.barrierFrees.keySet()) {
            if(name.equals(this.barrierFrees.get(latLng))) {
                barrierFree.add(latLng);
            }
        }
        return barrierFree;
    }
}
