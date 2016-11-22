package com.gwt.sample.server.service;

import com.gwt.sample.server.model.MessageCategory;
import com.gwt.sample.server.repository.MessageCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PerevalovaMA on 22.11.2016.
 */
@Service
public class MessageCategoryService {
    @Autowired
    private MessageCategoryRepository repository;

    public List<MessageCategory> getAll() {
        List<MessageCategory> categories = new ArrayList<>();
        for (MessageCategory c: repository.findAll()) {
            categories.add(c);
        }
        return categories;
    }
}