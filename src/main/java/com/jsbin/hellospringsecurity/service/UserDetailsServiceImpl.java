package com.jsbin.hellospringsecurity.service;

import com.jsbin.hellospringsecurity.domain.User;
import com.jsbin.hellospringsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            System.out.println("User is not found.");
            throw new UsernameNotFoundException("Can not get user by specified username" + username);
        }
        return user;
    }
}
