package com.nalssam.barrierless;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.view.ViewController;

public class FooterFragment extends Fragment {
    private final ViewController viewController;
    private MainActivity mainActivity;

    public FooterFragment(ViewController viewController) {
        this.viewController = viewController;
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_footer, container, false);

        Button aroundBtn = viewGroup.findViewById(R.id.aroundBtn);
        aroundBtn.setOnClickListener(view -> this.viewController.changeView(ViewController.AROUND));

        Button directions = viewGroup.findViewById(R.id.directionsBtn);
        directions.setOnClickListener(view -> this.viewController.changeView(ViewController.DIRECTIONS));

        Button communityBtn = viewGroup.findViewById(R.id.communityBtn);
        communityBtn.setOnClickListener(view -> this.viewController.changeView(ViewController.COMMUNITY));

        Button myBtn = viewGroup.findViewById(R.id.myBtn);
        myBtn.setOnClickListener(view -> this.viewController.changeView(ViewController.MY));

        return viewGroup;
    }
}
