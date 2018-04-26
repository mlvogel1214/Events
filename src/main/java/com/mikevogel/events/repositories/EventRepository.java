package com.mikevogel.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mikevogel.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findByStateContaining(String state);
	List<Event> findByStateIgnoreCase(String state);
	@Query(value="select * from events where state != (:name)", nativeQuery=true)
	List<Event> findByStateExcluding(@Param("name") String state);
}
