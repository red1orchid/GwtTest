package com.gwt.sample.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RDSAddRequest<T> {
    private String dataSource;
    private String operationType;
    private String textMatchStyle;
    private String componentId;
    private T data;
    private T oldValues;

    public RDSAddRequest() {
    }

    public T getData() {
        return data;
    }
}
