package io.github.reconsolidated.meetupapi.Events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {
    List<Event> findByDateTimeIsAfter(LocalDateTime dateTime);

}
