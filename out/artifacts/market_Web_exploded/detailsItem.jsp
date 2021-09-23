<%@ page import="db.Brand" %>
<%@ page import="db.Items" %><%--
  Created by IntelliJ IDEA.
  User: mirasaitkazinov
  Date: 30.07.2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details of Item</title>
</head>
<body onload="loadReviews()">
<div class="container">
    <div class="row">
        <div class="col-12">
            <%@include file="navbar.jsp" %>
        </div>
    </div>
</div>
<%
    User user = (User) session.getAttribute("user");
    Items item = (Items) request.getAttribute("item");
%>
<div class="container">
    <div class="modal-content">
        <div class="modal-body">
            <div style="text-align: center">
                <img width="30%" src="<%=item.getImg()%>">
            </div>
            <div>
                Name: <input readonly class="form-control" type="text" name="name" form="form" value="<%=item.getName()%>">
            </div>
            <div>
                Price: <input readonly class="form-control" type="text" name="price" form="form" value="<%=item.getPrice()%>">
            </div>
            <div>
                Details: <input readonly class="form-control" type="text" name="details" form="form" value="<%=item.getDetails()%>">
            </div>
            <div>
                Brand: <input readonly class="form-control" type="text" name="brandId" form="form" value="<%=item.getBrand().getName()%>">
            </div>
        </div>
        <%
            if(user != null && user.isAdmin()){
        %>
        <div class="modal-footer">
            <form id="form" action="/updateItem" method="get">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button class="btn btn-primary">Edit</button>
            </form>
        </div>
        <%}else if(user != null && !user.isAdmin()){%>
        <div class="modal-footer">
            <form action="/basket" method="post">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button class="btn btn-primary">To Basket</button>
            </form>
        </div>
        <%}%>
        <div class="container">
            <p>
                <%
                    if(currentUser!=null){
                %>
                <a href="JavaScript:void(0)" onclick="toLike()" style="color:red;font-size: 18px; text-decoration: none;">
                    &#9829;
                </a>
                <script type="text/javascript">
                    function toLike(){
                        $.post("/ajaxToLike", {
                            itemId: <%=item.getId()%>
                        }, function(data){
                            document.getElementById("like_amount").innerHTML = data;
                        });
                    }
                </script>
                <%
                    }
                %>
                <span style="font-weight: bold;" id = "like_amount"><%=item.getLike()%></span> <%=(item.getLike()==1?"like": "likes")%>
            </p>
        </div>

        <script type="text/javascript">
            function loadReviews(){
                $.get("/ajaxReadReviews", {
                    itemId: <%=item.getId()%>
                }, function(result){
                    reviewList = JSON.parse(result);
                    htmlCode = "";

                    for(i = 0; i < reviewList.length; i++){
                        htmlCode += "<a href='JavaScript:void(0)' class='list-group-item list-group-item-action'>";
                        htmlCode += "<div class='d-flex w-100 justify-content-between'>";
                        htmlCode += "<h5 class='mb-1'>"+reviewList[i].user.fullname+"</h5>";
                        if(<%=(user != null? user.getId(): -1)%> == reviewList[i].user.id){
                            htmlCode += "<small onclick = 'deleteReview("+reviewList[i].id+")'>&#x2715;</small>";
                        }
                        htmlCode += "</div>";
                        htmlCode += "<p class='mb-1'>"+reviewList[i].review+"</p>";
                        htmlCode += "<small>"+reviewList[i].postDate+"</small>";
                        htmlCode += "</a>";
                    }
                    document.getElementById("reviewListDiv").innerHTML = htmlCode;
                });
            }
        </script>
        <div class="mt-3">
            <%
                if(currentUser!=null){
            %>
            <div class="container">
                <textarea class="form-control" id = "review_text_id" placeholder="Insert review"></textarea>
                <button class="btn btn-success btn-sm mt-3" onclick="addReview()">ADD REVIEW</button>
            </div>
            <script type="text/javascript">
                function addReview(){

                    reviewText = document.getElementById("review_text_id");

                    $.post("/ajaxAddReview", {
                        review: reviewText.value,
                        itemId: <%=item.getId()%>
                    }, function(result){
                        reviewText.value = "";
                        loadReviews();
                    });
                }

                function deleteReview(id) {
                    toDelete = confirm("Are you sure?");
                    if(toDelete){
                        $.post("/ajaxDeleteReview", {id: id}, function (result) {
                            loadReviews();
                        });
                    }
                }
            </script>
            <%
            }else{
            %>
            <div class="container">
                <h6>Please, <a href="/login">log in</a> to leave review</h6>
            </div>
            <%
                }
            %>
            <div class="list-group mt-3" id = "reviewListDiv">

            </div>
        </div>
    </div>
    <br>
    <br>
</div>
</body>
<script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
