package com.nalssam.barrierless.community;

import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class CommunityView implements ViewState {
    private List<Marker> markers = new ArrayList<>();

    @Override
    public void onOpen(MainActivity mainActivity) {

        //마커 테스트
        Marker marker = new Marker();
        markers.add(marker);
        marker.setPosition(new LatLng(36.38567474070798, 127.30387532269494));
        marker.setMaxZoom(16);
        marker.setMap(mainActivity.getNaverMap());
    }

    @Override
    public void onClose(MainActivity mainActivity) {
        for(Marker marker : this.markers) {
            marker.setMap(null);
        }
        this.markers.clear();
    }

    @Override
    public int getTopBarId() {
        return R.id.communityTopBar;
    }

    @Override
    public int getIconId() {
        return R.id.community;
    }

    @Override
    public int getReplaceIconId(boolean color) {
        return color ? R.drawable.ic_community_blue : R.drawable.ic_community_gray;
    }

    @Override
    public int getTextId() {
        return R.id.communityText;
    }
}
