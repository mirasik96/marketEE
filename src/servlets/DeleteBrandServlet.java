package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/deleteBrand")
public class DeleteBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            String idT = request.getParameter("id");
            try{
                Long id = Long.parseLong(idT);
                DBManager.deleteBrand(id);
            }catch(Exception e){
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
