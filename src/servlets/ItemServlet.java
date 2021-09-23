package servlets;

import db.Brand;
import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            try {
                ArrayList<Items> items = DBManager.getItems();
                ArrayList<Brand> brands = DBManager.getBrands();
                request.setAttribute("items", items);
                    request.setAttribute("brands", brands);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("item.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            String name = request.getParameter("name");
            String priceT = request.getParameter("price");
            String details = request.getParameter("details");
            String img = request.getParameter("img");
            String brandIdT = request.getParameter("brandId");
            try{
                Double price = Double.parseDouble(priceT);
                Long brandId = Long.parseLong(brandIdT);
                DBManager.addItem(new Items(null, name, price, details, img, DBManager.getBrand(brandId)));
                response.sendRedirect("/item");
            }catch(Exception e){
                e.printStackTrace();
            }        }else{
            response.sendRedirect("/login");
        }
    }
}
