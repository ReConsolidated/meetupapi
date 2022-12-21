package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;

    public long createEvent(AppUser user, AddEventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setOwnerId(user.getId());
        event.setDateTime(eventDto.getDate());
        event.setDurationInSeconds(eventDto.getDurationInSeconds());
        event.setLatitude(eventDto.getLatitude());
        event.setLongitude(eventDto.getLongitude());
        return eventsRepository.save(event).getId();
    }

    public List<Event> getNearbyEvents(AppUser currentUser, double latitude, double longitude) {
        return eventsRepository.findAll();
    }
}
