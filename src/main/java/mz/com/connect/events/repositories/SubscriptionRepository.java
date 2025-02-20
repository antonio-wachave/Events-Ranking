package mz.com.connect.events.repositories;

import mz.com.connect.events.models.Event;
import mz.com.connect.events.models.Subscription;
import mz.com.connect.events.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    Subscription findByEventAndSubscriber(Event event, User user);
}
