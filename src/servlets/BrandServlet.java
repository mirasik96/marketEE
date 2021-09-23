package servlets;

import db.Brand;
import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/brand")
public class BrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            try {
                ArrayList<Brand> brands = DBManager.getBrands();
                request.setAttribute("brands", brands);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("brand.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            try{
                DBManager.addBrand(new Brand(null, name, country));
                response.sendRedirect("/brand");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("/login");
        }
    }
}
