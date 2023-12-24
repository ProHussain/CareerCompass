package com.hashmac.careercompass.ui.auth.fragments.complete_profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.FragmentCompleteProfileBinding;

public class CompleteProfileFragment extends Fragment {

    private FragmentCompleteProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompleteProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinAdapters();
    }

    private void initSpinAdapters() {
        ArrayAdapter<String> levelsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.levels_of_edu));
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.etLevel.setAdapter(levelsAdapter);
        binding.etLevel.setOnClickListener(v -> binding.etLevel.showDropDown());

        ArrayAdapter<String> fieldsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fields_of_study));
        fieldsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.etField.setAdapter(fieldsAdapter);
        binding.etField.setOnClickListener(v -> binding.etField.showDropDown());
    }
}