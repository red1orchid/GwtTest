package com.gwt.sample.server.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;

/**
 * Created by PerevalovaMA on 22.11.2016.
 */
@Entity
@Table(name="message_categories")
public class MessageCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
