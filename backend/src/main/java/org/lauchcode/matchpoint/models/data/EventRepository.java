package org.lauchcode.matchpoint.models.data;

import org.lauchcode.matchpoint.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findAll();


}
