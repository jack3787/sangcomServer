package capstone.sangcom.controller.api;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.User;
import capstone.sangcom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public SimpleResponse register(@RequestBody User user) {
        if(userService.findById(user.getId()) != null)
            return new SimpleResponse(false);

        if(userService.register(user) == null)
            return new SimpleResponse(false);

        return new SimpleResponse(true);

    }
}
