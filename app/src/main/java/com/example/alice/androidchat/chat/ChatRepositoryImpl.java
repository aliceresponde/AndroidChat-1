package com.example.alice.androidchat.chat;

import com.example.alice.androidchat.chat.events.ChatEvent;
import com.example.alice.androidchat.domain.FirebaseHelper;
import com.example.alice.androidchat.entities.ChatMessage;
import com.example.alice.androidchat.lib.EvenBus;
import com.example.alice.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by alice on 6/19/16.
 */
public class ChatRepositoryImpl implements ChatRepository {

    private String recipient;
    private EvenBus evenBus;
    private FirebaseHelper firebaseHelper;
    private ChildEventListener chatEventListener;


    public ChatRepositoryImpl() {
        firebaseHelper = FirebaseHelper.getInstance();
        evenBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void sendMessages(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(firebaseHelper.getAuthUserEmail());
        chatMessage.setMsg(msg);

        Firebase chatsReference = firebaseHelper.getChatsReferrence(recipient);
        chatsReference.push().setValue(chatMessage);  //creo identificador unico
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
        if (chatEventListener == null){

            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender =  chatMessage.getSender();

                    chatMessage.setSemdByMe( msgSender.equals(firebaseHelper.getAuthUserEmail()));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    evenBus.post(chatEvent);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }
        firebaseHelper.getChatsReferrence(recipient).addChildEventListener(chatEventListener);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

        if (chatEventListener != null){
            firebaseHelper.getChatsReferrence(recipient).removeEventListener(chatEventListener);
        }

    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        firebaseHelper.changeUserConnectionStatus(online);
    }
}
