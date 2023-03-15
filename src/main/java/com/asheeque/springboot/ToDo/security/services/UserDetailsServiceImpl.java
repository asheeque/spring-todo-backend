package com.asheeque.springboot.ToDo.security.services;

import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        System.out.println(username);
        if (user == null){
            throw new UsernameNotFoundException("Not found");
        }
        return new UserDetailsImpl(user);
    }
}
