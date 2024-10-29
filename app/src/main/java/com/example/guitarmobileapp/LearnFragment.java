package com.example.guitarmobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LearnFragment extends Fragment {

    public LearnFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        LearnList[] learnList = new LearnList[] {
                new LearnList("Unit 1: ", "Major Chords", false),
                new LearnList("Unit 2: ", "Minor Chords", true),
                new LearnList("Unit 3: ", "Barre Chords", false),
        };

        RecyclerView recyclerView = view.findViewById(R.id.learn_recycler_view);
        LearnListAdapter adapter = new LearnListAdapter(learnList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            Window window = getActivity().getWindow();
            window.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color_primary));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        return view;
    }
}