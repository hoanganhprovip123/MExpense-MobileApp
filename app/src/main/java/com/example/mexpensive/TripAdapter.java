package com.example.mexpensive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mexpensive.Entities.Trip;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    private Context context;
    private List<Trip> list;

    public TripAdapter(Context context, List<Trip> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_detail_trip,null);
        }
        TextView tvName = convertView.findViewById(R.id.tvTripName);
        TextView tvDest = convertView.findViewById(R.id.tvDestination);
        TextView tvStart = convertView.findViewById(R.id.tvStartDate);

        Trip tr = list.get(position);
        tvName.setText(tr.getTripName());
        tvDest.setText(tr.getDestination());
        tvStart.setText(tr.getStartDate());

        return convertView;
    }
}
