package servlets;

import com.google.gson.Gson;
import db.DBManager;
import db.Items;
import db.Review;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/ajaxReadReviews")
public class AjaxReadReviewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("itemId");
        try{
            Long itemId = Long.parseLong(id);
            Items item = DBManager.getItem(itemId);
            if(item != null){
                ArrayList<Review> reviews = DBManager.getReviews(itemId);
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                String result = gson.toJson(reviews);
                out.print(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
