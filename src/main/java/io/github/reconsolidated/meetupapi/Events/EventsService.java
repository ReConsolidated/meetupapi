package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;
    private final AppUserService appUserService;

    public long createEvent(AppUser user, AddEventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setOwnerId(user.getId());
        event.setDateTime(eventDto.getDate());
        event.setDurationInSeconds(eventDto.getDurationInSeconds());
        event.setLatitude(eventDto.getLatitude());
        event.setLongitude(eventDto.getLongitude());
        event.setDescription(eventDto.getDescription());
        event.setColor(eventDto.getColor());
        return eventsRepository.save(event).getId();
    }

    public List<Event> getNearbyEvents(AppUser currentUser, double latitude, double longitude) {
        return eventsRepository.findByDateTimeIsAfter(LocalDateTime.now().minusHours(1)).stream().filter((event) ->{
            return event.getParticipants().stream().anyMatch((par) -> par.getId().equals(currentUser.getId()))
                    || event.getOwnerId().equals(currentUser.getId());
        }).collect(Collectors.toList());
    }

    public void addParticipant(Long eventId, Long userId) {
        Event event = eventsRepository.findById(eventId).orElseThrow();
        event.getParticipants().add(appUserService.getById(userId).orElseThrow());
        eventsRepository.save(event);
    }

    public void addParticipants(Long eventId, List<Long> userIds) {
        Event event = eventsRepository.findById(eventId).orElseThrow();
        List<AppUser> users = userIds.stream().map(id -> appUserService.getById(id).orElseThrow()).toList();
        event.getParticipants().addAll(users);
        eventsRepository.save(event);
    }

    public void deleteAllEvents() {
        eventsRepository.deleteAll();
    }
}
