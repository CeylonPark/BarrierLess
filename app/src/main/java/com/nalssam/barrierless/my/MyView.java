package com.nalssam.barrierless.my;

import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;

public class MyView implements ViewState {
    @Override
    public void onOpen(MainActivity mainActivity) {

    }

    @Override
    public void onClose(MainActivity mainActivity) {

    }

    @Override
    public int getTopBarId() {
        return R.id.myTopBar;
    }

    @Override
    public int getIconId() {
        return R.id.my;
    }

    @Override
    public int getReplaceIconId(boolean color) {
        return color ? R.drawable.ic_my_blue : R.drawable.ic_my_gray;
    }

    @Override
    public int getTextId() {
        return R.id.myText;
    }
}
