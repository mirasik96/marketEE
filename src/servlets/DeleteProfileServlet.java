package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            String idT = request.getParameter("id");
            try{
                Long id = Long.parseLong(idT);
                DBManager.deleteUser(id);
                response.sendRedirect("/login");
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
