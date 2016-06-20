package com.example.andrearodriguez.androidchat.chat.ui.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.entities.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alice on 6/19/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context, ArrayList<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        Log.i("dataset",chatMessage.getMsg() +""+chatMessage.isSemdByMe());

        String msg  = chatMessage.getMsg();
        holder.txtMessage.setText(msg);

        int color = fetchColor(R.attr.colorPrimary);
        int gravity = Gravity.LEFT;

        if (!chatMessage.isSemdByMe()){
            color = fetchColor( R.attr.colorAccent);
            gravity = Gravity.RIGHT;
        }


        holder.txtMessage.setBackgroundColor(color );
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
        params.gravity = gravity;

        holder.txtMessage.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void add(ChatMessage msg) {
        if (!chatMessages.contains(msg)) {
            chatMessages.add(msg);
            notifyDataSetChanged();
        }
    }

    /**
     * obtain  color form id
     * @param color
     * @return
     */
    private  int fetchColor(int color){
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data,
                new int[]{color});
        int returnColor = a.getColor(0,0);
        a.recycle();
        return returnColor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtMessage)
        TextView txtMessage;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
