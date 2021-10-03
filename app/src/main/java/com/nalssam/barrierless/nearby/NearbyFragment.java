package com.nalssam.barrierless.nearby;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.NearbyData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.util.*;

public class NearbyFragment extends Fragment {
    private final List<Marker> markerList = new ArrayList<>();
    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();

        Map<LatLng, NearbyData.BarrierFreeType> facilities = new NearbyData().getBarrierFree(NearbyData.BarrierFreeType.TOILET);
        for(LatLng latLng : facilities.keySet()) {
            Marker marker = new Marker();
            marker.setPosition(latLng);
            marker.setIcon(OverlayImage.fromResource(R.drawable.marker_toilet));
            markerList.add(marker);
        }

        for(Marker marker : this.markerList) {
            marker.setMap(mainActivity.getNaverMap());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;

        for(Marker marker : this.markerList) {
            marker.setMap(null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearby, container, false);
    }
}
