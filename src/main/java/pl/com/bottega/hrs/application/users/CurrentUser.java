package pl.com.bottega.hrs.application.users;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Component
@SessionScope
public class CurrentUser {

    private Integer userId;
    private UserRepository userRepository;

    public CurrentUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(User user) {
        this.userId = user.getId();
    }

    public void logout() {
        this.userId = null;
    }

    public Optional getUserInfo() {
        if (this.userId == null) {
            return Optional.empty();
        }
        User user = userRepository.get(this.userId);
        return Optional.of(new UserDto(user));
    }

    public boolean isAuthenticated(Role[] requiredRoles) {
        if (userId == null) {
            return false;
        }
        if (requiredRoles == null || requiredRoles.length == 0) {
            return true;
        }
        User user = userRepository.get(this.userId);
        return user.hasRoles(requiredRoles);
    }
}
