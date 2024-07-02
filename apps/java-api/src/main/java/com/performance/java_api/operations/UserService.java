package com.performance.java_api.operations;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final String NESTJS_API_URL = "http://localhost:3000/api/users?limit=1000";

    public List<User> fetchUsersFromNestJS() {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(NESTJS_API_URL, User[].class);
        return Arrays.asList(users);
    }

    public List<models.User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public models.User findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
