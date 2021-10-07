package com.nalssam.barrierless.navigation;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.nalssam.barrierless.R;

public class TestActivity extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_test);

        this.imageButton = findViewById(R.id.test);
        this.imageButton.setOnClickListener(view -> changeView());
    }

    public void changeView() {
        this.imageButton.setImageDrawable(getDrawable(R.drawable.test_two));
    }
}
