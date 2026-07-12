package controller;

import dao.TripDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trip;
import model.User;

import java.io.IOException;
import java.sql.Time;

// Man hinh "Sua" thong tin chuyen xe: trang thai chuyen, gio khoi hanh/ket thuc thuc te,
// tinh trang xe (theo yeu cau de bai: nguoi quan ly cap nhat xe da khoi hanh chua, vao may gio...)
public class ManagerTripEditServlet extends HttpServlet {

    private final TripDAO tripDAO = new TripDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User manager = (User) req.getSession().getAttribute("user");
        int tripId = Integer.parseInt(req.getParameter("tripId"));

        Trip trip = tripDAO.getTripById(tripId, manager.getUserId());
        if (trip == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/trips");
            return;
        }

        req.setAttribute("trip", trip);
        req.getRequestDispatcher("/manager/tripEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User manager = (User) req.getSession().getAttribute("user");
        int tripId = Integer.parseInt(req.getParameter("tripId"));

        // Kiem tra chuyen xe co thuoc quyen quan ly khong
        if (tripDAO.getTripById(tripId, manager.getUserId()) == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/trips");
            return;
        }

        String status = req.getParameter("status");
        String vehicleStatus = req.getParameter("vehicleStatus");
        String vehicleIssueNote = req.getParameter("vehicleIssueNote");

        Time actualStart = parseTime(req.getParameter("actualStartTime"));
        Time actualEnd = parseTime(req.getParameter("actualEndTime"));

        tripDAO.updateTrip(tripId, status, actualStart, actualEnd, vehicleStatus, vehicleIssueNote);

        resp.sendRedirect(req.getContextPath() + "/manager/trips");
    }

    private Time parseTime(String value) {
        if (value == null || value.isEmpty()) return null;
        // input type="time" tra ve dang HH:mm -> them :00 giay
        return Time.valueOf(value.length() == 5 ? value + ":00" : value);
    }
}
