package com.example.andrearodriguez.androidchat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by alice on 6/19/16.
 */

@JsonIgnoreProperties({"sendByMe"})
public class ChatMessage {

    private String msg ;
    private String sender;
    private boolean semdByMe;

    public ChatMessage() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSemdByMe() {
        return semdByMe;
    }

    public void setSemdByMe(boolean semdByMe) {
        this.semdByMe = semdByMe;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if(o instanceof ChatMessage){
            ChatMessage msg = (ChatMessage) o;
            equal = this.sender.equals(msg.getSender())
                    && this.msg.equals(msg.getMsg())
                    && this.semdByMe == msg.semdByMe;
        }
        return equal;
    }
}
