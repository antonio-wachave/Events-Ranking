package mz.com.connect.events.services.Subcription;

import mz.com.connect.events.dto.SubcriberResponse;
import mz.com.connect.events.exception.EventNotFoundException;
import mz.com.connect.events.exception.SubcriptionConflictException;
import mz.com.connect.events.exception.UserIndicatorNotFoundException;
import mz.com.connect.events.models.Event;
import mz.com.connect.events.models.Subcription;
import mz.com.connect.events.models.User;
import mz.com.connect.events.repositories.EventRepository;
import mz.com.connect.events.repositories.SubcriptionRepository;
import mz.com.connect.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcriptionServiceImpl implements SubcriptionService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubcriptionRepository subcriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SubcriberResponse createNewSubcription(String eventName, User user, Integer userId) {

        Event event = this.eventRepository.findByPrettyName(eventName);

        if(event == null){
            throw new EventNotFoundException(" Event "+eventName+" nai existe! ");
        }

        User emailUsers = this.userRepository.findByEmail(user.getEmail());

        if(emailUsers == null){
            emailUsers = this.userRepository.save(user);
        }

        User indicator = this.userRepository.findById(userId).orElse(null);

        if(indicator == null){
            throw new UserIndicatorNotFoundException(" Usuario "+userId+" indicador nao existe!");
        }

        Subcription subs = new Subcription();

        subs.setEvent(event);
        subs.setSubscriber(emailUsers);
        subs.setIndication(indicator);


        Subcription subcription = this.subcriptionRepository.findByEventAndUser(event, emailUsers);

        if(subcription != null){
            throw new SubcriptionConflictException(" Ja existe inscricao para o usuario "+emailUsers.getName()+" no evento"+event.getTitle());
        }

        Subcription createSubs = this.subcriptionRepository.save(subs);

        return new SubcriberResponse(createSubs.getSubcriptionNumber(), "http://codecraft.com/subscription/"+createSubs.getEvent().getPrettyName()+"/"+createSubs.getSubscriber().getId());
    }
}
