package com.facebook.dao.dto.impl;

import com.facebook.dao.PostDao;
import com.facebook.util.DatabaseConnection;
import com.facebook.util.models.Comment;
import com.facebook.util.models.Like;
import com.facebook.util.models.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImp implements PostDao {
    final String createNewPost="INSERT INTO posts(user_id,post,created_at)VALUES(?,?,?)";
    private Connection connection= DatabaseConnection.INSTANCE.getDataSource().getConnection();

    public PostDaoImp() throws SQLException {
    }

    @Override
    public boolean createPost(int userID, String post) {
        boolean isPostSaved=false;
        try{
            PreparedStatement statement= connection.prepareStatement(createNewPost,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,userID);
            statement.setString(2,post);
            statement.setObject(3, LocalDateTime.now());
            int result= statement.executeUpdate();
            isPostSaved=result==1?true:false;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isPostSaved;
    }

    @Override
    public List<Post> getAllPost() {
        List <Post> allPosts= new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM posts INNER JOIN users ON users.id=posts.user_id");
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                int id= resultSet.getInt("id");
                int user_id=resultSet.getInt("user_id");
                String postDetails=resultSet.getString("post");
                String postedBy=resultSet.getString("fullName");
                String createdAt=resultSet.getString("created_at");
                allPosts.add(new Post(id,user_id,postDetails,postedBy,createdAt));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return  allPosts;
    }

    @Override
    public Post getPostById(int postId) {
        Post post= new Post();
        try{
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM posts JOIN users ON users.id=posts.user_id WHERE posts.id=?");
            statement.setInt(1,postId);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
                int Id= resultSet.getInt("id");
                int userId=resultSet.getInt("user_id");
                String postDetails=resultSet.getString("post");
                String postedBy= resultSet.getString("fullName");
                String createdAt= resultSet.getString("created_at");
                System.out.println(postedBy);
                post= new Post(Id,userId,postDetails,postedBy,createdAt);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("postedBy");
        return post;
    }

    public  List<Comment> getPostComment(int postId){
        List<Comment>commentList= new ArrayList<>();
        try{
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM  comments JOIN users ON users.id=comments.user_id WHERE post_id=?");
            statement.setInt(1,postId);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt("id");
                int userId=resultSet.getInt("user_id");
                int postID=resultSet.getInt("post_id");
                String commentedBy=resultSet.getString("fullName");
                String comment= resultSet.getString("comment");
                String commentDate= resultSet.getString("created_at");
                commentList.add(new Comment(id,postID,userId,comment,commentedBy,commentDate));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return commentList;
    }
    public  List<Like> getPostLike(int postId){
        List<Like>likeList= new ArrayList<>();
        try{
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM  likes JOIN users ON users.id=likes.user_id WHERE post_id=?");
            statement.setInt(1,postId);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                int userId=resultSet.getInt("user_id");
                String likedBy=resultSet.getString("fullName");
               int commentId= resultSet.getInt("id");
                likeList.add(new Like(id,userId,postId,commentId,likedBy));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return likeList;
    }
}
