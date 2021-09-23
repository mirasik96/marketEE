package servlets;

import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/ajaxDeleteReview")
public class AjaxDeleteReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            String reviewId = request.getParameter("id");
            try{
                Long id = Long.parseLong(reviewId);
                DBManager.deleteReview(id, user.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("/login");
        }
    }
}
