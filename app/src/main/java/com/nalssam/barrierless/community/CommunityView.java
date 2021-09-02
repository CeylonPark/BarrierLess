package com.nalssam.barrierless.community;

import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class CommunityView implements ViewState {
    private List<Marker> markers = new ArrayList<>();

    @Override
    public void onOpen(MainActivity mainActivity) {

    }

    @Override
    public void onClose(MainActivity mainActivity) {

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
