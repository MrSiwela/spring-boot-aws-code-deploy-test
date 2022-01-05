package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.MyUserDetails;
import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Repositories.VendorRepository;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    public User signUpUser(User user){
        return userRepository.save(user);
    }
}
