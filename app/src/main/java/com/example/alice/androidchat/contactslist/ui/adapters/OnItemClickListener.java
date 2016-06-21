package com.example.alice.androidchat.contactslist.ui.adapters;

import com.example.alice.androidchat.entities.User;

/**
 * Created by alice on 6/16/16.
 * Manejar el evento click, del ContactListAdapter, q esta sobre el recclerView
 */

public interface OnItemClickListener {
    void  onItemClick(User user);
    void  onItemLongClick(User user);
}
