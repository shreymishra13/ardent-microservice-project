package com.ardent.userService.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    private String addressLine;
    private String city ;
    private State state;
    private String country;
    private String pincode;
}
