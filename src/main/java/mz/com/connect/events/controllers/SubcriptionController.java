package mz.com.connect.events.controllers;

import mz.com.connect.events.dto.ErrorMessage;
import mz.com.connect.events.dto.SubcriberResponse;
import mz.com.connect.events.exception.EventNotFoundException;
import mz.com.connect.events.exception.SubcriptionConflictException;
import mz.com.connect.events.exception.UserIndicatorNotFoundException;
import mz.com.connect.events.models.Subcription;
import mz.com.connect.events.models.User;
import mz.com.connect.events.services.Subcription.SubcriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubcriptionController {

    @Autowired
    private SubcriptionService subcriptionService;


    @PostMapping({"/subcription/{prettyName}", "/subcription/{prettyName}/{userId}"})
    public ResponseEntity createSubcription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable(required = false) Integer userId){

        SubcriberResponse createSubs = this.subcriptionService.createNewSubcription(prettyName, subscriber, userId);

        try {
            if (createSubs != null) {
                return ResponseEntity.ok(createSubs);
            }
        }catch (EventNotFoundException enfe){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ErrorMessage(enfe.getMessage()));

        }catch (SubcriptionConflictException sce) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(sce.getMessage()));

        }catch (UserIndicatorNotFoundException enfe){

            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(enfe.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
