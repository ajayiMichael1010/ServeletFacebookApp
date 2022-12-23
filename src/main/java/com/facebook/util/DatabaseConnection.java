package com.facebook.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public enum DatabaseConnection {
    INSTANCE;
    public DataSource getDataSource(){
        //MysqlDataSource dataSource= new MysqlXADataSource();
        HikariDataSource dataSource= new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost/chat_app?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        return  dataSource;
    }
    public boolean isConnectionValid(){
        try (Connection connection = getDataSource().getConnection()){
            return connection.isValid(0);
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
