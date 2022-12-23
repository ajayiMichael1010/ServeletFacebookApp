package com.facebook.dao.dto.impl;

import com.facebook.dao.LikeDoa;
import com.facebook.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LikeDoaImpl implements LikeDoa {
    private Connection connection= DatabaseConnection.INSTANCE.getDataSource().getConnection();

    public LikeDoaImpl() throws SQLException {
    }

    @Override
    public boolean createLike(int userId, int postId, int commentId) {
        //CHECK IF USER HAS ALREADY LIKED THE POST
        boolean isLikedByTheUser=postIsLikedByUser(userId, postId,commentId);
        boolean isSaved=false;
        if(!isLikedByTheUser){
            try{
                PreparedStatement statement=connection.prepareStatement("INSERT INTO likes(user_id,post_id,comment_id,created_at)VALUES (?,?,?,?)");
                statement.setInt(1,userId);
                statement.setInt(2,postId);
                statement.setInt(3,commentId);
                statement.setObject(4, LocalDateTime.now());
                int  result= statement.executeUpdate();
                isSaved=result==1?true:false;

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return isSaved;
    }

    private boolean postIsLikedByUser(int userID, int postID,int commentID){
        try{
            String likeRecord= "";
            String deleteLike="";
            if(commentID>0){
                likeRecord="SELECT user_id FROM likes WHERE user_id=? AND post_id=? AND comment_id=?";
                deleteLike="DELETE FROM likes WHERE user_id=? AND post_id=? OR comment_id=?";
            }
            else{
                likeRecord="SELECT user_id FROM likes WHERE user_id=? AND post_id=?";
                deleteLike="DELETE FROM likes WHERE user_id=? AND post_id=?";
            }

            PreparedStatement statement=connection.prepareStatement(likeRecord);
            statement.setInt(1,userID);
            statement.setInt(2,postID);
            if(commentID>0){statement.setInt(3,commentID);}
            ResultSet resultSet= statement.executeQuery();
            if (resultSet.next()){
                PreparedStatement statement2= connection.prepareStatement(deleteLike);
                statement.setInt(1,userID);
                statement.setInt(2,postID);
                if(commentID>0){statement.setInt(3,commentID);
                }
                 ResultSet resultSet1=statement.executeQuery();
                return  true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
