package com.gwt.sample.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marina on 19.11.16.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Message loadPage() {
        System.out.println("TESTTT");

        return new Message("Warning", "My patience is running thin",
                "No, I mean really. How long are you gonna keep paying these incompetent people out of my pocket?!");
    }
}
