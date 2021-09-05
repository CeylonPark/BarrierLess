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
import androidx.fragment.app.FragmentTransaction;
import com.nalssam.barrierless.community.CommunityFragment;
import com.nalssam.barrierless.location.LocationFragment;
import com.nalssam.barrierless.my.MyFragment;
import com.nalssam.barrierless.navigation.NavigationFragment;

public class FooterElementFragment extends Fragment {
    private final int type;
    private final FooterFragment footerFragment;
    private Fragment fragment;
    private MainActivity mainActivity;
    private ImageView topBar;
    private ImageView elementImg;
    private TextView elementText;
    private boolean state;

    public FooterElementFragment(int type, FooterFragment footerFragment) {
        this.type = type;
        this.footerFragment = footerFragment;
        fragment = null;
    }

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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_footer_element, container, false);
        this.topBar = viewGroup.findViewById(R.id.topBar);
        this.elementImg = viewGroup.findViewById(R.id.elementImg);
        this.elementText = viewGroup.findViewById(R.id.elementText);
        this.state = false;

        if(type == FooterFragment.LOCATION) {
            this.elementImg.setImageResource(R.drawable.ic_location);
            this.elementText.setText("주변");
        } else if(type == FooterFragment.NAVIGATION) {
            this.elementImg.setImageResource(R.drawable.ic_location_arrow);
            this.elementText.setText("길찾기");
        } else if(type == FooterFragment.COMMUNITY) {
            this.elementImg.setImageResource(R.drawable.ic_community);
            this.elementText.setText("커뮤니티 매핑");
        } else if(type == FooterFragment.MY) {
            this.elementImg.setImageResource(R.drawable.ic_profile);
            this.elementText.setText("MY");
        }

        Button elementBtn = viewGroup.findViewById(R.id.elementBtn);
        elementBtn.setOnClickListener(view -> this.footerFragment.switchFragment(this));
        return viewGroup;
    }

    public void onSwitch() {
        // change footer element color
        ColorStateList topBarColor, elementColor;
        if(state) {
            topBarColor = ColorStateList.valueOf(getResources().getColor(R.color.white, null));
            elementColor = ColorStateList.valueOf(getResources().getColor(R.color.gray, null));
        } else {
            topBarColor = ColorStateList.valueOf(getResources().getColor(R.color.main_blue, null));
            elementColor = ColorStateList.valueOf(getResources().getColor(R.color.main_blue, null));
        }
        this.topBar.setImageTintList(topBarColor);
        this.elementImg.setImageTintList(elementColor);
        this.elementText.setTextColor(elementColor);

        // fragment work
        FragmentTransaction fragmentTransaction = this.mainActivity.getSupportFragmentManager().beginTransaction();
        if(this.fragment != null) {
            fragmentTransaction.remove(this.fragment).commit();
            this.fragment = null;
        } else {
            if (type == FooterFragment.LOCATION) {
                this.fragment = new LocationFragment();
            } else if (type == FooterFragment.NAVIGATION) {
                this.fragment = new NavigationFragment();
            } else if (type == FooterFragment.COMMUNITY) {
                this.fragment = new CommunityFragment();
            } else if (type == FooterFragment.MY) {
                this.fragment = new MyFragment();
            }
            assert this.fragment != null;
            fragmentTransaction.add(R.id.fragmentContainer, this.fragment).commit();
        }

        this.state = !this.state;
    }

    public int getType() {
        return this.type;
    }
}
