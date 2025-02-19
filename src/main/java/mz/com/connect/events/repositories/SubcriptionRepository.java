package mz.com.connect.events.repositories;

import mz.com.connect.events.models.Event;
import mz.com.connect.events.models.Subcription;
import mz.com.connect.events.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcriptionRepository extends CrudRepository<Subcription, Integer> {

    Subcription findByEventAndUser(Event event, User user);
}
