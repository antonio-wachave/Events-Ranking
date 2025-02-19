package mz.com.connect.events.services.Event;

import mz.com.connect.events.models.Event;
import mz.com.connect.events.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addNewEvent(Event event) {

    event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ","-"));

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>) this.eventRepository.findAll();
    }

    @Override
    public Event getByPrettyName(String prettyName) {
        return this.eventRepository.findByPrettyName(prettyName);
    }
}
