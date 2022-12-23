package com.facebook;
import com.facebook.dao.CommentDao;
import com.facebook.dao.LikeDoa;
import com.facebook.dao.PostDao;
import com.facebook.dao.UserDao;
import com.facebook.dao.dto.impl.LikeDoaImpl;
import com.facebook.dao.dto.impl.PostDaoImp;
import com.facebook.dao.dto.impl.UserDaoImpl;
import com.facebook.dao.dto.impl.commentDaoImpl;
import com.facebook.util.models.Post;
import com.facebook.util.models.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class App {
    private static UserDao userDao = new UserDaoImpl();
    private  PostDao  postDao = new PostDaoImp();

    public App() throws SQLException {
    }


    public static void main(String[] args) {
//        User user= userDao.saveUser("Paul Israel","paujnnkkl@gmail.com","male",
//                "pajnnjully","12345678", LocalDateTime.now());
//         System.out.println(user);
//
//        userDao.getAllUsers().
//                forEach(System.out::println);

       // boolean isInserted=postDao.createPost(1,"THIS IS MY FIRST POST");
        //System.out.println(isInserted);
       // System.out.println(postDao.getAllPost().size());
       // Post post= new Post();
        //postDao.getPostById(1,post);
       // System.out.println(post.getPostedBy());

        //CommentDao commentDao= new commentDaoImpl();
        //System.out.println(commentDao.createComment(1,1,"This is my first comment"));

        //LikeDoa likeDoa= new LikeDoaImpl();
        //System.out.println(likeDoa.createLike(1,1,1));
    }
}