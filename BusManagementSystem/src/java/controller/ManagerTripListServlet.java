package controller;

import dao.TripDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Trip;
import model.User;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

// Trang chinh cua Manager: danh sach chuyen xe phu trach trong ngay
public class ManagerTripListServlet extends HttpServlet {

    private final TripDAO tripDAO = new TripDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User manager = (User) session.getAttribute("user");
        if (manager == null || !"manager".equals(manager.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String dateParam = req.getParameter("date");
        LocalDate localDate = (dateParam != null && !dateParam.isEmpty())
                ? LocalDate.parse(dateParam) : LocalDate.now();
        Date tripDate = Date.valueOf(localDate);

        List<Trip> trips = tripDAO.getTripsByManagerAndDate(manager.getUserId(), tripDate);

        req.setAttribute("trips", trips);
        req.setAttribute("selectedDate", localDate.toString());
        req.getRequestDispatcher("/manager/tripList.jsp").forward(req, resp);
    }
}
