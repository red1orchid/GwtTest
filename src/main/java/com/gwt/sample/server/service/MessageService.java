package com.gwt.sample.server.service;

import com.gwt.sample.server.model.Message;
import com.gwt.sample.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PerevalovaMA on 22.11.2016.
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        for (Message m: repository.findAll()) {
            messages.add(m);
        }
        return messages;
    }

    public Message add(Message m) {
        return repository.save(m);
    }
}
