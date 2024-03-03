package com.hashmac.careercompass.utils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.hashmac.careercompass.databinding.DialogWaitingBinding;

import java.util.Objects;

public class DialogWaiting extends Dialog {
    private DialogWaitingBinding binding;


    public DialogWaiting(@NonNull Context context) {
        super(context);
        binding = DialogWaitingBinding.inflate(LayoutInflater.from(context));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(binding.getRoot());
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(MATCH_PARENT, WRAP_CONTENT);
    }

    public void showDialog(String message) {
        if (!isShowing()) {
            show();
        }
        binding.tvStatusLoading.setText(message);
    }

    public void hideDialog() {
        if (isShowing()) {
            dismiss();
        }
    }
}
