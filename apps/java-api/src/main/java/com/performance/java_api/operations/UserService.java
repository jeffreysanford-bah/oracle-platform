package com.performance.java_api.operations;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import models.User;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final String NESTJS_API_URL = "http://localhost:3000/api/users?limit=1000";    

    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(NESTJS_API_URL, User[].class);

        return Arrays.asList(users);
    }

    public User getUserById(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String userApiUrl = NESTJS_API_URL + "&userId=" + userId;
        return restTemplate.getForObject(userApiUrl, User.class);
    }
}
