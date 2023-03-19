package com.asheeque.springboot.ToDo.service;

import com.asheeque.springboot.ToDo.dto.request.SigninRequest;
import com.asheeque.springboot.ToDo.dto.request.SignupRequest;
import com.asheeque.springboot.ToDo.dto.response.JwtResponse;
import com.asheeque.springboot.ToDo.model.ERole;
import com.asheeque.springboot.ToDo.model.Role;
import com.asheeque.springboot.ToDo.model.User;
import com.asheeque.springboot.ToDo.repository.RoleRepository;
import com.asheeque.springboot.ToDo.repository.UserRepository;
import com.asheeque.springboot.ToDo.security.PasswordConfig;
import com.asheeque.springboot.ToDo.security.jwt.JwtUtils;
import com.asheeque.springboot.ToDo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordConfig passwordConfig;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public JwtResponse registerUser(SignupRequest signupRequest) {

        PasswordEncoder passwordEncoder = passwordConfig.passwordEncoder();
        User newUser = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> userRolls = signupRequest.getRole();

        Set<Role> roles = new HashSet<>();
        for(String role:userRolls){
            Role currentRole;
            switch (role){
                case "ROLE_ADMIN":
                    currentRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(currentRole);
                    break;
                default :

                    currentRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(currentRole);
                    break;
            }



        }
        newUser.setRoles(roles);
        userRepository.save(newUser);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Get user roles
        List<String> userRoles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Return JwtResponse
        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), userDetails.getId(), userRoles);
//        return newUser;
    }

    @Override
    public JwtResponse loginUser(SigninRequest signinRequest) {

//        User user = userRepository.findByUsername("test4");


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        for(String role : roles){
            System.out.println(role);
        }
//        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
//        System.out.println(jwt);
        return new JwtResponse(jwt,userDetails.getUsername(),userDetails.getEmail(),userDetails.getId(),roles);


    }
}
