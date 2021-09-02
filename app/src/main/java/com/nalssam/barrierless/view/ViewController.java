package com.nalssam.barrierless.view;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.around.AroundView;
import com.nalssam.barrierless.community.CommunityView;
import com.nalssam.barrierless.directions.DirectionsView;
import com.nalssam.barrierless.my.MyView;

import java.util.Arrays;
import java.util.List;

public class ViewController {
    private static final int AROUND = 0;
    private static final int DIRECTIONS = 1;
    private static final int COMMUNITY = 2;
    private static final int MY = 3;
    private final MainActivity mainActivity;
    private ViewState viewState;
    private List<ViewState> views;

    public ViewController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.viewState = new AroundView();
        this.views = Arrays.asList(new AroundView(), new DirectionsView(), new CommunityView(), new MyView());

        Button aroundBtn = mainActivity.findViewById(R.id.aroundBtn);
        aroundBtn.setOnClickListener(view -> changeView(ViewController.AROUND));

        Button directions = mainActivity.findViewById(R.id.directionsBtn);
        directions.setOnClickListener(view -> changeView(ViewController.DIRECTIONS));

        Button communityBtn = mainActivity.findViewById(R.id.communityBtn);
        communityBtn.setOnClickListener(view -> changeView(ViewController.COMMUNITY));

        Button myBtn = mainActivity.findViewById(R.id.myBtn);
        myBtn.setOnClickListener(view -> changeView(ViewController.MY));
    }

    private void changeView(int state) {
        //종료
        this.mainActivity.findViewById(this.viewState.getTopBarId()).setVisibility(View.INVISIBLE);
        ((ImageView) mainActivity.findViewById(this.viewState.getIconId())).setImageResource(this.viewState.getReplaceIconId(false));
        ((TextView) this.mainActivity.findViewById(this.viewState.getTextId())).setTextColor(Color.rgb(119, 119, 119));
        this.viewState.onClose(this.mainActivity);

        //값 변경
        this.viewState = this.views.get(state);

        //시작
        this.viewState.onOpen(this.mainActivity);
        this.mainActivity.findViewById(this.viewState.getTopBarId()).setVisibility(View.VISIBLE);
        ((ImageView) mainActivity.findViewById(this.viewState.getIconId())).setImageResource(this.viewState.getReplaceIconId(true));
        ((TextView) this.mainActivity.findViewById(this.viewState.getTextId())).setTextColor(Color.rgb(32, 136, 255));
    }
}
