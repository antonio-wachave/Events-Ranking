package mz.com.connect.events.controllers;

import mz.com.connect.events.dto.ErrorMessage;
import mz.com.connect.events.exception.EventNotFoundException;
import mz.com.connect.events.models.Subscription;
import mz.com.connect.events.models.User;
import mz.com.connect.events.services.Subscription.SubscriptionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribers/{prettyName}")
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User user){

        Subscription newSubscription = this.subscriptionService.createNewSubscription(prettyName, user);

        try {
            if (newSubscription != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(newSubscription);
            }
        }catch ( EventNotFoundException efe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ErrorMessage(efe.getMessage()));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" usuario nao encontrado!");
    }

}
