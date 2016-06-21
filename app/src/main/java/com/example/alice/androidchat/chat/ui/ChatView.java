package com.example.alice.androidchat.chat.ui;

import com.example.alice.androidchat.entities.ChatMessage;

/**
 * Created by alice on 6/19/16.
 */

public interface ChatView {
    void onMessageReceived (ChatMessage msg);
}
