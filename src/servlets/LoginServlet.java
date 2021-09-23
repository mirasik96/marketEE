package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("basket");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = DBManager.login(email);
            if(user != null){
                if(user.getPassword().equals(password)){
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("/profile");
                }else{
                    response.sendRedirect("/login?passwordError");
                }
            }else {
                response.sendRedirect("/login?emailError");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
