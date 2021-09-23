package servlets;

import db.Brand;
import db.DBManager;
import db.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/detailsBrand")
public class DetailsBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User user = (User) request.getSession().getAttribute("user");
//        if(user != null){
            String idT = request.getParameter("id");
            try {
                Long id = Long.parseLong(idT);
                Brand brand = DBManager.getBrand(id);
                request.setAttribute("brand", brand);
            }catch (Exception e){
                e.printStackTrace();
            }
            request.getRequestDispatcher("detailsBrand.jsp").forward(request, response);
//        }else{
//            response.sendRedirect("/login");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
