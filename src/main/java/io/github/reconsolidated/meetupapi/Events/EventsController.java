package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.currentUser.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @GetMapping("/create_event")
    public ResponseEntity<?> addEvent(@CurrentUser AppUser currentUser, @RequestBody @Valid AddEventDto eventDto) {
        return ResponseEntity.ok(eventsService.createEvent(currentUser, eventDto));
    }
}
