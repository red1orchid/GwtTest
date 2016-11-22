package com.gwt.sample.server.web;

import com.gwt.sample.server.model.Message;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RDSAddMessageRequest {
    private String dataSource;
    private String operationType;
    private String textMatchStyle;
    private String componentId;
    private Message data;
    private Message oldValues;

    public RDSAddMessageRequest() {
    }

    public Message getData() {
        return data;
    }
}
