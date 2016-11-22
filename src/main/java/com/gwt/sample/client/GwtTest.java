package com.gwt.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.*;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
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
        final ListGrid table = createTable();
        final Window winModal = createModalWindow();
        final DynamicForm selectorForm = new DynamicForm();
        final RestDataSource categoriesRDS = createCategoriesRDS();

      final SelectItem categoriesSelector = createCategoriesSelector(categoriesRDS, "Message category", true);
        categoriesSelector.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                Integer categoryId = (Integer) event.getItem().getValue();
                if (categoryId != null) {
                    table.filterData(new Criteria("categoryId", categoryId.toString()));
                } else {
                    table.filterData();
                }
            }
        });

        Button addButton = new Button("Add new message");
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                winModal.show();
            }
        });

        final DynamicForm newMessageForm = new DynamicForm();
        newMessageForm.setWidth100();
        newMessageForm.setPadding(10);
        newMessageForm.setLayoutAlign(VerticalAlignment.BOTTOM);

        final SelectItem modalCategoriesSelector = createCategoriesSelector(categoriesRDS, "category", false);

        final TextItem subjectField = new TextItem();
        subjectField.setName("subject");

        final TextAreaItem bodyField = new TextAreaItem();
        bodyField.setName("body");
        newMessageForm.setFields(modalCategoriesSelector, subjectField, bodyField);
        winModal.addItem(newMessageForm);

        Button saveButton = new Button("OK");
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("categoryId", (Integer) modalCategoriesSelector.getValue());
                record.setAttribute("subject", subjectField.getEnteredValue());
                record.setAttribute("body", bodyField.getEnteredValue());

                table.addData(record);
                winModal.hide();
            }
        });
        winModal.addItem(saveButton);

        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setHeight(10);

        setPadding(10);
        setWidth("100%");

        selectorForm.setItems(categoriesSelector);
        selectorForm.setPadding(10);

        addMember(selectorForm);
        addMember(table);
        addMember(spacer);
        addMember(addButton);

        draw();
    }

    private SelectItem createCategoriesSelector(RestDataSource dataSource, String title, boolean isEmptyAllowed) {
        SelectItem categoriesSelector = new SelectItem();
        categoriesSelector.setTitle(title);
        categoriesSelector.setOptionDataSource(dataSource);
        categoriesSelector.setAutoFetchData(true);
        categoriesSelector.setValueField("id");
        categoriesSelector.setDisplayField("name");
        if (isEmptyAllowed) {
            categoriesSelector.setAllowEmptyValue(true);
            categoriesSelector.setEmptyDisplayValue("All");
        }

        return categoriesSelector;
    }

    private ListGrid createTable() {
        final RestDataSource messagesRDS = createMessagesRDS();
        messagesRDS.getField("categoryId").setHidden(true);
        messagesRDS.getField("categoryName").setTitle("Category");

        ListGrid table = new ListGrid(messagesRDS);
        table.setAutoFetchData(true);
        table.setWidth("70%");
        table.setShowAllRecords(true);
        table.setBodyOverflow(Overflow.VISIBLE);
        table.setOverflow(Overflow.VISIBLE);
        table.setCanResizeFields(false);
        table.setAllowFilterExpressions(false);
        table.setShowHeaderContextMenu(false);
        table.setShowHeaderMenuButton(false);

        return table;
    }

    private Window createModalWindow() {
        Window winModal = new Window();
        winModal.setWidth(500);
        winModal.setHeight(300);
        winModal.setTitle("Add new message");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();
        winModal.setCanDrag(true);
        return winModal;
    }

    private RestDataSource createMessagesRDS() {
        RestDataSource messagesRDS = new RestDataSource();
        messagesRDS.setPrettyPrintJSON(true);
        messagesRDS.setDataFormat(DSDataFormat.JSON);
        messagesRDS.setFields(
                new DataSourceIntegerField("categoryId"),
                new DataSourceTextField("categoryName"),
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

    private RestDataSource createCategoriesRDS() {
        RestDataSource categoriesRDS = new RestDataSource();
        categoriesRDS.setPrettyPrintJSON(true);
        categoriesRDS.setDataFormat(DSDataFormat.JSON);

        categoriesRDS.setDataURL("/rest/messages/categories");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");

        categoriesRDS.setOperationBindings(
                createOperationBinding(DSOperationType.FETCH, DSProtocol.GETPARAMS, headers)
        );

        return categoriesRDS;
    }

    private OperationBinding createOperationBinding(DSOperationType type, DSProtocol protocol, Map headers) {
        OperationBinding op = new OperationBinding();
        op.setOperationType(type);
        op.setDataProtocol(protocol);

/*        DSRequest request = new DSRequest();
        request.setHttpHeaders(headers);
        op.setRequestProperties(request);*/

        return op;
    }
}
