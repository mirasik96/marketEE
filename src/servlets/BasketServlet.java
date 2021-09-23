package servlets;

import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && !user.isAdmin()){
            ArrayList<Items> items = (ArrayList<Items>) request.getSession().getAttribute("basket");
            if(items == null){
                items = new ArrayList<>();
            }
            request.setAttribute("items", items);
            request.getRequestDispatcher("basket.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && !user.isAdmin()){
            String idT = request.getParameter("id");
            try{
                Long id = Long.parseLong(idT);
                Items item = DBManager.getItem(id);
                ArrayList<Items> items = (ArrayList<Items>) request.getSession().getAttribute("basket");
                if(items == null){
                    items = new ArrayList<>();
                }
                items.add(item);
                request.getSession().setAttribute("basket", items);
            }catch (Exception e){
                e.printStackTrace();
            }
            response.sendRedirect("/homepage");
        }else{
            response.sendRedirect("/login");
        }
    }
}
