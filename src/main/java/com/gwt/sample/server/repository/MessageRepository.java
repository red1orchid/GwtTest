package com.gwt.sample.server.repository;

import com.gwt.sample.server.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by PerevalovaMA on 22.11.2016.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
