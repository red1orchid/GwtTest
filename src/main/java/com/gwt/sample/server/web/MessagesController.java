package com.gwt.sample.server.web;

import com.gwt.sample.server.model.Message;
import com.gwt.sample.server.model.MessageCategory;
import com.gwt.sample.server.service.MessageCategoryService;
import com.gwt.sample.server.service.MessageService;
import com.smartgwt.client.rpc.RPCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Marina on 19.11.16.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageCategoryService messageCategoryService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public RDSFetchResponse<Message> getMessages() {
        List<Message> messages = messageService.getAll();

        return new RDSFetchResponse<>(RPCResponse.STATUS_SUCCESS, messages);
    }

    @ResponseBody
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public RDSFetchResponse<MessageCategory> getMessagesCategories() {
        return new RDSFetchResponse<>(RPCResponse.STATUS_SUCCESS, messageCategoryService.getAll());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public RDSAddMessageResponse addMessage(@RequestBody RDSAddMessageRequest request) {
        messageService.add(request.getData());

        RDSAddMessageResponse resp = new RDSAddMessageResponse(RPCResponse.STATUS_SUCCESS, request.getData());

        return resp;
    }
}
