package com.teraninja.guard.Model;

import okhttp3.MultipartBody;

public class SendMessage {
    String conversation_id;
    String type;
    String message;
    MultipartBody.Part message2;

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultipartBody.Part getMessage2() {
        return message2;
    }

    public void setMessage2(MultipartBody.Part message2) {
        this.message2 = message2;
    }
}
