package com.nalssam.barrierless.nearby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nalssam.barrierless.R;
import com.nalssam.barrierless.data.LocationData;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<LocationData> data;

    public LocationAdapter(Context context, ArrayList<LocationData> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.layoutInflater.inflate(R.layout.listview_location, null);

        TextView locationName = view.findViewById(R.id.locationName);
        TextView locationAddress = view.findViewById(R.id.locationAddress);

        locationName.setText(this.data.get(position).getTitle());
        locationAddress.setText(this.data.get(position).getAddress());

        return view;
    }
}
