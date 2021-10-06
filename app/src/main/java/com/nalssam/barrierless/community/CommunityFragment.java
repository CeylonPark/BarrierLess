package com.nalssam.barrierless.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.CommunityData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommunityFragment extends Fragment {
    private final List<Marker> markers = new ArrayList<>();
    private CommunityInfoFragment communityInfoFragment;

    @Override
    public void onDetach() {
        super.onDetach();

        for(Marker marker : this.markers) {
            marker.setMap(null);
        }
        this.markers.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);

        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.infoContainer, new CommunityReportFragment()).commit();

        NaverMap map = ((MainActivity) Objects.requireNonNull(this.getActivity())).getNaverMap();

        for(LatLng latLng : CommunityData.getInstance().getUserReportList()) {
            Marker marker = new Marker(latLng);
            marker.setIcon(OverlayImage.fromResource(R.drawable.ping_my));
            marker.setMap(map);
            this.markers.add(marker);

            marker.setOnClickListener(view -> {
                this.showReportInfo(marker);
                return true;
            });
        }

        return viewGroup;
    }

    public void showReportInfo(Marker marker) {
        if(this.communityInfoFragment != null) {
            this.removeReportInfo(false);
        }


        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().remove(Objects.requireNonNull(fm.findFragmentById(R.id.infoContainer))).commit();

        CommunityInfoFragment communityInfoFragment = new CommunityInfoFragment(marker);
        fm.beginTransaction().add(R.id.infoContainer, communityInfoFragment).commit();
        this.communityInfoFragment = communityInfoFragment;

    }

    public void removeReportInfo(boolean buttonVisible) {
        if(this.communityInfoFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().remove(this.communityInfoFragment).commit();

        if(buttonVisible) {
            fm.beginTransaction().add(R.id.infoContainer, new CommunityReportFragment()).commit();
        }
    }
}

