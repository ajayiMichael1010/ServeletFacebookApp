<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.facebook.util.models.Post" %>
<%@ page import="com.facebook.dao.PostDao" %>
<%@ page import="com.facebook.dao.dto.impl.PostDaoImp" %>
<%
    HttpSession ses= request.getSession();
   if(ses.getAttribute("userID")==null){response.sendRedirect("login.jsp");
       System.out.println(ses.getAttributeNames().toString());

    }

    //List<Post> allPosts= (ArrayList<Post>)request.getAttribute("data");
    %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard </title>
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
    <div class="facebook-post-container" style="padding: 15px;">
        <form action="/PostServlet" method="post">
            <div class="comment-box">
                <div class="comment-owner-dp"><img src="asset/images/user-icon.png"></div>
                <div class="form-element-group">
                    <textarea name="ownerPost" class="owner-comment" rows="2" placeholder="What is on your mind"></textarea>
                    <button type="submit" class="comment-button">Send</button>
                </div>
            </div>
            <input type="hidden" name="userID" value="<%=ses.getAttribute("userID")%>">
        </form>

    </div>
    <br>
    <%
        PostDao postDao= null;
        postDao = new PostDaoImp();

      List <Post> allPost=postDao.getAllPost();
        for(int i=0; i<allPost.size();i++){
            int postID= allPost.get(i).getId();
            int totalComments=postDao.getPostComment(postID).size();
            int totalLikes=postDao.getPostLike(postID).size();
    %>
    <div class="facebook-post-container" style="padding: 0 10px 0 10px;">
        <div class="post-head">
            <div class="post-owner">
                <div class="post-owner-dp"><img src="asset/images/user-icon.png"></div>
                <div class="post-owner-name"><h4><%=allPost.get(i).getPostedBy()%></h4></div>
            </div>
            <div class="post-date">
                <h5 style="color:green">Posted on <span style="color: black"><%=allPost.get(i).getCreatedAt()%></></span></h5>
            </div>
        </div>
        <div class="post-details">
            <a href="/singlepost.jsp?postID=<%=postID%>" style="color:#222;"> <%=allPost.get(i).getPostDetails()%></a>
        </div>
        <div class="post-like-comment">
            <div class="likes"> <span style="color:blue;font-size: 13px" class="glyphicon glyphicon-thumbs-up"></span> <%=totalLikes==0?"":totalLikes==1?totalLikes+" like":totalLikes+" likes"%></div>
            <div class="comments"><%=totalComments==0?" No comment yet ":totalComments>1?totalComments+" comments":totalComments+" comment" %></div>
        </div>

        <div class="like-comment-action">
            <div class="like-this"><a href="/LikeServlet?userID=<%=ses.getAttribute("userID")%>&&postID=<%=postID%>&&commentID=0"><span class="glyphicon glyphicon-thumbs-up"><span></a></div>
            <div class="comment-on-this"><span class="glyphicon glyphicon-comment"></span></div>
        </div>
        <form action="/CommentServlet" method="post">
            <div class="comment-box">
                <div class="comment-owner-dp"><img src="asset/images/user-icon.png"></div>
                <div class="form-element-group">
                    <textarea name="owner_comment" class="owner-comment" rows="2" placeholder="Write a comment"></textarea>
                    <button type="submit" class="comment-button">Send</button>
                </div>
            </div>
            <input type="hidden" name="commentPage" value="0">
            <input type="hidden" value="<%=ses.getAttribute("userID")%>" name="userID">
            <input type="hidden" value="<%=allPost.get(i).getId()%>" name="postID">
        </form>

    </div>
<br>
    <%}%>
</div>
</div>

</body>
</html>