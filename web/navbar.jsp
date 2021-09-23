<%@ page import="db.User" %>
<%
    String siteName = "MARKET";
    User currentUser = (User) session.getAttribute("user");

%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #3399ff;">
    <div class="container-fluid">
        <a class="navbar-brand" href="/homepage"><%=siteName%></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="d-flex justify-between">

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <%
                if(currentUser!=null && currentUser.isAdmin()){
            %>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/item">Items</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/brand">Brands</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/profile"><%=currentUser.getFullname()%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/login">Log Out</a>
                </li>
            </ul>
            <%
                }else if(currentUser!=null){
            %>
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" href="/basket">Basket</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/profile"><%=currentUser.getFullname()%></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/login">Log Out</a>
                        </li>
                    </ul>

            <%
            }else{%>
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/login">Log in</a>
                    </li>
                </ul>

            <%
                }
            %>
        </div>
        </div>
    </div>
</nav>
<br>