package com.facebook.realtimeapp;

import com.facebook.dao.LikeDoa;
import com.facebook.dao.dto.impl.LikeDoaImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LikeServlet", value = "/LikeServlet")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int userID= Integer.parseInt(request.getParameter("userID"));
            int postID= Integer.parseInt(request.getParameter("postID"));
            int commentID= Integer.parseInt(request.getParameter("commentID"));

        LikeDoa likeDoa= null;
        try {
            likeDoa = new LikeDoaImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        likeDoa.createLike(userID,postID,commentID);
        response.sendRedirect("/index.jsp");

        System.out.println("userid"+ userID+ "postID"+ postID+ " commentID"+ commentID);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
