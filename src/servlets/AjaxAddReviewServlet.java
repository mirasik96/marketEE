package servlets;

import db.DBManager;
import db.Items;
import db.Review;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/ajaxAddReview")
public class AjaxAddReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            String review = request.getParameter("review");
            String item_id = request.getParameter("itemId");
            try{
                Long itemId = Long.parseLong(item_id);
                Items item = DBManager.getItem(itemId);
                if(item != null){
                    Review review1 = new Review();
                    review1.setReview(review);
                    review1.setItem(item);
                    review1.setUser(user);
                    DBManager.addReview(review1);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("/login");
        }
    }
}
