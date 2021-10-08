package com.nalssam.barrierless.navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.api.NCPDirection5;
import com.nalssam.barrierless.data.LocationData;
import com.nalssam.barrierless.nearby.LocationSearchActivity;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

import java.util.ArrayList;
import java.util.List;

public class NavigationFragment extends Fragment {
    private MainActivity mainActivity;
    private TextView[] pointText = new TextView[2];
    private LocationData[] data = new LocationData[2];
    private Marker[] marker = new Marker[2];
    private NavigationInfoFragment navigationInfoFragment;
    private PathOverlay pathOverlay;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;
        this.removePointMarker();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_navigation, container, false);

        viewGroup.findViewById(R.id.startPointBtn).setOnClickListener(view -> this.onLocationSearchClick(2));
        viewGroup.findViewById(R.id.endPointBtn).setOnClickListener(view -> this.onLocationSearchClick(3));

        this.pointText[0] = viewGroup.findViewById(R.id.startPointText);
        this.pointText[1] = viewGroup.findViewById(R.id.endPointText);

        return viewGroup;
    }

    private void onLocationSearchClick(int type) {
        if(type != 2 && type != 3) {
            return;
        }
        Intent intent = new Intent(mainActivity, LocationSearchActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mainActivity.startActivityForResult(intent, type);
    }

    public void setLocationPoint(int type, LocationData data) {
        final int index = type - 2;
        TextView textView = this.pointText[index];
        this.data[index] = data;
        textView.setText(data.getTitle());
        textView.setTextColor(getResources().getColor(R.color.black, null));

        if(this.data[0] == null || this.data[1] == null) {
            return;
        }
        this.removePointMarker();

        for(int i = 0; i < this.data.length; i++) {
            Marker marker = new Marker(this.data[i].getLatLng());
            marker.setIcon(i == 0 ? OverlayImage.fromResource(R.drawable.ping_start) : OverlayImage.fromResource(R.drawable.ping_end));
            marker.setMap(mainActivity.getNaverMap());
            this.marker[i] = marker;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                JsonArray jsonArray = JsonParser.parseString(msg.getData().getString("data")).getAsJsonObject()
                        .get("route").getAsJsonObject()
                        .get("traoptimal").getAsJsonArray()
                        .get(0).getAsJsonObject()
                        .get("path").getAsJsonArray();

                List<LatLng> coords = new ArrayList<>();

                for(int i = 0; i < jsonArray.size(); i++) {
                    JsonArray jsonDoubles = jsonArray.get(i).getAsJsonArray();
                    coords.add(new LatLng(jsonDoubles.get(1).getAsDouble(), jsonDoubles.get(0).getAsDouble()));
                }

                pathOverlay = new PathOverlay();
                pathOverlay.setCoords(coords);
                pathOverlay.setMap(mainActivity.getNaverMap());
                pathOverlay.setColor(Color.rgb(32, 136, 255));
                pathOverlay.setOutlineColor(Color.rgb(32, 136, 255));
            }
        };
        new Thread(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("data", new NCPDirection5().getDirection(this.data[0].getLatLng(), this.data[1].getLatLng()));

            Message msg = handler.obtainMessage();
            msg.setData(bundle);
            handler.sendMessage(msg);
        }).start();
    }

    public void removePointMarker() {
        if(this.marker[0] == null) {
            return;
        }
        this.marker[0].setMap(null);
        this.marker[1].setMap(null);
        this.marker[0] = null;
        this.marker[1] = null;
        if(pathOverlay != null) {
            pathOverlay.setMap(null);
        }
    }
}

