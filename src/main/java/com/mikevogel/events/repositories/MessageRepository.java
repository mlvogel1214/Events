package com.mikevogel.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mikevogel.events.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
	@Query(value="Select * from messages where event_id = (:id) order by created_at desc", nativeQuery=true)
	List<Message> getMessageByEvent(@Param("id") Long id);
}
