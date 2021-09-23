<%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 29.07.2021
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%User user = (User) session.getAttribute("user");%>
<%
    String oldPasswordError = request.getParameter("oldPasswordError");
    String rePasswordError = request.getParameter("rePasswordError");
    String success = request.getParameter("success");
%>
<%if(oldPasswordError != null){
%>
<div class="container">
    <div class="alert alert-danger" role="alert">
        Wrong <a href="#" class="alert-link">old password!</a>. Try again.
    </div>
</div>
<br>
<%}else if(rePasswordError != null){
%>
<div class="container">
    <div class="alert alert-danger" role="alert">
        Wrong <a href="#" class="alert-link">repassword!</a>. Try again.
    </div>
</div>
<br>
<%}else if(success != null){
%>
<div class="container">
    <div class="alert alert-success" role="alert">
        User <a href="#" class="alert-link">successfully</a> edited.
    </div>
</div>
<br>
<%}%>
<div class="container">
    <form action="/profile" method="post">
        <div class="form-group">
            <label>Fullname</label>
            <input name="fullname" value="<%=user.getFullname()%>" type="text" class="form-control" placeholder="Enter fullname">
        </div>
        <div class="form-group">
            <label>Old Password</label>
            <input name="oldPassword" type="password" class="form-control" placeholder="Old Password">
        </div>
        <div class="form-group">
            <label>New Password</label>
            <input name="newPassword" type="password" class="form-control" placeholder="New Password">
        </div>
        <div class="form-group">
            <label>Password again</label>
            <input name="rePassword" type="password" class="form-control" placeholder="rePassword">
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success">Edit</button>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                Delete
            </button>
        </div>
    </form>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Deletion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a href="/deleteProfile?id=<%=user.getId()%>" class="btn btn-danger">Yes</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
