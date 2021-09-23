<%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%
    String emailError = request.getParameter("emailError");
    String passwordError = request.getParameter("passwordError");
%>
<%if(emailError != null){
%>
<div class="container">
    <div class="alert alert-danger" role="alert">
        This email already <a href="#" class="alert-link">exists!</a>. Try again.
    </div>
</div>
<br>
<%}else if(passwordError != null){
%>
<div class="container">
    <div class="alert alert-danger" role="alert">
        Passwords are <a href="#" class="alert-link">not same!</a>. Try again.
    </div>
</div>
<br>
<%}%>
<div class="container">
    <form action="/register" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label>Fullname</label>
            <input name="fullname" type="text" class="form-control" placeholder="Enter fullname">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input name="password" type="password" class="form-control" placeholder="Password">
        </div>
        <div class="form-group">
            <label>Password again</label>
            <input name="repassword" type="password" class="form-control" placeholder="rePassword">
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success">Register</button>
        </div>
    </form>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
