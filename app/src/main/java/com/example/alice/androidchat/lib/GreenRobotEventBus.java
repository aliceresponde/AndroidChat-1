package com.example.alice.androidchat.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andrearodriguez on 6/12/16.
 */
public class GreenRobotEventBus implements EvenBus {


    EventBus eventBus;

    private static class SingletonHolder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus(){

        this.eventBus = EventBus.getDefault();
    }


    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);

    }

    @Override
    public void post(Object event) {
        eventBus.post(event);

    }
}
