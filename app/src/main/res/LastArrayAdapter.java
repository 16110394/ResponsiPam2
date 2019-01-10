package com.amikom.thesaint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LastArrayAdapter extends ArrayAdapter{
    private List<LastDetails> lastDetailsList;
    private int resource;
    private Context context;

    public LastArrayAdapter(Context context, int resource, List<LastDetails> lastDetails) {
        super(context, resource, lastDetails);
        this.context = context;
        this.lastDetailsList = lastDetails;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LastDetails details = lastDetailsList.get(position);

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView movieName = (TextView) view.findViewById(R.id.tv1);
        TextView movieOver = (TextView) view.findViewById(R.id.tv2);
        TextView movieRelease = (TextView) view.findViewById(R.id.tv3);
        TextView movieRate = (TextView) view.findViewById(R.id.tv4);
        TextView movieAway = (TextView) view.findViewById(R.id.tv5);
        ImageView image = (ImageView) view.findViewById(R.id.iv1);

        movieName.setText(details.getOriginal_title());
        movieOver.setText(details.getOverview());
        movieRelease.setText(details.getRelease_date());
        movieRate.setText(details.getVote_average());
        movieAway.setText(details.getGol_away());

        Glide.with(context).load(details.getPoster_path()).into(image);

        return view;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return lastDetailsList.get(position);
    }
}
