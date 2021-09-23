<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Brand" %>
<%@ page import="db.Items" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");
    ArrayList<Brand> brands = (ArrayList<Brand>) request.getAttribute("brands");
%>

<div class="container">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        +Add Item
    </button>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New Item</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/item" method="post">
                    <div class="modal-body">
                        <div>
                            Name: <input class="form-control" type="text" name="name">
                        </div>
                        <div>
                            Price: <input class="form-control" type="number" name="price">
                        </div>
                        <div>
                            Details: <input class="form-control" type="text" name="details">
                        </div>
                        <div>
                            Img URL: <input class="form-control" type="text" name="img">
                        </div>
                        <div>
                            Brand: <select class="form-control" name="brandId">
                            <%for(Brand b: brands){%>
                            <option value="<%=b.getId()%>"><%=b.getName()%></option>
                            <%}%>
                        </select>
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
            <th scope="col">Price</th>
            <th scope="col">Details</th>
            <th scope="col">Img</th>
            <th scope="col">Brand</th>
            <th scope="col">DETAILS</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(Items i: items){
        %>
        <tr style="text-align: center">
            <th scope="row"><%=i.getId()%></th>
            <td><%=i.getName()%></td>
            <td><%=i.getPrice()%></td>
            <td><%=i.getDetails()%></td>
            <td><%=i.getImg()%></td>
            <td><%=i.getBrand().getName()%></td>
            <td><a class="btn btn-primary" href="/detailsItem?id=<%=i.getId()%>">DETAILS</a></td>
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
