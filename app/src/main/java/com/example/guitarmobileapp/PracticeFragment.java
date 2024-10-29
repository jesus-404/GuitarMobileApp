package com.example.guitarmobileapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class PracticeFragment extends Fragment {

    public PracticeFragment() {}

    private ProgressBar practiceProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);

        PracticeList[] practiceList = new PracticeList[] {
                new PracticeList("One Minute Changes", R.drawable.icon_alarm),
                new PracticeList("Perfect Chords", R.drawable.icon_bulls_eye),
                new PracticeList("Chord Builder", R.drawable.icon_wrench),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.practice_recycler_view);
        PracticeListAdapter adapter = new PracticeListAdapter(practiceList, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            Window window = getActivity().getWindow();
            window.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color_accent_s));
            window.getDecorView().setSystemUiVisibility(0);
        }

        TextView profileName = view.findViewById(R.id.practice_progress_time);
        practiceProgressBar = view.findViewById(R.id.practice_progress_bar);
        profileName.setText(getTimePracticed());

        return view;
    }

    private String getTimePracticed() {
        String sharedPrefFile = "com.example.android.sharedprefs";
        String key = "timePracticed";

        SharedPreferences preferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        int timeSpent = preferences.getInt(key, 0);

        int min = timeSpent / 60;
        int sec = timeSpent % 60;
        updateProgressBar(min);

        return String.format("%d:%02d", min, sec);
    }

    private void updateProgressBar(int min) {
        practiceProgressBar.setProgress(Math.min(min, 60));
    }
}