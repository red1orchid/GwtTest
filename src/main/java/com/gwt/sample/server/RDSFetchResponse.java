package com.gwt.sample.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

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
    private T[] data;

    public RDSFetchResponse(int status, int startRow, int endRow, int totalRows, T[] data) {
        this.status = status;
        this.startRow = startRow;
        this.endRow = endRow;
        this.totalRows = totalRows;
        this.data = data;
    }
}
