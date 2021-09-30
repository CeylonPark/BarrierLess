package com.nalssam.barrierless;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.nalssam.barrierless.community.CommunityFragment;
import com.nalssam.barrierless.location.LocationFragment;
import com.nalssam.barrierless.my.MyFragment;
import com.nalssam.barrierless.navigation.NavigationFragment;

import java.util.Objects;

public class BottomNavigationFragment extends Fragment {
    private static final int NEARBY = 0;
    private static final int NAVIGATION = 1;
    private static final int COMMUNITY = 2;
    private static final int MY = 3;
    private static final int TYPE_SIZE = 4;
    private FragmentActivity activity;
    private FragmentManager fragmentManager;
    private ImageView[] bar;
    private ImageView[] img;
    private TextView[] text;
    private int state = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = this.getActivity();
        this.fragmentManager = this.getParentFragmentManager();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
        this.fragmentManager = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        // Init Button
        Button[] btn = new Button[] {
                viewGroup.findViewById(R.id.nearbyBtn),
                viewGroup.findViewById(R.id.navigationBtn),
                viewGroup.findViewById(R.id.communityBtn),
                viewGroup.findViewById(R.id.myBtn)
        };
        for(int i = 0; i < BottomNavigationFragment.TYPE_SIZE; i++) {
            int finalI = i;
            btn[i].setOnClickListener(v -> onSwitch(finalI));
        }

        // Init Bar, Img, Text
        this.bar = new ImageView[] {
                viewGroup.findViewById(R.id.nearbyBar),
                viewGroup.findViewById(R.id.navigationBar),
                viewGroup.findViewById(R.id.communityBar),
                viewGroup.findViewById(R.id.myBar)
        };

        this.img = new ImageView[] {
                viewGroup.findViewById(R.id.nearbyImg),
                viewGroup.findViewById(R.id.navigationImg),
                viewGroup.findViewById(R.id.communityImg),
                viewGroup.findViewById(R.id.myImg)
        };

        this.text = new TextView[] {
                viewGroup.findViewById(R.id.nearbyText),
                viewGroup.findViewById(R.id.navigationText),
                viewGroup.findViewById(R.id.communityText),
                viewGroup.findViewById(R.id.myText)
        };

        this.onSwitch(BottomNavigationFragment.NEARBY);

        return viewGroup;
    }

    private void onSwitch(int newType) {
        ColorStateList white, gray, blue;
        white = ColorStateList.valueOf(getResources().getColor(R.color.white, null));
        gray = ColorStateList.valueOf(getResources().getColor(R.color.gray, null));
        blue = ColorStateList.valueOf(getResources().getColor(R.color.main_blue, null));

        if(this.state != -1) {
            this.bar[state].setImageTintList(white);
            this.img[state].setImageTintList(gray);
            this.text[state].setTextColor(gray);
            this.fragmentManager.beginTransaction()
                    .remove(Objects.requireNonNull(this.fragmentManager.findFragmentById(R.id.fragmentContainer)))
                    .commit();
        }

        this.state = newType;
        Fragment fragment;
        switch(newType) {
            case BottomNavigationFragment.NEARBY:
                fragment = new LocationFragment();
                break;
            case BottomNavigationFragment.NAVIGATION:
                fragment = new NavigationFragment();
                break;
            case BottomNavigationFragment.COMMUNITY:
                fragment = new CommunityFragment();
                break;
            case BottomNavigationFragment.MY:
                fragment = new MyFragment();
                break;
            default:
                return;
        }
        this.fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        this.bar[newType].setImageTintList(blue);
        this.img[newType].setImageTintList(blue);
        this.text[newType].setTextColor(blue);
    }
}
