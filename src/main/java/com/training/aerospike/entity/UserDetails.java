package com.training.aerospike.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDetails {

    private String id;

    @BinName(value = "firstName")
    private String firstName;

    @BinName(value = "lastName")
    private String lastName;

    @BinName(value = "dob")
    private String dob;

    @BinName(value = "username")
    private String username;

    @BinName(value = "password")
    private String password;

    @BinName(value = "gender")
    private String gender;

    @BinName(value = "region")
    private String region;

    @BinName(value = "interests")
    private List<String> interests;

}
