package com.facebook.realtimeapp;

import com.facebook.dao.CommentDao;
import com.facebook.dao.dto.impl.commentDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CommentServlet", value = "/CommentServlet")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID= Integer.parseInt(request.getParameter("userID"));
        int postID= Integer.parseInt(request.getParameter("postID"));
        String comment=request.getParameter("owner_comment");
        CommentDao commentDao= null;
        try {
            commentDao = new commentDaoImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        commentDao.createComment(userID,postID,comment);
        int fromCommentPage= Integer.parseInt(request.getParameter("commentPage"));
        if(fromCommentPage==1){
            response.sendRedirect("/singlepost.jsp?postID="+fromCommentPage);
        }
        else{
            response.sendRedirect("/index.jsp");
        }


    }
}
