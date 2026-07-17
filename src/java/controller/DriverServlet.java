package controller;

import dao.DriverDAO;
import model.Trip;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DriverServlet", urlPatterns = {"/driver"})
public class DriverServlet extends HttpServlet {

    private final DriverDAO dao = new DriverDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Integer driverId = (Integer) session.getAttribute("driverId");
        if (driverId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Trip trip = dao.getTodayTrip(driverId);
        request.setAttribute("trip", trip);

        request.getRequestDispatcher("/driver.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("driverId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int tripId = Integer.parseInt(request.getParameter("tripId"));
            String status = request.getParameter("status");
            String note = request.getParameter("note");

            dao.updateVehicle(tripId, status, note);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/driver");
    }
}
