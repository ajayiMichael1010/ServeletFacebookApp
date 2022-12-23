<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 01/11/2022
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%
    HttpSession ses= request.getSession();
    if(ses.getAttribute("userID")!=null){response.sendRedirect("index.jsp");
        System.out.println(ses.getAttributeNames().toString());
    }
    Object wrongCredential=session.getAttribute("errorMessage");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN PAGE </title>
    <link rel="stylesheet" href="asset/css/facebook-style.css">

</head>
<body>

<div class="facebook-form-container">
    <div class="facebook-banner">
        <h1 class="facebook-text-logo">Facebook</h1>
        <div class="facebook-form-description"><span>Facebook helps you connect and share<br>
        with people in your life</span></div>
    </div>

    <div class="facebook-form">
        <form action="/LoginServlet" method="post" class="facebookFormElements">
        <input type="username" placeholder="Username" name="username" required>
        <input type="password" placeholder="Password" name="password" required>
        <button type="submit">Login</button>${error}
            <div class="error"><span style="color:red;font-size: medium;"><%=wrongCredential!=null?wrongCredential:""%></span></div>
        <div class="forgotten-password"><span>Forgotten Password</span></div>
        <hr color="gray">
        <button class="facebook-signup-link alternative-action"><a href="signup.jsp" >
            Create New Account</a>
        </button>
        </form>
    </div>
</div>


</body>
</html>
