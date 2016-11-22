package com.gwt.sample.server.web;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.List;

/**
 * Created by PerevalovaMA on 21.11.2016.
 */
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "response")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RDSFetchResponse<T> {
    private int status;
    private int startRow;
    private int endRow;
    private int totalRows;
    private List<T> data;

    public RDSFetchResponse(int status, List<T> data) {
        this.status = status;
        totalRows = data.size();
        startRow = 0;
        endRow = totalRows - 1;
        this.data = data;
    }
}
