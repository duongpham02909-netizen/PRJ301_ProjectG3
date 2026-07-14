/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.StudentDAO;
// Import thêm các DAO khác mà bạn cần
// import dal.RouteDAO; 
// import dal.AbsenceDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;
import model.Student;
// import model.Account;

public class ParentDash extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int parentid = 2;
        StudentDAO cDao = new StudentDAO();
        Student student = cDao.getStudentByParent(parentid);
        String routeName = cDao.getRouteName(student.getStudent_id());
        String routeCode = cDao.getRouteCode(student.getStudent_id());
        Map<String, String> studentStops = cDao.getStopName(student.getStudent_id());
        String startstop = studentStops.get("startStop");
        String returnstop = studentStops.get("returnStop");
        Map<String, String> t = cDao.getTime_Status(student.getStudent_id());
        String pickupStatus = t.get("pickupStatus");
        String pickupTime = t.get("pickupTime");
        String returnStatus = t.get("returnStatus");
        String returnTime = t.get("returnTime");

        request.setAttribute("student", student);
        request.setAttribute("routeName", routeName);
        request.setAttribute("routeCode", routeCode);
        request.setAttribute("startstop", startstop);
        request.setAttribute("returnstop", returnstop);
        request.setAttribute("pickupStatus", pickupStatus);
        request.setAttribute("pickupTime", pickupTime);
        request.setAttribute("returnStatus", returnStatus);
        request.setAttribute("returnTime", returnTime);
        request.getRequestDispatcher("lichtrinh.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO cDao = new StudentDAO();
        ArrayList<String> error = new ArrayList<>();
        String absenceDate = request.getParameter("absenceDate");
        String reason = request.getParameter("reason");
        if (reason == null || reason.trim().isEmpty()) {
            error.add("Reason must be not be empty/blank");
        }
        int parentid = 2;
        Student student = cDao.getStudentByParent(parentid);
        int student_id = student.getStudent_id();
        int route_id = cDao.getRouteID(student_id);
        if (error.size() > 0) {
            request.setAttribute("error", error);
            request.setAttribute("absenceDate", absenceDate);
            request.setAttribute("reason", reason);
            request.getRequestDispatcher("xinnghi.jsp").forward(request, response);
        } else {
            String e = cDao.absent(student_id, route_id, absenceDate, reason);
            if (e != null) {
                error.add(e);
                request.setAttribute("absenceDate", absenceDate);
                request.setAttribute("reason", reason);
                request.getRequestDispatcher("xinnghi.jsp").forward(request, response);
            } else {
                request.setAttribute("succ", "successful");
                request.getRequestDispatcher("xinnghi.jsp").forward(request, response);
            }
        }
    }
}
