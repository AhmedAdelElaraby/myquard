package com.teraninja.guard.Model;

import okhttp3.MultipartBody;

public class SendDataProblem {
    String contact_reason_id;
    String message;
    MultipartBody.Part file;
    String type;

    public String getContact_reason_id() {
        return contact_reason_id;
    }

    public void setContact_reason_id(String contact_reason_id) {
        this.contact_reason_id = contact_reason_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
