package com.nalssam.barrierless.location;

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
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;

import java.util.*;

public class LocationFragment extends Fragment {
    private final List<Marker> markerList = new ArrayList<>();
    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();

        Map<LatLng, OverlayImage> latLngMap = new HashMap<>();
        latLngMap.put(new LatLng(36.39064492429601, 127.30882718744387), MarkerIcons.PINK);
        latLngMap.put(new LatLng(36.392283977477604, 127.31413960157613), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.39105547472043, 127.31079942407383), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.38981095276135, 127.3124650615602), MarkerIcons.YELLOW);
        latLngMap.put(new LatLng(36.39003103856445, 127.31374851856948), MarkerIcons.BLUE);
        latLngMap.put(new LatLng(36.39256165123829, 127.3115900094301), MarkerIcons.LIGHTBLUE);
        for(LatLng latLng : latLngMap.keySet()) {
            Marker marker = new Marker();
            marker.setPosition(latLng);
            marker.setIcon(Objects.requireNonNull(latLngMap.get(latLng)));
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
        return inflater.inflate(R.layout.fragment_location, container, false);
    }
}
