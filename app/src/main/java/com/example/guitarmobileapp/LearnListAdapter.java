package com.example.guitarmobileapp;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LearnListAdapter extends RecyclerView.Adapter<LearnListAdapter.ViewHolder>{
    private final LearnList[] listdata;

    public LearnListAdapter(LearnList[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public int getItemViewType(int position) {
        if (listdata[position].getMirror()) {
            return 1;
        } else {
            return 0;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItem;
        if (viewType == 1) {
            listItem = layoutInflater.inflate(R.layout.layout_learn_right, parent, false);
        } else {
            listItem = layoutInflater.inflate(R.layout.layout_learn_left, parent, false);
        }

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LearnList myListData = listdata[position];
        holder.titleTextView.setText(listdata[position].getTitle());
        holder.descTextView.setText(listdata[position].getDescription());

        for (int i = 0; i < drawableIds.length; i++) {
            String colorHex;

            // i must be odd
            if (position < 0 && i <= 1) {
                colorHex = (i % 2 != 0) ? "#FFA500" : "#D18700";
            } else {
                colorHex = (i % 2 != 0) ? "#D9D9D9" : "#B1B1B1";
            }
            ImageView imageView = holder.itemView.findViewById(drawableIds[i]);
            setDrawableColor(imageView, R.drawable.shape_oval, colorHex);
        }
    }

    private void setDrawableColor(ImageView imageView, int drawableId, String colorHex) {
        Drawable drawable = ContextCompat.getDrawable(imageView.getContext(), drawableId);

        if (drawable != null) {
            Drawable drawableCopy = drawable.mutate();
            int fillColor = Color.parseColor(colorHex);
            drawableCopy.setTint(fillColor);
            imageView.setImageDrawable(drawableCopy);
        }
    }

    private final int[] drawableIds = {
            R.id.oval1_1,
            R.id.oval2_1,
            R.id.oval1_2,
            R.id.oval2_2,
            R.id.oval1_3,
            R.id.oval2_3,
            R.id.oval1_4,
            R.id.oval2_4,
    };

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descTextView;
        public TextView titleTextView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.descTextView = itemView.findViewById(R.id.learn_desc);
            this.titleTextView = itemView.findViewById(R.id.learn_title);
            relativeLayout = itemView.findViewById(R.id.learn_relative_layout);
        }
    }
}