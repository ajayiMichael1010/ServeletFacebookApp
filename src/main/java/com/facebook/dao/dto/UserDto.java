package com.facebook.dao.dto;
import lombok.Data;
@Data
public class UserDto {

    private  final int id;
    private final String name;
    private  final String email;
    private final String gender;
    private final String username;
    private final  String password;
    private final Object createdAt;
}
