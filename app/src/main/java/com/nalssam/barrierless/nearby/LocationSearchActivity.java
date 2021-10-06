package com.nalssam.barrierless.nearby;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.api.PlaceSearchService;
import com.nalssam.barrierless.data.LocationSearchData;

import java.util.ArrayList;

public class LocationSearchActivity extends AppCompatActivity {
    private ListView listView;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_location_search);

        this.findViewById(R.id.backBtn).setOnClickListener(view -> finish());

        this.listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent();
            intent.putExtra("data", ((LocationSearchData) locationAdapter.getItem(position)).getAddress());
            setResult(RESULT_OK, intent);
            finish();});

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String jsonString = bundle.getString("data");

                ArrayList<LocationSearchData> locationList = new ArrayList<>();

                JsonElement element = JsonParser.parseString(jsonString);
                JsonArray jsonArray = element.getAsJsonObject().get("items").getAsJsonArray();
                for(int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    locationList.add(new LocationSearchData(jsonObject.get("title").getAsString().replace("<b>", "").replace("</b>", ""), jsonObject.get("address").getAsString()));
                }
                locationAdapter = new LocationAdapter(LocationSearchActivity.this, locationList);
                listView.setAdapter(locationAdapter);
            }
        };

        EditText editText = this.findViewById(R.id.wordInput);
        editText.setOnKeyListener((view, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                new Thread(() -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("data", new PlaceSearchService().searchPlace(editText.getText().toString()));

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }).start();
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        EditText wordInput = findViewById(R.id.wordInput);
        wordInput.requestFocus();
        imm.showSoftInput(wordInput, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(0,0);
    }
}

// new Thread(() -> {new PlaceSearchService().searchPlace("반석역");}).start();

/*
ArrayList<LocationSearchData> locationList = new ArrayList<>();

        JsonElement element = JsonParser.parseString(responseBody);
        JsonArray jsonArray = element.getAsJsonObject().get("items").getAsJsonArray();
        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            locationList.add(new LocationSearchData(jsonObject.get("title").getAsString(), jsonObject.get("address").getAsString()));
        }
 */