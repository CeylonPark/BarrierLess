package com.nalssam.barrierless.my;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;

public class MyFragment extends Fragment {
    private MainActivity mainActivity;
    private ImageView myTest;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainActivity = null;

        this.myTest.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_my, container, false);

        this.myTest = viewGroup.findViewById(R.id.myTest);
        this.myTest.setVisibility(View.VISIBLE);

        return viewGroup;
    }
}
