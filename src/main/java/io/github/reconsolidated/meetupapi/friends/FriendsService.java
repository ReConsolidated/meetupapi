package io.github.reconsolidated.meetupapi.friends;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final AppUserService appUserService;

    public void addFriend(Long friend1, Long friend2) {
        if(Objects.equals(friend1, friend2)) {
            throw new IllegalStateException("Cannot add yourself as a friend!");
        }
        Friends friends = friendsRepository.findById(friend1).orElse(new Friends(friend1));
        friends.getFriendList().add(appUserService.getById(friend2).orElseThrow());
        Friends friends2 = friendsRepository.findById(friend2).orElse(new Friends(friend2));
        friends2.getFriendList().add(appUserService.getById(friend1).orElseThrow());
        friendsRepository.save(friends);
        friendsRepository.save(friends2);
    }

    public List<AppUser> getFriends(Long id) {
        Optional<Friends> friendsOptional = friendsRepository.findById(id);
        if (friendsOptional.isPresent()) {
            return friendsOptional.get().getFriendList();
        }
        return new ArrayList<>();
    }

    public boolean areFriends(Long friend1, Long friend2) {
        Friends friends1 = friendsRepository.findById(friend1).orElse(new Friends(friend1));
        Friends friends2 = friendsRepository.findById(friend2).orElse(new Friends(friend2));
        return friends1.getFriendList().stream().anyMatch(friend -> friend.getId().equals(friend2))
                || friends2.getFriendList().stream().anyMatch(friend -> friend.getId().equals(friend1));
    }
}
