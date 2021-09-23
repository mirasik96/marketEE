package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullname  = request.getParameter("fullname");
        try {
            if(DBManager.login(email) == null){
                if(password.equals(repassword)){
                    DBManager.addUser(new User(null,email,password,fullname,false));
                    response.sendRedirect("/login");
                }else{
                    response.sendRedirect("/register?passwordError");
                }
            }else{
                response.sendRedirect("/register?emailError");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
