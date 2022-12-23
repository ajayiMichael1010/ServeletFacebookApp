package com.facebook.dao.dto.impl;
import com.facebook.dao.CommentDao;
import com.facebook.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class commentDaoImpl implements CommentDao {
    private Connection connection= DatabaseConnection.INSTANCE.getDataSource().getConnection();
    public commentDaoImpl() throws SQLException {
    };
    @Override
    public boolean createComment(int userId,int postId, String comment) {

        boolean isSaved=false;
        try{
            PreparedStatement statement=connection.prepareStatement("INSERT INTO comments(user_id,post_id,comment,created_at)VALUES (?,?,?,?)");
            statement.setInt(1,userId);
            statement.setInt(2,postId);
            statement.setString(3,comment);
            statement.setObject(4, LocalDateTime.now());
            int  result= statement.executeUpdate();
            isSaved=result>0?true:false;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return isSaved;

    }
}
