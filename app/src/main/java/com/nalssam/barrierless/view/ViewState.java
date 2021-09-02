package com.nalssam.barrierless.view;

import com.nalssam.barrierless.MainActivity;

public interface ViewState {
    void onOpen(MainActivity mainActivity);
    void onClose(MainActivity mainActivity);
    int getTopBarId();
    int getIconId();
    int getReplaceIconId(boolean color);
    int getTextId();
}
