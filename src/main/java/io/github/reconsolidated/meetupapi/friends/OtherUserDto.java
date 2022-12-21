package io.github.reconsolidated.meetupapi.friends;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OtherUserDto {
    private Long id;
    private String firstName;
    private String surname;
}
