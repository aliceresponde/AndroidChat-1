package com.example.andrearodriguez.androidchat.chat.events;

import com.example.andrearodriguez.androidchat.entities.ChatMessage;

/**
 * Created by alice on 6/19/16.
 */
public class ChatEvent {

    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
