package servlets;

import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/deleteBasket")
public class DeleteBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && !user.isAdmin()){
            String idT = request.getParameter("id");
            try{
                Long id = Long.parseLong(idT);
                ArrayList<Items> items = (ArrayList<Items>) request.getSession().getAttribute("basket");
                if(items == null){
                    items = new ArrayList<>();
                }
                for(int i = 0; i < items.size(); i++){
                    if(items.get(i).getId() == id){
                        items.remove(i);
                    }
                }
                request.getSession().setAttribute("basket", items);
            }catch (Exception e){
                e.printStackTrace();
            }
            response.sendRedirect("/basket");
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
