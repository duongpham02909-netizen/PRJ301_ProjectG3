package controllers.admin;

import DAO.BusDAO;
import DAO.RouteDAO;
import DAO.UserDAO;
import models.Bus;
import models.Route;
import models.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="adminBusServlet", urlPatterns={"/admin/buses"})
public class adminBusServlet extends HttpServlet {
    private final BusDAO busDAO = new BusDAO();
    private final RouteDAO routeDAO = new RouteDAO();
    private final UserDAO userDAO = new UserDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            try {
                int busId = Integer.parseInt(request.getParameter("busId"));
                busDAO.deleteBus(busId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            response.sendRedirect("buses");
            return;
        }

        List<Bus> buses = busDAO.getAllBuses();
        List<Route> routes = routeDAO.getAllRoutes();
        List<User> drivers = userDAO.getUsersByRole("driver");
        List<User> managers = userDAO.getUsersByRole("manager");

        request.setAttribute("buses", buses);
        request.setAttribute("routes", routes);
        request.setAttribute("drivers", drivers);
        request.setAttribute("managers", managers);

        request.getRequestDispatcher("/views/admin/bus-management.jsp").forward(request, response);
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
                try {
                    Bus b = new Bus();
                    b.setBusCode(request.getParameter("busCode"));
                    b.setLicensePlate(request.getParameter("licensePlate"));
                    b.setCapacity(Integer.parseInt(request.getParameter("capacity")));
                    b.setRouteId(Integer.parseInt(request.getParameter("routeId")));
                    b.setDriverId(Integer.parseInt(request.getParameter("driverId")));
                    b.setManagerId(Integer.parseInt(request.getParameter("managerId")));
                    b.setStatus("active");
                    busDAO.createBus(b);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (action.equals("update")) {
                try {
                    int busId = Integer.parseInt(request.getParameter("busId"));
                    Bus b = busDAO.getBusById(busId);
                    if (b != null) {
                        b.setLicensePlate(request.getParameter("licensePlate"));
                        b.setCapacity(Integer.parseInt(request.getParameter("capacity")));
                        b.setStatus(request.getParameter("status"));
                        
                        String routeIdStr = request.getParameter("routeId");
                        if (routeIdStr != null) {
                            b.setRouteId(Integer.parseInt(routeIdStr));
                        }
                        
                        String driverIdStr = request.getParameter("driverId");
                        if (driverIdStr != null) {
                            b.setDriverId(Integer.parseInt(driverIdStr));
                        }

                        String managerIdStr = request.getParameter("managerId");
                        if (managerIdStr != null) {
                            b.setManagerId(Integer.parseInt(managerIdStr));
                        }

                        busDAO.updateBus(b);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("buses");
    }
}
