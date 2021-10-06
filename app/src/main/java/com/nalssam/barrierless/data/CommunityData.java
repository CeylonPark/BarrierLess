package com.nalssam.barrierless.data;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class CommunityData {
    private final static CommunityData communityData = new CommunityData();

    public static CommunityData getInstance() {
        return CommunityData.communityData;
    }

    private final List<LatLng> userReport = new ArrayList<>();

    private CommunityData() {
        this.userReport.add(new LatLng(36.31355950006274, 127.38019404582923));
        this.userReport.add(new LatLng(36.31200707404612, 127.3791753033488));
        this.userReport.add(new LatLng(36.312382275092645, 127.38125287513685));
    }

    public List<LatLng> getUserReportList() {
        return new ArrayList<>(this.userReport);
    }
}
