package com.acornworks.customer.controllers;

import com.acornworks.customer.domains.UserData;
import com.acornworks.customer.domains.UserInfo;
import com.acornworks.customer.repositories.UserDataRepository;
import com.acornworks.customer.repositories.UserInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/allUsers")
    public List<UserInfo> getAllUsers() {
        final List<UserInfo> allUsers = new ArrayList<>();
        userInfoRepository.findAll().forEach(allUsers::add);

        return allUsers;
    }

    @GetMapping("/sync/{user}")
    public UserData syncCurrencyData(@PathVariable("user") String userName) {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);

        if (userInfo == null) {
            userInfo = userInfoRepository.save(new UserInfo(userName));
        }

        final JsonNode priceNode = restTemplate.getForObject("http://localhost:65080/quote/AUDKRW=X", JsonNode.class);
        double price = priceNode.get("price").asDouble();

        UserData userData = new UserData(userName, (float)price);
        userData = userDataRepository.save(userData);

        return userData;
    }
}
