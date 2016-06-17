package com.example.andrearodriguez.androidchat.contactslist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.contactslist.ContactListPresenter;
import com.example.andrearodriguez.androidchat.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reciclerViewContacts)
    RecyclerView reciclerViewContacts;

    ContactListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        //Nota es necesario implementar el presntador si no, tengo un null pointer exceotion

        presenter.onCreate();
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

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactDeleted(User user) {

    }
}
