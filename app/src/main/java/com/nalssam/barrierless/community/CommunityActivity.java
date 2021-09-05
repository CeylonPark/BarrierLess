package com.nalssam.barrierless.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import com.nalssam.barrierless.R;

public class CommunityActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_report);

        ImageButton cancelBtn = findViewById(R.id.reportCancelBtn);
        cancelBtn.setOnClickListener(view -> finish());
    }
}
