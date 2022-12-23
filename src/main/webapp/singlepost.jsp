<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.facebook.util.models.Post" %>
<%@ page import="com.facebook.dao.PostDao" %>
<%@ page import="com.facebook.dao.dto.impl.PostDaoImp" %>
<%@ page import="com.facebook.util.models.Comment" %>
<%
  HttpSession ses= request.getSession();
  if(ses.getAttribute("userID")==null){response.sendRedirect("login.jsp");
    System.out.println(ses.getAttributeNames().toString());

  }

%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Single Post</title>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <link rel="stylesheet" href="asset/css/facebook-style.css">
</head>
<body style="padding: 0 !important;">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/index.jsp" style="font-size: 25px;color: blue">Facebook</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><%= ses.getAttribute("fullName")%></a></li>
        <li class="dropdown">
          <a href="/LogoutServlet" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Log Out <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="login.jsp">Log Out</a></li>

          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
  <div class="facebook-container-dashboard" style="padding: 0;">
    <%
      PostDao postDao= null;
      postDao = new PostDaoImp();

      int postID= Integer.parseInt(request.getParameter("postID"));
      Post singlePost=postDao.getPostById(postID);
        int totalComments=postDao.getPostComment(postID).size();
        int totalLikes=postDao.getPostLike(postID).size();
    %>
    <div class="facebook-post-container" style="padding: 0 10px 0 10px;">
      <div class="post-head">
        <div class="post-owner">
          <div class="post-owner-dp"><img src="asset/images/user-icon.png"></div>
          <div class="post-owner-name"><h4><%=singlePost.getPostedBy()%></h4></div>
        </div>
        <div class="post-date">
          <h5 style="color:green">Posted on <span style="color: black"><%=singlePost.getCreatedAt()%></></span></h5>
        </div>
      </div>
      <div class="post-details">
        <%=singlePost.getPostDetails()%>
      </div>
      <div class="post-like-comment">
        <div class="likes"> <span style="color:blue;font-size: 13px" class="glyphicon glyphicon-thumbs-up"></span> <%=totalLikes==0?"":totalLikes==1?totalLikes+" like":totalLikes+" likes"%></div>
        <div class="comments"><%=totalComments==0?" No comment yet ":totalComments>1?totalComments+" comments":totalComments+" comment" %></div>
      </div>
      <%
        List<Comment> comments= postDao.getPostComment(postID);
        for(int i=0;i<comments.size();i++){
          String singleComment= comments.get(i).getComment();
          String commentBy= comments.get(i).getCommentedBy();
      %>
      <div class="all-comments" style="display: flex;justify-content:left">
        <div class="user-image"><img src="asset/images/user-icon.png" style="width:45px;"></div>
        <div class="post-comments">
          <small><%=commentBy%></small><br>
          <span style="font-size:20px;"><%=singleComment%></span>
        </div>
      </div>
      <%}%>
      <hr>
      <form action="/CommentServlet" method="post">
        <div class="comment-box">
          <div class="comment-owner-dp"><img src="asset/images/user-icon.png"></div>
          <div class="form-element-group">
            <textarea name="owner_comment" class="owner-comment" rows="2" placeholder="Write a comment"></textarea>
            <button type="submit" class="comment-button">Send</button>
          </div>
        </div>
        <input type="hidden" name="commentPage" value="1">
        <input type="hidden" value="<%=ses.getAttribute("userID")%>" name="userID">
        <input type="hidden" value="<%=singlePost.getId()%>" name="postID">
      </form>

    </div>

  </div>
</div>

</body>
</html>