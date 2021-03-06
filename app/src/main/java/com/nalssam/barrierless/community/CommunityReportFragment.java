package com.nalssam.barrierless.community;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nalssam.barrierless.MainActivity;
import com.nalssam.barrierless.R;

public class CommunityReportFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_community_report, container, false);

        viewGroup.findViewById(R.id.reportBtn).setOnClickListener(view -> this.onReportClick());
        viewGroup.findViewById(R.id.complaintBtn).setOnClickListener(view -> this.onComplaintClick());

        return viewGroup;
    }

    private void onReportClick() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity == null) {
            return;
        }
        Intent intent = new Intent(mainActivity, CommunityReportActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mainActivity.startActivity(intent);
    }

    private void onComplaintClick() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.epeople.go.kr/nep/crtf/userLogn.npaid?returnUrl=%2Fnep%2Fpttn%2FgnrlPttn%2FPttnRqstWrtnInfo.paid"));
        mainActivity.startActivity(intent);
    }
}
