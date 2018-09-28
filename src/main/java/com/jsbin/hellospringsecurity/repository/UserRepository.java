package com.jsbin.hellospringsecurity.repository;

import com.jsbin.hellospringsecurity.domain.Role;
import com.jsbin.hellospringsecurity.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private Collection<Role> roles = new HashSet<>();

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_admin");
        roles.add(role);
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setId((i + 1));
            user.setAge((18 + i));
            user.setUserName("jason.zou->" + (i + 1));
            user.setRoleSet(roles);
            users.add(user);
        }
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }
}
