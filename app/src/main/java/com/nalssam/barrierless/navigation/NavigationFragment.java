package com.nalssam.barrierless.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.LocationSearchData;
import com.nalssam.barrierless.nearby.LocationSearchActivity;

public class NavigationFragment extends Fragment {
    private MainActivity mainActivity;
    private TextView startPointText;
    private TextView endPointText;

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

        viewGroup.findViewById(R.id.startPointBtn).setOnClickListener(view -> this.onLocationSearchClick(2));
        viewGroup.findViewById(R.id.endPointBtn).setOnClickListener(view -> this.onLocationSearchClick(3));

        this.startPointText = viewGroup.findViewById(R.id.startPointText);
        this.endPointText = viewGroup.findViewById(R.id.endPointText);

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

    public void setLocationPoint(int type, LocationSearchData data) {
        TextView textView;
        if(type == 2) {
            textView = this.startPointText;
        } else if(type == 3){
            textView = this.endPointText;
        } else {
            return;
        }
        textView.setText(data.getTitle());
        textView.setTextColor(getResources().getColor(R.color.black, null));

        //여기서 두개 값이 있다면 길찾기 프레그먼트 실행
    }
}

