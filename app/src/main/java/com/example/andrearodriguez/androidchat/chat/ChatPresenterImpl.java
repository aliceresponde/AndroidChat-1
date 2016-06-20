package com.example.andrearodriguez.androidchat.chat;

import com.example.andrearodriguez.androidchat.chat.events.ChatEvent;
import com.example.andrearodriguez.androidchat.chat.ui.ChatView;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 6/19/16.
 */
public class ChatPresenterImpl implements ChatPresenter {

    private EvenBus evenBus;
    private ChatView view;
    private ChatSessionInteractor chatSessionInteractor;
    private ChatInteractor chatInteractor;







    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        evenBus = GreenRobotEventBus.getInstance();
        chatSessionInteractor = new ChatSessionInteractorImpl();
        chatInteractor = new ChatInteractorImpl();

    }

    /**
     * Registro el presenter al eventBus
     */
    @Override
    public void onCreate() {
        evenBus.register(this);
    }


    @Override
    public void onResume() {
        chatInteractor.subscribe();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        evenBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
       chatInteractor.sendMessages(msg);
    }

    @Subscribe
    @Override
    public void onEventMainThread(ChatEvent event) {
        if (view != null){
            view.onMessageReceived( event.getMessage());
        }

    }
}
