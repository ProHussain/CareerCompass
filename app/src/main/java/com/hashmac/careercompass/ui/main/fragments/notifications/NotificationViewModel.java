package com.hashmac.careercompass.ui.main.fragments.notifications;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hashmac.careercompass.beans.Notification;

import java.util.List;

public class NotificationViewModel extends ViewModel {
    MutableLiveData<List<Notification>> notificationMutableLiveData = new MutableLiveData<>();

    public void getNotifications() {
        FirebaseFirestore.getInstance().collection("notifications")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Notification> notifications = task.getResult().toObjects(Notification.class);
                        notificationMutableLiveData.setValue(notifications);
                    }
                });
    }
}
