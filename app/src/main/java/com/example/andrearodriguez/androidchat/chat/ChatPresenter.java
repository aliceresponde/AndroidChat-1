package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.chat.events.ChatEvent;

/**
 * Created by alice on 6/19/16.
 */

public interface ChatPresenter {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);



}
