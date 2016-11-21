package com.gwt.sample.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MessageCategory {
    private int id;
    private String name;

    public MessageCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
