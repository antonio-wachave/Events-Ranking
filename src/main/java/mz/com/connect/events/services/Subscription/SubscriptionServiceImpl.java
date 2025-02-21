package mz.com.connect.events.services.Subscription;

import mz.com.connect.events.dto.SubscriberResponse;
import mz.com.connect.events.exception.EventNotFoundException;
import mz.com.connect.events.exception.SubscriptionConflictException;
import mz.com.connect.events.exception.UserIndicatorNotFoundException;
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
    public SubscriberResponse createNewSubscription(String eventName, User user, Integer userId) {

        Event event = this.eventRepository.findByPrettyName(eventName);

        if(event == null){
            throw new EventNotFoundException(" Evento "+eventName+" não existe. ");
        }

        User newUser = this.userRepository.findByEmail(user.getEmail());

        if(newUser == null){
            this.userRepository.save(user);
        }

        User indicator = this.userRepository.findById(userId).orElse(null);

        if(indicator == null){
            throw new UserIndicatorNotFoundException(" usuario "+indicator+" nao existe. ");
        }

        Subscription subs = new Subscription();

        subs.setEvent(event);
        subs.setSubscriber(user);
        subs.setIndication(indicator);

        Subscription existSubs = this.subscriptionRepository.findByEventAndSubscriber(event,user);

        if(existSubs != null){
            throw new SubscriptionConflictException(" Ja existe inscricao para o usuario!"+user.getName()+" no evento "+event.getTitle());
        }

       Subscription saveSubs = this.subscriptionRepository.save(subs);

       return new SubscriberResponse(saveSubs.getSubscriptionNumber(),"http://codecraft.com/"+saveSubs.getEvent().getPrettyName()+"/"+saveSubs.getSubscriber().getId());

    }
}
