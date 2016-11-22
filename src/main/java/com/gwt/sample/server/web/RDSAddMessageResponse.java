package com.gwt.sample.server.web;

import com.gwt.sample.server.model.Message;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RDSAddMessageResponse {
    private int status;
    private Message data;

    public RDSAddMessageResponse(int status, Message data) {
        this.status = status;
        this.data = data;
    }
}
