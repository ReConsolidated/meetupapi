package io.github.reconsolidated.meetupapi.friends;

import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUser;
import io.github.reconsolidated.meetupapi.authentication.AppUser.AppUserService;
import io.github.reconsolidated.meetupapi.authentication.currentUser.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@AllArgsConstructor
public class FriendsController {
    private final FriendsService friendsService;
    private final AppUserService appUserService;

    @GetMapping("/get_friends")
    public ResponseEntity<List<OtherUserDto>> getFriends(@CurrentUser AppUser user) {
        return ResponseEntity.ok(friendsService.getFriends(
                user.getId()).stream()
                .map(
                friend -> new OtherUserDto(friend.getId(), friend.getFirstName(), friend.getLastName(), friend.getEmail()))
                .collect(Collectors.toList()));
    }

    @PostMapping("/add_friend")
    public ResponseEntity<?> addFriend(@CurrentUser AppUser user, @RequestParam String friendEmail) {
        friendEmail = friendEmail.replace("%40", "@");
        Optional<AppUser> friend = appUserService.getByEmail(friendEmail);
        if (friend.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (friendsService.areFriends(user.getId(), friend.get().getId())) {
            throw new IllegalArgumentException("These users are already friends: %d, %d".formatted(user.getId(), friend.get().getId()));
        }
        friendsService.addFriend(user.getId(), friend.get().getId());
        return ResponseEntity.ok().build();
    }
}
