package controllers.admin;

import DAO.UserDAO;
import models.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="adminUserServlet", urlPatterns={"/admin/users"})
public class adminUserServlet extends HttpServlet {
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
                int userId = Integer.parseInt(request.getParameter("userId"));
                userDAO.deleteUser(userId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            response.sendRedirect("users");
            return;
        }

        List<User> list = userDAO.getAllUsers();
        request.setAttribute("users", list);
        request.getRequestDispatcher("/views/admin/user-management.jsp").forward(request, response);
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
                User u = new User();
                u.setUsername(request.getParameter("username"));
                u.setPassword(request.getParameter("password"));
                u.setEmail(request.getParameter("email"));
                u.setFullName(request.getParameter("fullName"));
                u.setRole(request.getParameter("role"));
                u.setStatus("active");
                userDAO.createUser(u);
            } else if (action.equals("update")) {
                try {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    User u = userDAO.getUserById(userId);
                    if (u != null) {
                        u.setEmail(request.getParameter("email"));
                        u.setFullName(request.getParameter("fullName"));
                        u.setStatus(request.getParameter("status"));
                        String role = request.getParameter("role");
                        if (role != null) {
                            u.setRole(role);
                        }
                        userDAO.updateUser(u);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("users");
    }
}
