package com.nalssam.barrierless.around;

import com.nalssam.barrierless.LocationFragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;

import java.util.*;

public class AroundView implements ViewState {
    private LocationFragment locationFragment;

    private final List<Marker> markerList = new ArrayList<>();

    public AroundView() {
        Map<LatLng, OverlayImage> latLngMap = new HashMap<>();
        latLngMap.put(new LatLng(36.39064492429601, 127.30882718744387), MarkerIcons.PINK);
        latLngMap.put(new LatLng(36.392283977477604, 127.31413960157613), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.39105547472043, 127.31079942407383), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.38981095276135, 127.3124650615602), MarkerIcons.YELLOW);
        latLngMap.put(new LatLng(36.39003103856445, 127.31374851856948), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.39256165123829, 127.3115900094301), MarkerIcons.LIGHTBLUE);
        for(LatLng latLng : latLngMap.keySet()) {
            Marker marker = new Marker();
            marker.setPosition(latLng);
            marker.setIcon(Objects.requireNonNull(latLngMap.get(latLng)));
            markerList.add(marker);
        }
    }

    @Override
    public void onOpen(MainActivity mainActivity) {
        this.locationFragment = new LocationFragment();
        mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, this.locationFragment).commit();

        for(Marker marker : this.markerList) {
            marker.setMap(mainActivity.getNaverMap());
        }
    }

    @Override
    public void onClose(MainActivity mainActivity) {
        if(this.locationFragment != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(this.locationFragment).commit();
            this.locationFragment = null;
        }
        for(Marker marker : this.markerList) {
            marker.setMap(null);
        }
    }

    @Override
    public int getTopBarId() {
        return R.id.aroundTopBar;
    }

    @Override
    public int getIconId() {
        return R.id.around;
    }

    @Override
    public int getReplaceIconId(boolean color) {
        return color ? R.drawable.ic_around_blue : R.drawable.ic_around_gray;
    }

    @Override
    public int getTextId() {
        return R.id.aroundText;
    }
}
