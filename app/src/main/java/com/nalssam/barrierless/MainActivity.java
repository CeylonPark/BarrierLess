package com.nalssam.barrierless;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.community.CommunityFragment;
import com.nalssam.barrierless.data.LocationData;
import com.nalssam.barrierless.navigation.NavigationFragment;
import com.nalssam.barrierless.nearby.NearbyFragment;
import com.naver.maps.map.*;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.LocationButtonView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        MapView mapView = findViewById(R.id.map);
        mapView.getMapAsync(this);

        // locationButton 활성화
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            NaverMapOptions options = new NaverMapOptions().locationButtonEnabled(false);
            mapFragment = MapFragment.newInstance(options);
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(naverMap -> {
            LocationButtonView zoomControlView = findViewById(R.id.mapLocationBtn);
            zoomControlView.setMap(naverMap);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLogoClickEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setScaleBarEnabled(false);

        // footer fragment
        FragmentManager fm = getSupportFragmentManager();
        BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();
        fm.beginTransaction().add(R.id.bottomNavigationContainer, bottomNavigationFragment).commit();

        //네이버 지도 클릭시 인포 프래그먼트 제거
        naverMap.setOnMapClickListener((pointF0, latLng) -> {
            if(bottomNavigationFragment.getState() == 0) {
                NearbyFragment nearbyFragment = (NearbyFragment) fm.findFragmentById(R.id.fragmentContainer);
                assert nearbyFragment != null;
                nearbyFragment.removeBarrierFreeInfo();
                nearbyFragment.removeLocationSearchMarker();
            } else if(bottomNavigationFragment.getState() == 2) {
                CommunityFragment communityFragment = (CommunityFragment) fm.findFragmentById(R.id.fragmentContainer);
                assert communityFragment != null;
                communityFragment.removeReportInfo(true);
            }
        });
    }

    public NaverMap getNaverMap() {
        return this.naverMap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK || requestCode < 1 || requestCode > 3) {
            return;
        }

        FragmentManager fm = getSupportFragmentManager();
        BottomNavigationFragment bnf = (BottomNavigationFragment) fm.findFragmentById(R.id.bottomNavigationContainer);
        if (bnf == null) {
            return;
        }

        LocationData locationData = new LocationData(data.getStringExtra("data"));

        if (requestCode == 1) {
            if(bnf.getState() == 0) {
                NearbyFragment nearbyFragment = (NearbyFragment) fm.findFragmentById(R.id.fragmentContainer);
                assert nearbyFragment != null;
                nearbyFragment.showLocationSearchMarker(locationData);
            }
        } else {
            if(bnf.getState() != 1) {
                return;
            }
            NavigationFragment navigationFragment = (NavigationFragment) fm.findFragmentById(R.id.fragmentContainer);
            assert navigationFragment != null;
            navigationFragment.setLocationPoint(requestCode, locationData);
        }
    }
}
