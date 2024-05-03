package com.hashmac.careercompass.ui.main.fragments.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.databinding.FragmentHomeBinding;
import com.hashmac.careercompass.ui.result.ResultActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initClickListeners();
    }

    private void initClickListeners() {
        binding.btnJoinNow.setOnClickListener(v -> joinNow());
        binding.cardAndroid.setOnClickListener(v -> openResult("Android App Development"));
        binding.cardIOS.setOnClickListener(v -> openResult("iOS App Development"));
        binding.cardWeb.setOnClickListener(v -> openResult("Web Development"));
        binding.cardDropshipping.setOnClickListener(v -> openResult("Drop shipping"));
        binding.cardPrintOnDemand.setOnClickListener(v -> openResult("Print on Demand"));
        binding.cardCustomCake.setOnClickListener(v -> openResult("Custom Cake Making"));
        binding.cardFreelanceWriter.setOnClickListener(v -> openResult("Freelance Writer"));
        binding.cardVirtualAssistant.setOnClickListener(v -> openResult("Virtual Assistant"));
        binding.cardPhotographer.setOnClickListener(v -> openResult("Photographer"));
        binding.cardCattleFarm.setOnClickListener(v -> openResult("Cattle & Poultry Farming"));
        binding.cardFruitFarm.setOnClickListener(v -> openResult("Fruit & Vegetable Farming"));
    }

    private void openResult(String title) {
        Intent intent = new Intent(requireActivity(), ResultActivity.class);
        intent.putExtra("from", "home");
        intent.putExtra("prompt", title);
        startActivity(intent);
    }

    private void joinNow() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://topmate.io/pro_hussain"));
        startActivity(intent);
    }
}
