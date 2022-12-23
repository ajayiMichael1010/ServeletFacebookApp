package com.facebook.dao;

import com.facebook.util.models.Like;
import com.facebook.util.models.User;

import java.util.List;

public interface CommentDao {
    boolean createComment(int userId,int postId, String comment);

}
