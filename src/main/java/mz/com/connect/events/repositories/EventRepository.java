package mz.com.connect.events.repositories;

import mz.com.connect.events.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    Event findByPrettyName(String prettyName);
}
