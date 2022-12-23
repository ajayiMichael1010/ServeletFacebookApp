package com.facebook.dao;
import com.facebook.dao.dto.UserDto;
import com.facebook.util.models.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User saveUser(UserDto userDto);
    User saveUser(String name, String email, String gender, String username, String password, Object createdAt);
    User authenticateUser(String username, String password);
    List<User> getAllUsers();
    Optional<User> getUserById(long userId);
}

