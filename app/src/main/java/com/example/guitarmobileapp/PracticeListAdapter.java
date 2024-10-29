package com.example.guitarmobileapp;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeListAdapter extends RecyclerView.Adapter<PracticeListAdapter.ViewHolder>{
    private final PracticeList[] listdata;
    private Context context;

    public PracticeListAdapter(PracticeList[] listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_practice_item, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PracticeList myListData = listdata[position];
        holder.textView.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(listdata[position].getImgId());
        // Toast
        holder.relativeLayout.setOnClickListener(view -> {
            Intent intent = null;

            if (myListData.getDescription().equalsIgnoreCase("One Minute Changes")) {
                intent = new Intent(context, OneMinChangeActivity.class);
            } else if (myListData.getDescription().equalsIgnoreCase("Perfect Chords")) {
                intent = new Intent(context, PerfectChordsActivity.class);
            } else if (myListData.getDescription().equalsIgnoreCase("Chord Builder")) {
                intent = new Intent(context, ChordBuilderActivity.class);
            }

            if (intent != null) {
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.practice_icons);
            this.textView = itemView.findViewById(R.id.practice_text);
            relativeLayout = itemView.findViewById(R.id.practice_relative_layout);
        }
    }
}

