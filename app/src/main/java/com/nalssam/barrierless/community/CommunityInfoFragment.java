package com.nalssam.barrierless.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.R;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

public class CommunityInfoFragment extends Fragment {
    private final Marker marker;

    public CommunityInfoFragment(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.marker.setIcon(OverlayImage.fromResource(R.drawable.ping_my_click));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.marker.setIcon(OverlayImage.fromResource(R.drawable.ping_my));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_community_info, container, false);

        ((ImageView) viewGroup.findViewById(R.id.barrierFreeImg)).setImageResource(R.drawable.img_slope);
        ((TextView) viewGroup.findViewById(R.id.barrierFreeAdrText)).setText("대전광역시 서구 도마동 175-23");

        return viewGroup;
    }
}
