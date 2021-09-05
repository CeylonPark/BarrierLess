package com.nalssam.barrierless.community;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.view.ViewState;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.util.ArrayList;
import java.util.List;

public class CommunityView implements ViewState {
    private static final int ADD = 0;
    private static final int PROBLEM = 1;
    private final List<Marker> markers = new ArrayList<>();

    private void onClickReport(MainActivity mainActivity, int type) {
        Intent intent = new Intent(mainActivity, CommunityActivity.class);
        intent.putExtra("data", "Test Popup");

        mainActivity.startActivity(intent);
    }

    @Override
    public void onOpen(MainActivity mainActivity) {
        //제보 버튼 활성화
        ImageButton reportNewBtn = mainActivity.findViewById(R.id.reportNew);
        reportNewBtn.setVisibility(View.VISIBLE);
        reportNewBtn.setOnClickListener(view -> onClickReport(mainActivity, CommunityView.ADD));

        ImageButton reportProblemBtn = mainActivity.findViewById(R.id.reportProblem);
        reportProblemBtn.setVisibility(View.VISIBLE);
        reportProblemBtn.setOnClickListener(view -> onClickReport(mainActivity, CommunityView.PROBLEM));

        //마커 테스트
        Marker marker = new Marker();
        markers.add(marker);
        marker.setPosition(new LatLng(36.3906994443793, 127.30733252302068));
        marker.setMap(mainActivity.getNaverMap());

        marker.setOnClickListener(l -> {
            ImageView imageView = mainActivity.findViewById(R.id.communityTest);
            if(imageView.getVisibility() == View.GONE) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            return true;
        });

        Marker marker2 = new Marker();
        markers.add(marker2);
        marker2.setPosition(new LatLng(36.39064492429601, 127.30882718744387));
        marker2.setIcon(MarkerIcons.PINK);
        marker2.setMap(mainActivity.getNaverMap());

        marker2.setOnClickListener(l -> {
            ImageView imageView = mainActivity.findViewById(R.id.reviewTest);
            if(imageView.getVisibility() == View.GONE) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            return true;
        });
    }

    @Override
    public void onClose(MainActivity mainActivity) {
        //제보 버튼 비활성화
        mainActivity.findViewById(R.id.reportNew).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.reportProblem).setVisibility(View.GONE);

        ImageView imageView = mainActivity.findViewById(R.id.communityTest);
        if(imageView.getVisibility() == View.VISIBLE) {
            imageView.setVisibility(View.GONE);
        }

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
