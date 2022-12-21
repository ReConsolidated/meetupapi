package io.github.reconsolidated.meetupapi.Events;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AddEventDto {
    @NotNull
    private String name;
    @NotNull
    private Long ownerId;
    @NotNull
    private LocalDateTime date;
    private long durationInSeconds;
}
