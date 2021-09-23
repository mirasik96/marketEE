package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");
        String fullname = request.getParameter("fullname");
        User user = (User) request.getSession().getAttribute("user");
        if(user.getPassword().equals(oldPassword)){
            if(newPassword.equals(rePassword)){
                try {
                    DBManager.updateUser(new User(user.getId(), null, newPassword, fullname, false));
                    request.getSession().setAttribute("user", DBManager.login(user.getEmail()));
                    response.sendRedirect("/profile?success");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                response.sendRedirect("/profile?rePasswordError");
            }
        }else{
            response.sendRedirect("/profile?oldPasswordError");
        }
    }
}
