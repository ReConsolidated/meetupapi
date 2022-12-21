package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.currentUser.CurrentUser;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @PostMapping("/create_event")
    public ResponseEntity<?> addEvent(@CurrentUser AppUser currentUser, @RequestBody @Valid AddEventDto eventDto) {
        return ResponseEntity.ok(eventsService.createEvent(currentUser, eventDto));
    }

    @GetMapping("/get_nearby_events")
    public ResponseEntity<?> getNearbyEvents(
            @CurrentUser AppUser currentUser,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        return ResponseEntity.ok(eventsService.getNearbyEvents(currentUser, latitude, longitude));
    }



}
