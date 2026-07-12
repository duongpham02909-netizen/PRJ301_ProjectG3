package controller;

import dao.AttendanceDAO;
import dao.TripDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.StudentAttendanceView;
import model.Trip;
import model.User;

import java.io.IOException;
import java.util.List;

// Trang diem danh hoc sinh theo tung chuyen xe
public class ManagerAttendanceServlet extends HttpServlet {

    private final TripDAO tripDAO = new TripDAO();
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

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

        List<StudentAttendanceView> students = attendanceDAO.getStudentListForTrip(tripId);

        req.setAttribute("trip", trip);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/manager/attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int tripId = Integer.parseInt(req.getParameter("tripId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String action = req.getParameter("action");

        switch (action) {
            case "board":
                attendanceDAO.markBoarded(tripId, studentId, Integer.parseInt(req.getParameter("stopId")));
                break;
            case "dropoff":
                attendanceDAO.markDroppedOff(tripId, studentId, Integer.parseInt(req.getParameter("stopId")));
                break;
            case "noshow":
                attendanceDAO.markNoShow(tripId, studentId);
                break;
            default:
                break;
        }

        resp.sendRedirect(req.getContextPath() + "/manager/attendance?tripId=" + tripId);
    }
}
