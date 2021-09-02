package com.nalssam.barrierless.directions;

import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;

public class DirectionsView implements ViewState {

    @Override
    public void onOpen(MainActivity mainActivity) {

    }

    @Override
    public void onClose(MainActivity mainActivity) {

    }

    @Override
    public int getTopBarId() {
        return R.id.directionsTopBar;
    }

    @Override
    public int getIconId() {
        return R.id.directions;
    }

    @Override
    public int getReplaceIconId(boolean color) {
        return color ? R.drawable.ic_location_arrow_blue : R.drawable.ic_location_arrow_gray;
    }

    @Override
    public int getTextId() {
        return R.id.directionsText;
    }
}
