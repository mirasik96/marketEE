<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");%>
<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Details</th>
            <th scope="col">Img</th>
            <th scope="col">Brand</th>
            <th scope="col">REMOVE</th>
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
            <td><a class="btn btn-danger" href="/deleteBasket?id=<%=i.getId()%>">REMOVE</a></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>

</body>
</html>
