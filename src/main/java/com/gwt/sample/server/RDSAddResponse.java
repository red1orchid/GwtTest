package com.gwt.sample.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RDSAddResponse<T> {
    private int status;
    private T data;

    public RDSAddResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
