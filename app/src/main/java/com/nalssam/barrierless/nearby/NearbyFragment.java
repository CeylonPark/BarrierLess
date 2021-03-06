package com.nalssam.barrierless.nearby;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.LocationData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;

import java.util.*;

public class NearbyFragment extends Fragment {
    private final Set<NearbyBarrierFree> barrierFrees = new HashSet<>();
    private MainActivity mainActivity;
    private NearbyInfoFragment nearbyInfoFragment;
    private Marker searchMarker;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;

        for(NearbyBarrierFree barrierFree : this.barrierFrees) {
            barrierFree.remove();
        }
        this.barrierFrees.clear();
        this.removeLocationSearchMarker();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_nearby, container, false);

        NearbyBarrierFree toilet = new NearbyBarrierFree("TOILET", this, mainActivity.getNaverMap());
        toilet.setIcon(OverlayImage.fromResource(R.drawable.marker_toilet));
        this.barrierFrees.add(toilet);

        NearbyBarrierFree slope = new NearbyBarrierFree("SLOPE", this, mainActivity.getNaverMap());
        slope.setIcon(OverlayImage.fromResource(R.drawable.marker_slope));
        this.barrierFrees.add(slope);

        NearbyBarrierFree elevator = new NearbyBarrierFree("ELEVATOR", this, mainActivity.getNaverMap());
        elevator.setIcon(OverlayImage.fromResource(R.drawable.marker_elevator));
        this.barrierFrees.add(elevator);

        NearbyBarrierFree swingDoor = new NearbyBarrierFree("SWING_DOOR", this, mainActivity.getNaverMap());
        swingDoor.setIcon(OverlayImage.fromResource(R.drawable.marker_swing_door));
        this.barrierFrees.add(swingDoor);

        viewGroup.findViewById(R.id.toiletBtn).setOnClickListener(view -> switchMarker("TOILET"));
        viewGroup.findViewById(R.id.slopeBtn).setOnClickListener(view -> switchMarker("SLOPE"));
        viewGroup.findViewById(R.id.elevatorBtn).setOnClickListener(view -> switchMarker("ELEVATOR"));
        viewGroup.findViewById(R.id.swingDoorBtn).setOnClickListener(view -> switchMarker("SWING_DOOR"));

        viewGroup.findViewById(R.id.locationSearchBar).setOnClickListener(view -> onLocationSearchClick());

        return viewGroup;
    }

    private void switchMarker(String name) {
        NearbyBarrierFree nearbyBarrierFree = null;
        for(NearbyBarrierFree nbf : this.barrierFrees) {
            if(nbf.getName().equals(name)) {
                nearbyBarrierFree = nbf;
            }
        }

        if(nearbyBarrierFree == null) {
            return;
        }

        ImageButton imageButton;
        TextView textView;

        switch(name) {
            case "TOILET":
                imageButton = this.mainActivity.findViewById(R.id.toiletBtn);
                textView = this.mainActivity.findViewById(R.id.toiletText);
                break;
            case "SLOPE":
                imageButton = this.mainActivity.findViewById(R.id.slopeBtn);
                textView = this.mainActivity.findViewById(R.id.slopeText);
                break;
            case "ELEVATOR":
                imageButton = this.mainActivity.findViewById(R.id.elevatorBtn);
                textView = this.mainActivity.findViewById(R.id.elevatorText);
                break;
            case "SWING_DOOR":
                imageButton = this.mainActivity.findViewById(R.id.swingDoorBtn);
                textView = this.mainActivity.findViewById(R.id.swingDoorText);
                break;
            default:
                return;
        }

        ColorStateList white, black;
        white = ColorStateList.valueOf(getResources().getColor(R.color.white, null));
        black = ColorStateList.valueOf(getResources().getColor(R.color.black, null));

        boolean visible = nearbyBarrierFree.isVisible();
        if(visible) {

            imageButton.setBackground(getResources().getDrawable(R.drawable.bg_facilities_filter, null));
            if(!name.equals("TOILET")) {
                imageButton.setImageTintList(black);
            }
            textView.setTextColor(black);
        } else {
            imageButton.setBackground(getResources().getDrawable(R.drawable.bg_facilities_filter_selected, null));
            if(!name.equals("TOILET")) {
                imageButton.setImageTintList(white);
            }
            textView.setTextColor(white);
        }
        nearbyBarrierFree.setVisible(!visible);
    }

    public void showBarrierFreeInfo(String name, Marker marker) {
        if(this.nearbyInfoFragment != null) {
            this.removeBarrierFreeInfo();
        }

        //???????????? ????????????
        if(!name.equals("SLOPE")) {
            return;
        }

        FragmentManager fm = getChildFragmentManager();
        NearbyInfoFragment nearbyInfoFragment = new NearbyInfoFragment(marker);
        fm.beginTransaction().add(R.id.infoContainer, nearbyInfoFragment).commit();
        this.nearbyInfoFragment = nearbyInfoFragment;

    }

    public void removeBarrierFreeInfo() {
        if(this.nearbyInfoFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().remove(this.nearbyInfoFragment).commit();
    }

    private void onLocationSearchClick() {
        Intent intent = new Intent(mainActivity, LocationSearchActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mainActivity.startActivityForResult(intent, 1);
    }

    public void showLocationSearchMarker(LocationData data) {
        if(searchMarker != null) {
            this.removeLocationSearchMarker();
        }
        NaverMap naverMap = mainActivity.getNaverMap();
        LatLng latLng = data.getLatLng();
        Marker marker = new Marker(latLng);
        marker.setIcon(MarkerIcons.GRAY);
        marker.setMap(mainActivity.getNaverMap());
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(latLng);
        naverMap.moveCamera(cameraUpdate);
        this.searchMarker = marker;
    }

    public void removeLocationSearchMarker() {
        if(this.searchMarker == null) {
            return;
        }
        this.searchMarker.setMap(null);
        this.searchMarker = null;
    }
}
