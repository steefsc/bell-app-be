package com.sheensoft.web.controllers;

import com.sheensoft.domain.Rate;
import com.sheensoft.domain.UserProfile;
import com.sheensoft.persistance.repository.UserProfileRepository;
import com.sheensoft.persistance.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Payload;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private RateService rateService;

    @GetMapping("/")
    public String home(){
        return "Hello";
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/sec/users")
    public List<UserProfile> getUsers(){
        List<UserProfile> userProfiles = this.userProfileRepository.findAll();
        return userProfiles;
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/rate", consumes = "application/json", produces = "application/json")
    public Rate getRate(@RequestBody Map<String, Object> payload) {
        Rate rates = this.rateService.getUpdatedRate((String) payload.get("from"), (String) payload.get("to"));
        return rates;
    }
}
