package servlets;

import db.Brand;
import db.DBManager;
import db.Items;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/detailsItem")
public class DetailsItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idT = request.getParameter("id");
            try {
                Long id = Long.parseLong(idT);
                Items item = DBManager.getItem(id);
                request.setAttribute("item", item);
            }catch (Exception e){
                e.printStackTrace();
            }
            request.getRequestDispatcher("detailsItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
