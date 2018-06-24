package com.training.aerospike.fixtures;

import com.training.aerospike.entity.UserDetails;

import java.util.Arrays;
import java.util.UUID;

public class FixtureBuilder {

    public static UserDetails buildUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Test");
        userDetails.setLastName("Test");
        userDetails.setPassword("password");
        userDetails.setUsername("sunil");
        userDetails.setGender("m");
        userDetails.setDob("12121222");
        userDetails.setRegion("west");

        String interests = "Basketball,Computing,Mentoring,Travelling,Community, Volunteer or Charity Work";
        userDetails.setInterests(Arrays.asList(interests.split(",")));

        String uuid = UUID.randomUUID().toString();
        userDetails.setId(uuid);

        return userDetails;
    }
}
