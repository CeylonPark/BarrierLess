package com.nalssam.barrierless.community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends Fragment {
    private final List<Marker> markers = new ArrayList<>();
    private MainActivity mainActivity;

    private void onClickReport() {
        Intent intent = new Intent(mainActivity, CommunityActivity.class);
        intent.putExtra("data", "Test Popup");

        mainActivity.startActivity(intent);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();

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
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;

        for(Marker marker : this.markers) {
            marker.setMap(null);
        }
        this.markers.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);

        //제보 버튼 활성화
        viewGroup.findViewById(R.id.reportNew).setOnClickListener(view -> onClickReport());
        viewGroup.findViewById(R.id.reportProblem).setOnClickListener(view -> onClickReport());

        return viewGroup;
    }
}

