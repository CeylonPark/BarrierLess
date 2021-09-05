package com.nalssam.barrierless;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.view.ViewController;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Arrays;

public class TestFragment extends Fragment {
    private MainActivity mainActivity;

    private PathOverlay path;
    private Marker marker;
    private Marker marker2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();

        path = new PathOverlay();
        path.setCoords(Arrays.asList(
                new LatLng(36.390044663947684, 127.31051577688491),
                new LatLng(36.39002319654331, 127.31027819206219),
                new LatLng(36.39071210211249, 127.31002121256273),
                new LatLng(36.3909892238756, 127.31079942407383),
                new LatLng(36.391176573247826, 127.31076548338072),
                new LatLng(36.3913240366052, 127.31160351531885),
                new LatLng(36.3913240366052, 127.31160351531885),
                new LatLng(36.39150549608154, 127.3115277734294),
                new LatLng(36.39208354524984, 127.31416001075131),
                new LatLng(36.392283977477604, 127.31413960157613)
        ));
        path.setColor(Color.rgb(32, 136, 255));
        path.setOutlineColor(Color.rgb(32, 136, 255));
        path.setPassedColor(Color.GRAY);
        path.setPassedOutlineColor(Color.GRAY);

        marker = new Marker();
        marker.setPosition(new LatLng(36.392283977477604, 127.31413960157613));
        marker.setIcon(MarkerIcons.BLUE);

        marker2 = new Marker();
        marker2.setPosition(new LatLng(36.39105547472043, 127.31079942407383));
        marker2.setIcon(MarkerIcons.RED);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            path.setMap(mainActivity.getNaverMap());
            marker.setMap(mainActivity.getNaverMap());
            marker2.setMap(mainActivity.getNaverMap());
        }, 3000);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;

        path.setMap(null);
        marker.setMap(null);
        marker2.setMap(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);

        Button backBtn = viewGroup.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(l -> {
            FragmentManager fragmentManager = this.mainActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(this).commit();
            fragmentManager.beginTransaction().add(R.id.footerContainer, new FooterFragment(new ViewController(mainActivity))).commit();
        });
        return viewGroup;
    }
}