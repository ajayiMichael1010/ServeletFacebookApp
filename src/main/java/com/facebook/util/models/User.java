package com.facebook.util.models;
import lombok.*;
@Data
public class User {
    private  final int id;
    private final String name;
    private  final String email;
    private final String gender;
    private final String username;
    private final  String password;
    private final Object created_at;

}


