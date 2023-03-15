package com.asheeque.springboot.ToDo.security.services;

import com.asheeque.springboot.ToDo.model.Role;
import com.asheeque.springboot.ToDo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private User user;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public  String getEmail(){return user.getEmail();}

    public Long getId(){return user.getUser_id();}

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
