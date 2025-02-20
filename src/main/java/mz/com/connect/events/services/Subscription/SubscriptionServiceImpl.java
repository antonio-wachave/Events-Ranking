package mz.com.connect.events.services.Subscription;

import mz.com.connect.events.models.Event;
import mz.com.connect.events.models.Subscription;
import mz.com.connect.events.models.User;
import mz.com.connect.events.repositories.EventRepository;
import mz.com.connect.events.repositories.SubscriptionRepository;
import mz.com.connect.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Subscription createNewSubscription(String eventName, User user) {

        Subscription subs = new Subscription();

        Event event = this.eventRepository.findByPrettyName(eventName);
        this.userRepository.save(user);

        subs.setEvent(event);
        subs.setSubscriber(user);

        return this.subscriptionRepository.save(subs);

    }
}
