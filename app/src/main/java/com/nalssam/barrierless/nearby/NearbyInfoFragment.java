package com.nalssam.barrierless.nearby;

import android.content.Context;
import android.content.res.ColorStateList;
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

public class NearbyInfoFragment extends Fragment {
    private final Marker marker;

    public NearbyInfoFragment(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.marker.setIcon(OverlayImage.fromResource(R.drawable.ping_slope));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.marker.setIcon(OverlayImage.fromResource(R.drawable.marker_slope));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_nearby_info, container, false);

        ((ImageView) viewGroup.findViewById(R.id.barrierFreeImage)).setImageResource(R.drawable.img_slope);


        TextView textView = viewGroup.findViewById(R.id.barrierFreeTypeText);
        textView.setTextColor(getResources().getColor(R.color.orange, null));
        textView.setText("경사로");

        ((TextView) viewGroup.findViewById(R.id.barrierFreeAdrText)).setText("대전광역시 서구 도마동 175-23");
        ((TextView) viewGroup.findViewById(R.id.aText)).setText("4.5");
        ((TextView) viewGroup.findViewById(R.id.bText)).setText("4.1");
        ((TextView) viewGroup.findViewById(R.id.cText)).setText("4.8");

        return viewGroup;
    }
}
