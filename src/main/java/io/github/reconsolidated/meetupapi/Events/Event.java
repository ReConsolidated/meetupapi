package io.github.reconsolidated.meetupapi.Events;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private Long ownerId;
    private LocalDateTime dateTime;
    private String date;
    private long durationInSeconds;
    private double latitude;
    private double longitude;
    private String description;
    private String color;
    @ManyToMany
    @JoinColumn(name = "events_participants")
    private List<AppUser> participants = new ArrayList<>();
}
