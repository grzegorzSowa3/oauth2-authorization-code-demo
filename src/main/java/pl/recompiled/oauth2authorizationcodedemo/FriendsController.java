package pl.recompiled.oauth2authorizationcodedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("friends")
@RequiredArgsConstructor
public class FriendsController {

    private final FriendsProvider friendsProvider;

    @GetMapping
    public String friendsSite(Model model) {
        model.addAttribute("friends", friendsProvider.getFriends());
        return "friends";
    }

}
