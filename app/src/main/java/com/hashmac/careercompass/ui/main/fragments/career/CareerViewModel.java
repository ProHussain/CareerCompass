package com.hashmac.careercompass.ui.main.fragments.career;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hashmac.careercompass.beans.chat.Chat;
import com.hashmac.careercompass.beans.user.User;
import com.hashmac.careercompass.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import timber.log.Timber;

public class CareerViewModel extends ViewModel {
    MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    MutableLiveData<List<Chat>> careerChatList = new MutableLiveData<>();
    MutableLiveData<Boolean> isChatCompleted = new MutableLiveData<>();
    private int index= 0;
    private List<Chat> generalQuestions;
    private List<Chat> chatList = new ArrayList<>();


    public void getUser(Context context) {
        User user = new SharedUtils(context).getUser();
        userMutableLiveData.setValue(user);
    }

    public void startCareerChat() {
        isChatCompleted.setValue(false);
        index = 0;
        chatList.clear();
        generateCareerChat();
        careerChatList();
        chatList.add(new Chat("0", "Hello " + Objects.requireNonNull(userMutableLiveData.getValue()).getAuth().getName() + ", I'm Career Compass. I'm here to help you explore your career interests and goals.", 1, ""));
        chatList.add(new Chat("2", "Remember by navigating away from this page, you will lose your progress. Are you ready to start?", 1, ""));
        chatList.add(new Chat("1","For each question, take a moment to reflect on your answer before responding. When you're ready, type your response and hit send. Let's begin!", 1, ""));
        chatList.add(generalQuestions.get(index));
    }

    public void careerChatList() {
        careerChatList.setValue(chatList);
    }

    public void sendAnswerToServer(String answer) {
        chatList.add(new Chat("11", answer, 0, ""));
        index++;
        if (index < generalQuestions.size()) {
            chatList.add(generalQuestions.get(index));
            careerChatList.setValue(chatList);
        } else {
            isChatCompleted.setValue(true);
        }
    }

    public void generateCareerChat() {
        generalQuestions = new ArrayList<>();
        generalQuestions.add(new Chat("1", "What skills or talents are you most proud of?", 1, ""));
        generalQuestions.add(new Chat("2", "What tasks or activities do you find most engaging and enjoyable?", 1, ""));
        generalQuestions.add(new Chat("3", "What kind of work environment do you thrive in?", 1, ""));
        generalQuestions.add(new Chat("4", "What are your long-term career goals?", 1, ""));
        generalQuestions.add(new Chat("5", "What are some accomplishments you are most proud of?", 1, ""));
        generalQuestions.add(new Chat("6", "What are some challenges or obstacles you've overcome in your academic or professional life?", 1, ""));
        generalQuestions.add(new Chat("7", "What are some of your favorite projects or assignments you've worked on?", 1, ""));
        generalQuestions.add(new Chat("8", "What are some areas or skills you're hoping to improve or develop?", 1, ""));
        generalQuestions.add(new Chat("9", "What are some industries or roles you're interested in exploring?", 1, ""));
        generalQuestions.add(new Chat("10", "What motivates you in your work or studies?", 1, ""));
    }

    public void clearChat() {
        chatList.clear();
        careerChatList.setValue(chatList);
    }
}
