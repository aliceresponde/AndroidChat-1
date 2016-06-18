package com.example.andrearodriguez.androidchat.contactslist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.entities.User;
import com.example.andrearodriguez.androidchat.lib.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.andrearodriguez.androidchat.domain.AvatarHelper.getAvatarUL;

/**
 * Created by alice on 6/16/16.
 * Adapter para cada usuario contacto
 * Recibe la interface onItemClickListener , para el manejo del onClick y del onLongClick
 * Recibe un List<users> llamado contactList que es el dataset
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {


    private List<User> contactList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public ContactListAdapter(List<User> contactList, ImageLoader imageLoading, OnItemClickListener onItemClickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoading;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item_row_layout, parent, false);


        return new ViewHolder(view);
    }

    /**
     * Defino los elementos del layout usando  butterKnife , para ponerlos dentro del  viewHolder
     * Aqui construyo el elemento, por cada user del dataSet
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user  = contactList.get(position);
        holder.setClickListener(user, onItemClickListener);
        boolean online = user.isOnline();
        String status = online ? "online" : "offline" ;
        int color = online ? Color.GREEN : Color.RED ;

        String email = user.getEmail();
        Log.i("Email", email);
        holder.txtUser.setText(email);

        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        //metodo para cargar la imagen desde url, en el circleImageView
        imageLoader.load(holder.imgAvatar , getAvatarUL(email));


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    /**
     * Define cada elemento del row, vinculando los que vienen del layout del row
     * Requiero un atributo extra, para settear y manejar la interface que permitira hacer click
     * sobre cada row. Al setClickListener (User, OnItemClickListener)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @BindView(R.id.txtUser)
        TextView txtUser;
        @BindView(R.id.txtStatus)
        TextView txtStatus;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view =  itemView;
        }

        private void setClickListener(final User user , final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });

        }
    }
}
