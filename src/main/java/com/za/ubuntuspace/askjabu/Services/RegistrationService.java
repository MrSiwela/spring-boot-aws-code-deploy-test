package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.RegistrationRequest;
import com.za.ubuntuspace.askjabu.Entities.Role;
import com.za.ubuntuspace.askjabu.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {

    private final UserDetailsServiceImpl userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegistrationService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public User register(RegistrationRequest registrationRequest) {
//        UserDetails userExists = userDetailsService.loadUserByUsername(registrationRequest.getEmail());
//        if(userExists != null) {throw new IllegalStateException("email already exists");}

        User regUser = new User();
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1,"USER"));
        regUser.setFullName(registrationRequest.getFullName());
        regUser.setEmail(registrationRequest.getEmail());
//        regUser.setRoles(roles);

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        regUser.setPassword(encodedPassword);

        System.out.println(regUser.getEmail());
        System.out.println(regUser.getPassword());

        return userDetailsService.signUpUser(regUser);
    }

    public User encodeUserPassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getEmail());
        user.setPassword(encodedPassword);
        return user;
    }
}
