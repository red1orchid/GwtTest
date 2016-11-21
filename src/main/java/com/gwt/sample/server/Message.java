package com.gwt.sample.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by Marina on 20.11.16.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {
    private int categoryId;
    private String category;
    private String subject;
    private String body;

    public Message(int categoryId, String category, String subject, String body) {
        this.categoryId = categoryId;
        this.category = category;
        this.subject = subject;
        this.body = body;
    }
}