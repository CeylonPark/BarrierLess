package com.nalssam.barrierless.nearby;

import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.BottomNavigationFragment;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.BarrierFreeData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.util.ArrayList;
import java.util.List;

public class NearbyBarrierFree {
    private final String name;
    private final NearbyFragment nearbyFragment;
    private final NaverMap map;
    private final List<Marker> markerList = new ArrayList<>();
    private OverlayImage icon;
    private boolean state;

    public NearbyBarrierFree(String name, NearbyFragment nearbyFragment, NaverMap map) {
        this.name = name;
        this.nearbyFragment = nearbyFragment;
        this.map = map;
        this.state = true;

        for(LatLng latLng : BarrierFreeData.getInstance().getBarrierFreeList(name)) {
            this.registerBarrierFree(latLng);
        }

    }

    public String getName() {
        return this.name;
    }

    public void registerBarrierFree(LatLng latLng) {
        Marker marker = new Marker(latLng);
        marker.setIcon(this.icon);
        marker.setVisible(this.state);
        marker.setMap(this.map);

        marker.setOnClickListener(view -> {
            this.nearbyFragment.showBarrierFreeInfo(marker);
            return true;
        });

        this.markerList.add(marker);
    }

    public void setIcon(OverlayImage icon) {
        this.icon = icon;
        for(Marker marker : this.markerList) {
            marker.setIcon(icon);
        }
    }

    public void setVisible(boolean visible) {
        for(Marker marker : this.markerList) {
            marker.setVisible(visible);
        }
        this.state = visible;
    }

    public boolean isVisible() {
        return this.state;
    }

    public void remove() {
        for(Marker marker : this.markerList) {
            marker.setMap(null);
        }
        this.markerList.clear();
    }
}
