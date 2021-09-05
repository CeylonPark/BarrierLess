package com.nalssam.barrierless.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.around.AroundView;
import com.nalssam.barrierless.community.CommunityView;
import com.nalssam.barrierless.directions.DirectionsView;
import com.nalssam.barrierless.my.MyView;

import java.util.Arrays;
import java.util.List;

public class ViewController {
    public static final int AROUND = 0;
    public static final int DIRECTIONS = 1;
    public static final int COMMUNITY = 2;
    public static final int MY = 3;
    private final MainActivity mainActivity;
    private final List<ViewState> views;
    private ViewState viewState;

    public ViewController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.views = Arrays.asList(new AroundView(), new DirectionsView(), new CommunityView(), new MyView());
    }

    public void changeView(int state) {
        //종료
        if(this.viewState != null) {
            this.mainActivity.findViewById(this.viewState.getTopBarId()).setVisibility(View.INVISIBLE);
            ((ImageView) mainActivity.findViewById(this.viewState.getIconId())).setImageResource(this.viewState.getReplaceIconId(false));
            ((TextView) this.mainActivity.findViewById(this.viewState.getTextId())).setTextColor(Color.rgb(119, 119, 119));
            this.viewState.onClose(this.mainActivity);

        }

        //값 변경
        this.viewState = this.views.get(state);

        //시작
        this.viewState.onOpen(this.mainActivity);
        this.mainActivity.findViewById(this.viewState.getTopBarId()).setVisibility(View.VISIBLE);
        ((ImageView) mainActivity.findViewById(this.viewState.getIconId())).setImageResource(this.viewState.getReplaceIconId(true));
        ((TextView) this.mainActivity.findViewById(this.viewState.getTextId())).setTextColor(Color.rgb(32, 136, 255));
    }
}
