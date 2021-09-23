package servlets;

import db.Brand;
import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/updateItem")
public class UpdateItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            String idT = request.getParameter("id");
            try{
                Long id = Long.parseLong(idT);
                Items item = DBManager.getItem(id);
                ArrayList<Brand> brands = DBManager.getBrands();
                request.setAttribute("item", item);
                request.setAttribute("brands", brands);
            }catch(Exception e){
                e.printStackTrace();
            }
            request.getRequestDispatcher("updateItem.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idT = request.getParameter("id");
        String name = request.getParameter("name");
        String priceT = request.getParameter("price");
        String details = request.getParameter("details");
        String img = request.getParameter("img");
        String brandIdT = request.getParameter("brandId");
        try{
            Long id = Long.parseLong(idT);
            Double price = Double.parseDouble(priceT);
            Long brandId = Long.parseLong(brandIdT);
            DBManager.updateItem(new Items(id, name, price, details, img, DBManager.getBrand(brandId)));
            response.sendRedirect("/item");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
