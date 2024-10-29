package com.example.guitarmobileapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileList[] profileList = new ProfileList[] {
                new ProfileList("D to A", "0"),
                new ProfileList("A to E", "0"),
                new ProfileList("E to D", "0"),
        };

        RecyclerView recyclerView = view.findViewById(R.id.practice_recycler_view);
        ProfileListAdapter adapter = new ProfileListAdapter(profileList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        if (getActivity() != null) {
            Window window = getActivity().getWindow();
            window.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color_accent_t));
            window.getDecorView().setSystemUiVisibility(0);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView profileName = view.findViewById(R.id.profile_text_name);
        profileName.setText(getProfileName());

        ImageButton editBtn = view.findViewById(R.id.profile_edit_text_name);
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            intent.putExtra("key", "Edit Account");
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            requireActivity().finish();
        });
    }

    private String getProfileName() {
        String sharedPrefFile = "com.example.android.sharedprefs";
        String key = "profileName";

        SharedPreferences preferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        return preferences.getString(key, "Default Name");
    }
}