<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Brand" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Brands</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%ArrayList<Brand> brands = (ArrayList<Brand>) request.getAttribute("brands");%>

<div class="container">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        +Add Brand
    </button>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New Brand</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/brand" method="post">
                    <div class="modal-body">
                        <div>
                            Name: <input class="form-control" type="text" name="name">
                        </div>
                        <div>
                            Country: <input class="form-control" type="text" name="country">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary">Add</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <br>
    <br>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Country</th>
            <th scope="col">Details</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(Brand b: brands){
        %>
        <tr style="text-align: center">
            <th scope="row"><%=b.getId()%></th>
            <td><%=b.getName()%></td>
            <td><%=b.getCountry()%></td>
            <td><a class="btn btn-primary" href="/detailsBrand?id=<%=b.getId()%>">DETAILS</a></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
