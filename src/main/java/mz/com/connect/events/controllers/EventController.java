package mz.com.connect.events.controllers;

import mz.com.connect.events.models.Event;
import mz.com.connect.events.services.Event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Event events = this.eventService.addNewEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(events);
    }

    @GetMapping("/events")
    public List<Event> getAllEvent(){
        return this.eventService.getAllEvents();
    }

    @GetMapping("/events/{prettyName}")
    public ResponseEntity<Event> getEventByprettyName(@PathVariable String prettyName){

        Event event = this.eventService.getByPrettyName(prettyName);

        if(event != null){
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
