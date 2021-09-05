package com.nalssam.barrierless;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FooterFragment extends Fragment {
    public static final int LOCATION = 0;
    public static final int NAVIGATION = 1;
    public static final int COMMUNITY = 2;
    public static final int MY = 3;

    private MainActivity mainActivity;
    private FragmentManager fragmentManager;
    private FooterElementFragment elementFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) this.getActivity();
        assert this.mainActivity != null;
        this.fragmentManager = this.mainActivity.getSupportFragmentManager();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;
        this.fragmentManager = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_footer, container, false);

        // 주변
        FooterElementFragment locationElementFragment = new FooterElementFragment(FooterFragment.LOCATION, this);
        this.fragmentManager.beginTransaction().add(R.id.locationContainer,
                locationElementFragment).runOnCommit(() -> this.elementFragment.onSwitch()).commit();
        this.elementFragment = locationElementFragment;

        // 길찾기
        this.fragmentManager.beginTransaction().add(R.id.navigationContainer,
                new FooterElementFragment(FooterFragment.NAVIGATION, this)).commit();

        // 커뮤니티 매핑
        this.fragmentManager.beginTransaction().add(R.id.communityContainer,
                new FooterElementFragment(FooterFragment.COMMUNITY, this)).commit();

        // MY
        this.fragmentManager.beginTransaction().add(R.id.myContainer,
                new FooterElementFragment(FooterFragment.MY, this)).commit();

        return viewGroup;
    }

    public void switchFragment(FooterElementFragment elementFragment) {
        if(elementFragment.getType() != this.elementFragment.getType()) {
            this.elementFragment.onSwitch();
            elementFragment.onSwitch();
            this.elementFragment = elementFragment;
        }
    }
}

