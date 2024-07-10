package com.performance.java_api.resolvers;

import com.performance.java_api.operations.UserService;
import models.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;

    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }
}
