package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.currentUser.CurrentUser;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @PostMapping("/create_event")
    public ResponseEntity<Long> addEvent(@CurrentUser AppUser currentUser, @RequestBody @Valid AddEventDto eventDto) {
        return ResponseEntity.ok(eventsService.createEvent(currentUser, eventDto));
    }

    @PostMapping("/add_participant")
    public ResponseEntity<?> addParticipant(@CurrentUser AppUser currentUser, @RequestParam Long eventId, @RequestParam Long userId) {
        eventsService.addParticipant(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add_participants")
    public ResponseEntity<?> addParticipants(@CurrentUser AppUser currentUser, @RequestParam Long eventId, @RequestBody List<Long> userIds) {
        eventsService.addParticipants(eventId, userIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get_nearby_events")
    public ResponseEntity<List<Event>> getNearbyEvents(
            @CurrentUser AppUser currentUser,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        List<Event> events = eventsService.getNearbyEvents(currentUser, latitude, longitude);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        events.forEach((e) -> {
            e.setDate(format.format(e.getDateTime()));
        });
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/delete_all_events")
    public ResponseEntity<?> deleteAllEvents(@CurrentUser AppUser currentUser) {
        eventsService.deleteAllEvents();
        return ResponseEntity.ok().build();
    }



}
