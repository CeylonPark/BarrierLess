package com.nalssam.barrierless.around;

import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;

public class AroundView implements ViewState {

    @Override
    public void onOpen(MainActivity mainActivity) {

    }

    @Override
    public void onClose(MainActivity mainActivity) {

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
