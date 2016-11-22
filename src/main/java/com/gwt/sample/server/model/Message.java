package com.gwt.sample.server.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by PerevalovaMA on 22.11.2016.
 */
@Entity
@Table(name="messages")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="category_id")
    private Integer categoryId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="category_id", insertable = false, updatable = false)
    @JsonIgnore
    private MessageCategory category;

    private String subject;

    private String body;

/*
    public Integer getCategoryId() {
        return category.getId();
    }
*/

    public String getCategoryName() {
        return category.getName();
    }
}
