package io.github.reconsolidated.meetupapi.friends;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Friends {
    @Id
    private Long id;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<AppUser> friendList = new ArrayList<>();

    public Friends(Long id) {
        this.id = id;
    }
}
