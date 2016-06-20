package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.entities.ChatMessage;

/**
 * Created by alice on 6/19/16.
 */

public interface ChatView {
    void onMessageReceived (ChatMessage msg);
}
