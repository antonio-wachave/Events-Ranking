package mz.com.connect.events.services.Event;


import mz.com.connect.events.models.Event;

import java.util.List;

public interface EventService {

    Event addNewEvent(Event event);

    List<Event> getAllEvents();

    Event getByPrettyName(String prettyName);

    
}
