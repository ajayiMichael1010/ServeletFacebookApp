package com.facebook.dao.dto.impl;
import com.facebook.dao.UserDao;
import com.facebook.dao.dto.UserDto;
import com.facebook.util.DatabaseConnection;
import com.facebook.util.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    final String registerUser= "INSERT INTO users (fullName,email,gender,username,password,created_at)"+"VALUES(?,?,?,?,?,?)";
    @Override
    public User saveUser(String name, String email, String gender, String username, String password, Object createdAt) {
        User savedUser=null;
        try(Connection connection= DatabaseConnection.INSTANCE.getDataSource().getConnection()){
            PreparedStatement statement= connection.prepareStatement(registerUser, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,gender);
            statement.setString(4,username);
            statement.setString(5,password);
            statement.setObject(6,createdAt);
            int result= statement.executeUpdate();
            final ResultSet keyResultSet= statement.getGeneratedKeys();
            keyResultSet.next();
            final int autogeneratedId= keyResultSet.getInt(1);

            savedUser=result==1? new User(autogeneratedId,"Folorunso Alabi","alabi@gmail.com", "male","alabi","12345678",LocalTime.now()):null;
            System.out.println(result);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return savedUser;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User savedUser=null;
        try(Connection connection= DatabaseConnection.INSTANCE.getDataSource().getConnection()){
            PreparedStatement statement= connection.prepareStatement(registerUser,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,userDto.getName());
            statement.setString(2, userDto.getEmail());
            statement.setString(3,userDto.getGender());
            statement.setString(4,userDto.getUsername());
            statement.setString(5,userDto.getPassword());
            statement.setObject(6,userDto.getCreatedAt());

            int result= statement.executeUpdate();

            final ResultSet keyResultSet= statement.getGeneratedKeys();
            keyResultSet.next();
            final int autogeneratedId= keyResultSet.getInt(1);

            savedUser=result==1? new User(autogeneratedId,userDto.getName(),userDto.getEmail(),
                    userDto.getGender(),userDto.getUsername(),userDto.getPassword(),userDto.getCreatedAt()):null;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  savedUser;
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {

        List<User> returnData = new ArrayList<>();

        try(Connection connection = DatabaseConnection.INSTANCE.getDataSource().getConnection()){

            PreparedStatement statement = connection.prepareStatement("select * from user");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String gender = resultSet.getString("gender");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Object createdAt=resultSet.getObject("created_at");

                returnData.add(new User(id,name,email,gender,username,password,createdAt));

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return returnData;
    }


    public User authenticateUser(String username, String password){
        User user=new User(0,"","","","","","");
        try(Connection connection=DatabaseConnection.INSTANCE.getDataSource().getConnection()){

            PreparedStatement statement= connection.prepareStatement("SELECT * FROM users WHERE (username=? || email=?) && password=?");
            statement.setString(1,username);
            statement.setString(2,username);
            statement.setString(3,password);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
                int id= resultSet.getInt("id");
                String fullName=resultSet.getString("fullName");
                String email= resultSet.getString("email");
                String gender= resultSet.getString("gender");
                Object dateCreated= resultSet.getObject("created_at");
                user= new User(id,fullName,email,gender,username,password,dateCreated);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }


}