package com.gwt.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtTest extends VLayout implements EntryPoint {

  /**
   * This is the entry point method.
   */
    public void onModuleLoad() {
        final RestDataSource messagesRDS = createMessagesRestDataSource();

        final ListGrid table = new ListGrid(messagesRDS);
        table.setAutoFetchData(true);
        table.setWidth("70%");
        table.setCanResizeFields(false);
        table.setAllowFilterExpressions(false);
        table.setShowHeaderContextMenu(false);
        table.setShowHeaderMenuButton(false);

        Button addButton = new Button("Add new message");
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.askforValue("", null);
            }

        });

        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setHeight(10);

        setPadding(10);
        setWidth("100%");

        addMember(table);
        addMember(spacer);
        addMember(addButton);

        draw();
    }

    private RestDataSource createMessagesRestDataSource() {
        RestDataSource messagesRDS = new RestDataSource();
        messagesRDS.setPrettyPrintJSON(true);
        messagesRDS.setDataFormat(DSDataFormat.JSON);
        messagesRDS.setFields(
                new DataSourceTextField("category"),
                new DataSourceTextField("subject"),
                new DataSourceTextField("body"));

        messagesRDS.setDataURL("/rest/messages");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");

        messagesRDS.setOperationBindings(
                createOperationBinding(DSOperationType.FETCH, DSProtocol.GETPARAMS, headers),
                createOperationBinding(DSOperationType.ADD, DSProtocol.POSTMESSAGE, headers)
        );

        return messagesRDS;
    }

    private OperationBinding createOperationBinding(DSOperationType type, DSProtocol protocol, Map headers) {
        OperationBinding op = new OperationBinding();
        op.setOperationType(type);
        op.setDataProtocol(protocol);

        DSRequest request = new DSRequest();
        request.setHttpHeaders(headers);
        op.setRequestProperties(request);

        return op;
    }
}
