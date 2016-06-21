package com.example.alice.androidchat.lib;

/**
 * Created by andrearodriguez on 6/12/16.
 */
public interface EvenBus {

    void register (Object subscriber);
    void unregister (Object subscriber);
    void post (Object event);
}
