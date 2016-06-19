package com.example.andrearodriguez.androidchat.contactslist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenter;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenterImpl;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.ContactListAdapter;
import com.example.andrearodriguez.androidchat.contactslist.ui.adapters.OnItemClickListener;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.GlideImageLoader;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;

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

    @OnClick(R.id.fav)
    public void addContact(){

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

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
