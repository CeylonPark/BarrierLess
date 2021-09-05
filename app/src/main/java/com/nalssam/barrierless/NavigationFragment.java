package com.nalssam.barrierless;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Arrays;
import java.util.Objects;

public class NavigationFragment extends Fragment {
    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_navigation, container, false);

        Button navigationBtn = viewGroup.findViewById(R.id.navigationBtn);
        navigationBtn.setOnClickListener(l -> {
            FragmentManager fragmentManager = this.mainActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(this).commit();
            fragmentManager.beginTransaction().remove(Objects.requireNonNull(fragmentManager.findFragmentById(R.id.footerContainer))).commit();

            fragmentManager.beginTransaction().add(R.id.testContainer, new TestFragment()).commit();
        });

        return viewGroup;
    }
}

