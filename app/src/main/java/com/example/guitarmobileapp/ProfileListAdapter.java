package com.example.guitarmobileapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder>{
    private final ProfileList[] listdata;

    public ProfileListAdapter(ProfileList[] listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_profile_data_item, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textChanges.setText(listdata[position].getDescription());
        holder.textView.setText(listdata[position].getChanges());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textChanges;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textChanges = itemView.findViewById(R.id.profile_changes);
            this.textView = itemView.findViewById(R.id.profile_text);
            relativeLayout = itemView.findViewById(R.id.profile_relative_layout);
        }
    }
}

