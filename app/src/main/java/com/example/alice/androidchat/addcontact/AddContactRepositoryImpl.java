package com.example.alice.androidchat.addcontact;

import android.util.Log;

import com.example.alice.androidchat.addcontact.events.AddContactEvent;
import com.example.alice.androidchat.domain.FirebaseHelper;
import com.example.alice.androidchat.entities.User;
import com.example.alice.androidchat.lib.EvenBus;
import com.example.alice.androidchat.lib.GreenRobotEventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by alice on 6/19/16.
 */
public class AddContactRepositoryImpl implements AddContactRepository {

    // Singletones
    private EvenBus evenBus;
    private FirebaseHelper firebaseHelper;

    public AddContactRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.evenBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String emailToAdd) {
        Log.i("clickAdd" , "REPOSITORY");
        final  String keyEmailToAdd  = emailToAdd.replace(".","_");
        Firebase userReference = firebaseHelper.getUserReference(emailToAdd);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user != null){
                    Firebase myContacstReferemce = firebaseHelper.getMyContactsReference();
                    myContacstReferemce.child(keyEmailToAdd).setValue(user.isOnline());

                    String currentUserKey = firebaseHelper.getAuthUserEmail().replace(".", "_");

                    Firebase reverseContactsReference = firebaseHelper.getContactsReference(emailToAdd);
                    reverseContactsReference.child(currentUserKey).setValue(User.ONLINE);

                    postSuccess();
                }else {
                    postError();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }
        });
    }

    private void postSuccess(){
        Log.i("clickAdd" , "REPOSITORY - succed");

        post(false);
    }

    private void postError(){
        post(true);
    }

    private  void  post(boolean error){
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        evenBus.post(event);
    }
}
