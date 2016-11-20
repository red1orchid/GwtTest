package com.gwt.sample.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by Marina on 20.11.16.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {
    private String category;
    private String subject;
    private String body;

    public Message(String category, String subject, String body) {
        this.category = category;
        this.subject = subject;
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}