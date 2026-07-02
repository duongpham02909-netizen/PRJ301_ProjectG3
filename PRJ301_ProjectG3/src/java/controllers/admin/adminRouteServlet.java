package controllers.admin;

import DAO.RouteDAO;
import models.Route;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="adminRouteServlet", urlPatterns={"/admin/routes"})
public class adminRouteServlet extends HttpServlet {
    private final RouteDAO routeDAO = new RouteDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            try {
                int routeId = Integer.parseInt(request.getParameter("routeId"));
                routeDAO.deleteRoute(routeId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            response.sendRedirect("routes");
            return;
        }

        List<Route> list = routeDAO.getAllRoutes();
        request.setAttribute("routes", list);
        request.getRequestDispatcher("/views/admin/route-management.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("create")) {
                Route r = new Route();
                r.setRouteName(request.getParameter("routeName"));
                r.setRouteCode(request.getParameter("routeCode"));
                r.setDescription(request.getParameter("description"));
                r.setStatus("active");
                routeDAO.createRoute(r);
            } else if (action.equals("update")) {
                try {
                    int routeId = Integer.parseInt(request.getParameter("routeId"));
                    Route r = routeDAO.getRouteById(routeId);
                    if (r != null) {
                        r.setRouteName(request.getParameter("routeName"));
                        r.setDescription(request.getParameter("description"));
                        String status = request.getParameter("status");
                        if (status != null) {
                            r.setStatus(status);
                        }
                        routeDAO.updateRoute(r);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("routes");
    }
}
