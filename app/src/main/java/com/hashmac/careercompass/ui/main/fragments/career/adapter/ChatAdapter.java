package com.hashmac.careercompass.ui.main.fragments.career.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmac.careercompass.beans.chat.Chat;
import com.hashmac.careercompass.databinding.ItemChatLeftBinding;
import com.hashmac.careercompass.databinding.ItemChatRightBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Chat> chatList = new ArrayList<>();

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            ItemChatLeftBinding userBinding = ItemChatLeftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new BotViewHolder(userBinding);
        } else {
            ItemChatRightBinding botBinding = ItemChatRightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new UserViewHolder(botBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (chatList.get(position).getType() == 0) {
            ((UserViewHolder) holder).bind(chatList.get(position));
        } else {
            ((BotViewHolder) holder).bind(chatList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatList.get(position).getType();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatRightBinding binding;

        public UserViewHolder(@NonNull ItemChatRightBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Chat chat) {
            binding.tvMessageRight.setText(chat.getMessage());
        }

    }

    public class BotViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatLeftBinding binding;
        public BotViewHolder(@NonNull ItemChatLeftBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Chat chat) {
            Timber.d("BotViewHolder: %s", chat.getMessage());
            binding.tvMessageLeft.setText(chat.getMessage());
        }
    }

}
