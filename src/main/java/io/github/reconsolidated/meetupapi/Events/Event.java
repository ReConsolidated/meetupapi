package io.github.reconsolidated.meetupapi.Events;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Generated
    @Id
    private Long id;
    private String name;
    private Long ownerId;
    private LocalDateTime dateTime;
    private long durationInSeconds;
    private double latitude;
    private double longitude;
}
