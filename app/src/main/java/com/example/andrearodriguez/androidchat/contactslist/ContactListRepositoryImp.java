package com.example.andrearodriguez.androidchat.contactslist;

import com.example.andrearodriguez.androidchat.contactslist.events.ContactListenerEvent;
import com.example.andrearodriguez.androidchat.domain.FirebaseHelper;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.EvenBus;
import com.example.andrearodriguez.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;


/**
 * Created by alice on 6/17/16.
 * Usa el FireBaseHelper
 * contactEventListener es del tipo Firebase.client
 */
public class ContactListRepositoryImp implements ContactListRepository {

    private FirebaseHelper fireBaseHelper;
    private ChildEventListener  contactEventListener;
    private EvenBus eventBus;


    public ContactListRepositoryImp() {
        this.fireBaseHelper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
        fireBaseHelper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return fireBaseHelper.getAuthUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {


    }

    @Override
    public void subscribeToContactListEvents() {
        if ( contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String email = dataSnapshot.getKey();
                    email  = email.replace("_", ".");
                    boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
                    User user = new User();
                    user.setEmail(email);
                    user.setOnline(online);
                    post(ContactListenerEvent.onContactAdded , user);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListenerEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListenerEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

                @Override
                public void onCancelled(FirebaseError firebaseError) { }
            };
        }
        fireBaseHelper.getMyContactsReference().addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int eventType) {

        String email = dataSnapshot.getKey();
        email  = email.replace("_", ".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(eventType , user);
    }

    private void post(int eventType, User user) {
        ContactListenerEvent event = new ContactListenerEvent();
        event.setEventType(eventType);
        event.setUser(user);
        eventBus.post(event);
    }

    @Override
    public void unsubscribe() {
        if ( contactEventListener != null){
            fireBaseHelper.getMyContactsReference().removeEventListener( contactEventListener);
        }

    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void removeContact(String aContactEmail) {
        String currentUserEmail  = fireBaseHelper.getAuthUserEmail();
        //remuevo contacto del usuario autenticado
        fireBaseHelper.getOneContactReference(currentUserEmail , aContactEmail).removeValue();
        // remuevo el usuario autenticado de los contactos del usuario a eliminar
        fireBaseHelper.getOneContactReference(aContactEmail ,currentUserEmail ).removeValue();
    }
}
