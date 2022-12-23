<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 03/11/2022
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <form action="/RegisterServlet"  method="post" class="facebookFormElements">
      <input type="text" placeholder="Full Name" name="name" required>
      <input type="email" placeholder="Email" name="email" required>
      <select name="gender" required>
        <option value="">Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
      </select>
      <input type="username" name="username" placeholder="Username" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit" id="signUp">Sign Up</button>
      <div class="forgotten-password"><span>Forgotten Password</span></div>
      <hr color="gray">
      <button class="facebook-signup-link alternative-action"><a href="login.jsp" >
        Log in</a>
      </button>
    </form>

  </div>
</div>

</body>
</html>
