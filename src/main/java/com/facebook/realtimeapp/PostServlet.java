package com.facebook.realtimeapp;

import com.facebook.dao.PostDao;
import com.facebook.dao.dto.impl.PostDaoImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends HttpServlet {
    private  PostDao postDao= new PostDaoImp();

    public PostServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//          request.setAttribute("data", postDao.getAllPost());
//        request.getRequestDispatcher("index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int userID= Integer.parseInt(request.getParameter("userID"));
            String post=request.getParameter("ownerPost");

             postDao.createPost(userID,post);
             response.sendRedirect("/index.jsp");

    }
}
