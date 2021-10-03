package com.nalssam.barrierless.data;

import com.naver.maps.geometry.LatLng;

import java.util.*;

public class NearbyData {
    public enum BarrierFreeType {
        TOILET, ELEVATOR, SLOPE, SWING_DOOR
    }

    public Map<LatLng, BarrierFreeType> getBarrierFree(BarrierFreeType... barrierFreeTypes) {
        Set<BarrierFreeType> types = new HashSet<>(Arrays.asList(barrierFreeTypes));
        Map<LatLng, BarrierFreeType> facilities = new HashMap<>();
        if(types.contains(BarrierFreeType.TOILET)) {
            facilities.put(new LatLng(36.313145920576645, 127.37918978788326), BarrierFreeType.TOILET);
            facilities.put(new LatLng(36.313444112444884, 127.37961359132368), BarrierFreeType.TOILET);
            facilities.put(new LatLng(36.31355950006274, 127.38019404582923), BarrierFreeType.TOILET);
            facilities.put(new LatLng(36.31264436326467, 127.38071960827881), BarrierFreeType.TOILET);
            facilities.put(new LatLng(36.312412120673855, 127.37977031912874), BarrierFreeType.TOILET);
        }
        return facilities;
    }
}
