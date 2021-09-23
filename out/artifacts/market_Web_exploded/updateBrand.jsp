<%@ page import="db.Brand" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Brand</title>
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
    User user = (User) session.getAttribute("user");
    Brand brand = (Brand) request.getAttribute("brand");
%>
<div class="container">
    <div class="modal-content">
        <div class="modal-body">
            <div>
                Name: <input class="form-control" type="text" name="name" form="form" value="<%=brand.getName()%>">
            </div>
            <div>
                Country: <input class="form-control" type="text" name="country" form="form" value="<%=brand.getCountry()%>">
            </div>
        </div>
        <div class="modal-footer">
            <form id="form" action="/updateBrand" method="post">
                <input type="hidden" name="id" value="<%=brand.getId()%>">
                <button class="btn btn-success">Edit</button>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                    Delete
                </button>
            </form>

        </div>

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
                        <a href="/deleteBrand?id=<%=brand.getId()%>" class="btn btn-danger">Yes</a>
                    </div>
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
