package servlets;

import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/ajaxToLike")
public class AjaxToLikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int likes = 0;
        if(user != null){
            String id = request.getParameter("itemId");
            try{
                Long itemId = Long.parseLong(id);
                Items item = DBManager.getItem(itemId);
                if(item != null){
                    likes = DBManager.toLike(itemId, user.getId());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.println(likes);
        }else{
            response.sendRedirect("/login");
        }
    }
}
