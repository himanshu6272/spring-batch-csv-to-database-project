package com.csvtodb.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
}
