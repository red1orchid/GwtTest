package com.gwt.sample.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Marina on 19.11.16.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public RDSFetchResponse<Message> getMessages() {
        System.out.println("FETCH");

        Message message = new Message(1, "Warning", "My patience is running thin",
                "No, I mean really. How long are you gonna keep paying these incompetent people out of my pocket?!");
        Message[] array = {message, message, message};

        return new RDSFetchResponse<>(0, 0, 2, 3, array);
    }

    @ResponseBody
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public RDSFetchResponse<MessageCategory> getMessagesCategories() {
        System.out.println("FETCH CAT");

        MessageCategory[] array = {new MessageCategory(1, "Warning"), new MessageCategory(2, "A"), new MessageCategory(3, "B")};
        return new RDSFetchResponse<>(0, 0, 2, 3, array);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public RDSAddResponse<Message> addMessage(@RequestBody RDSAddRequest<Message> addRequest) {
        System.out.println("ADD");

        return new RDSAddResponse<>(0, addRequest.getData());
    }
}
