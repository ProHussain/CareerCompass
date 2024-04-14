package com.hashmac.careercompass.ui.main.fragments.notifications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hashmac.careercompass.R;
import com.hashmac.careercompass.beans.Notification;
import com.hashmac.careercompass.databinding.FragmentNotificationBinding;
import com.hashmac.careercompass.ui.main.fragments.notifications.adapter.NotificationsAdapter;

import timber.log.Timber;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private NotificationViewModel viewModel;
    private NotificationsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        initAdapter();
        initObserver();
    }

    private void initAdapter() {
        adapter = new NotificationsAdapter(this::initNotificationClick);
        binding.rvNotifications.setAdapter(adapter);
    }

    private void initNotificationClick(Notification notification) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(notification.getTitle());
        builder.setMessage(notification.getMessage());
        builder.setPositiveButton("OK", (dialog, which) -> {

        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initObserver() {
        viewModel.getNotifications();
        viewModel.notificationMutableLiveData.observe(getViewLifecycleOwner(), notifications -> {
            adapter.setNotifications(notifications);
        });
    }

}