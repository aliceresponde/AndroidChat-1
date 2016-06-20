package com.example.andrearodriguez.androidchat.contactslist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.addcontact.iu.AddContactFragment;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenter;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenterImpl;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.ContactListAdapter;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.OnItemClickListener;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.GlideImageLoader;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;
import com.example.andrearodriguez.androidchat.login.ui.LoginActivity;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView , OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reciclerViewContacts)
    RecyclerView reciclerViewContacts;

    ContactListPresenter presenter;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        
        setupAdapter();
        setupRecyclerView();
        
        //Nota es necesario implementar el presntador si no, tengo un null pointer exceotion
        presenter  = new ContactListPresenterImpl(this);
        presenter.onCreate();
        setupToolbar();
    }


//    ========================CREATE MENU =============================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            presenter.signOff();
            Intent intent  = new Intent( this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                         |  Intent.FLAG_ACTIVITY_NEW_TASK
                         |  Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupRecyclerView() {
        reciclerViewContacts.setLayoutManager( new LinearLayoutManager(this));
        reciclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader  = new GlideImageLoader(this.getApplicationContext());
//        User user  =  new User();
//        user.setOnline(false);
//        user.setEmail("aliceresponde@gmail.com");
//
//        adapter = new ContactListAdapter(Arrays.asList(new User[]{user}), loader , this);
        adapter = new ContactListAdapter(new ArrayList<User>(), loader , this);


    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail()); //email del user
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
//   =============================AGREGAR CONTACTO - FLOATING ACTION BUTTON=================

    /**
     * open Dialog  fragment for add new contact email
     */
    @OnClick(R.id.fav)
    public void addContact(){
        //muestro dialogFragment
        Log.i("clickAdd" , "floatBTN");
        new AddContactFragment().show( getSupportFragmentManager() , getString(R.string.addcontact_message_title));
    }

//    ========================ContactListView =================================================

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

//    ==================================OnItemClickListener=================================

    @Override
    public void onItemClick(User user) {
        Toast.makeText(this, user.getEmail() , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
