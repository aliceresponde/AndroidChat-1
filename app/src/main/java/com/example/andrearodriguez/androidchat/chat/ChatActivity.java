package com.example.andrearodriguez.androidchat.chat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.chat.adapters.ChatAdapter;
import com.example.andrearodriguez.androidchat.domain.AvatarHelper;
import com.example.andrearodriguez.androidchat.entities.ChatMessage;
import com.example.andrearodriguez.androidchat.lib.GlideImageLoader;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements  ChatView{

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.messagesRecyclerView)
    RecyclerView messagesRecyclerView;
    @BindView(R.id.inputMessage)
    EditText inputMessage;
    @BindView(R.id.btnSendMessage)
    ImageButton btnSendMessage;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private  ChatPresenter presenter ;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new  ChatPresenterImpl(this);
        presenter.onCreate();


        setupToolBar(getIntent());
    }

    private void setupToolBar(Intent intent) {
        String recipient = intent.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);

        boolean online = intent.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        ImageLoader imageLoader =  new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar , AvatarHelper.getAvatarUL(recipient));

    }

    private void setupAdapter() {
    }

    private void setupRecyclerView() {
        messagesRecyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
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

    @OnClick(R.id.btnSendMessage)
    public void onClick() {
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        messagesRecyclerView.scrollToPosition(adapter.getItemCount() -1 );
    }
}
