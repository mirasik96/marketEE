<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 29.07.2021
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MARKET</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <%@include file="navbar.jsp" %>
            </div>
        </div>
    </div>


    <div class="container">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                ArrayList<Items> item = (ArrayList<Items>) request.getAttribute("itemList");
                if (item != null) {
                    for (Items u : item) {
            %>

            <div class="col">
                <div class="card text-dark bg-light text-center mt-4" style="max-width: 25rem;">
                    <div class="card-header text-center" style="font-size: 25px"><% out.print(u.getName());%></div>
                    <div class="card-body text-center">
                        <img class="card-img" style="height: 250px; width: 300px; object-fit: contain;" src="<%=u.getImg()%>">
                        <h5 class="card-title text-center text-success" style="font-size: 25px"><%
                            out.print(u.getPrice());%> KZT</h5>
                        <p class="card-text text-center"><% out.print(u.getDetails());%></p>
                        <a class="btn btn-primary" href="/detailsItem?id=<%=u.getId()%>">Details</a>
                    </div>
                </div>
            </div>
            <%}}%>
        </div>
    </div>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
