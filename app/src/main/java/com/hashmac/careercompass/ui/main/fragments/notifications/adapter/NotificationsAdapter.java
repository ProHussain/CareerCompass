package com.hashmac.careercompass.ui.main.fragments.notifications.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmac.careercompass.beans.Notification;
import com.hashmac.careercompass.databinding.ItemNotificationLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private List<Notification> notifications = new ArrayList<>();
    private final NotificationClickListener listener;

    public NotificationsAdapter(NotificationClickListener listener) {
        this.listener = listener;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationLayoutBinding binding = ItemNotificationLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        holder.bind(notifications.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemNotificationLayoutBinding binding;
        public ViewHolder(@NonNull ItemNotificationLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Notification notification, NotificationClickListener listener) {
            binding.tvNotificationTitle.setText(notification.getTitle());
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onNotificationClick(notification);
                }
            });
        }
    }

    public interface NotificationClickListener {
        void onNotificationClick(Notification notification);
    }
}
