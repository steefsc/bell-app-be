package com.sheensoft.persistance.service;

import com.sheensoft.domain.CustomUserDetails;
import com.sheensoft.domain.UserProfile;
import com.sheensoft.persistance.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByUsername(userName);
        if (userProfile == null){
            throw new UsernameNotFoundException("User Not found");
        } else {
            return new CustomUserDetails(userProfile);
        }
    }

    public UserDetails loadUserByUsernameAndPassword(String userName, String password) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByUsername(userName);
        if (userProfile == null){
            throw new UsernameNotFoundException("User Not found");
        } else {
            return new CustomUserDetails(userProfile);
        }
    }


}
