package com.hashmac.careercompass.ui.main.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hashmac.careercompass.R;
import com.hashmac.careercompass.databinding.ActivityMainBinding;
import com.hashmac.careercompass.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);

    }
}