package com.amikom.thesaint2;

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

public class NextArrayAdapter extends ArrayAdapter{
    private List<NextDetails> nextDetailsList;
    private int resource;
    private Context context;

    public NextArrayAdapter(Context context, int resource, List<NextDetails> nextDetails) {
        super(context, resource, nextDetails);
        this.context = context;
        this.nextDetailsList = nextDetails;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NextDetails details = nextDetailsList.get(position);

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        TextView movieName = (TextView) view.findViewById(R.id.tv1);
        TextView movieOver = (TextView) view.findViewById(R.id.tv2);
        TextView movieRelease = (TextView) view.findViewById(R.id.tv3);
        ImageView image = (ImageView) view.findViewById(R.id.iv1);

        movieName.setText(details.getOriginal_title());
        movieOver.setText(details.getOverview());
        movieRelease.setText(details.getRelease_date());


        Glide.with(context).load(details.getPoster_path()).into(image);

        return view;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return nextDetailsList.get(position);
    }

}
