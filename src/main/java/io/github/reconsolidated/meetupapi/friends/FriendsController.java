package io.github.reconsolidated.meetupapi.friends;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.currentUser.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@AllArgsConstructor
public class FriendsController {
    private final FriendsService friendsService;

    @GetMapping("/get_friends")
    public ResponseEntity<List<OtherUserDto>> getFriends(@CurrentUser AppUser user) {
        return ResponseEntity.ok(friendsService.getFriends(
                user.getId()).stream()
                .map(
                friend -> new OtherUserDto(friend.getId(), friend.getFirstName(), friend.getLastName()))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> addFriend(@CurrentUser AppUser user, @RequestParam Long friendId) {
        if (friendsService.areFriends(user.getId(), friendId)) {
            throw new IllegalArgumentException("These users are already friends: %d, %d".formatted(user.getId(), friendId));
        }
        friendsService.addFriend(user.getId(), friendId);
        return ResponseEntity.ok().build();
    }
}
