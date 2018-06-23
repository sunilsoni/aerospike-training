package com.training.aerospike.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Users {

    private String id;

    @BinName(value="firstName")
    private String firstName;

    @BinName(value="lastName")
    private String lastName;

    @BinName(value="dob")
    private String dob;

}
