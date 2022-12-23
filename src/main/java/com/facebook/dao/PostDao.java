package com.facebook.dao;

import com.facebook.dao.dto.UserDto;
import com.facebook.util.models.Comment;
import com.facebook.util.models.Like;
import com.facebook.util.models.Post;
import com.facebook.util.models.User;

import java.util.ArrayList;
import java.util.List;

public interface PostDao {
    boolean createPost(int userID, String post);
    List<Post> getAllPost();
    Post getPostById(int postId);
     List<Comment> getPostComment(int postId);
    List<Like> getPostLike(int postId);
}
