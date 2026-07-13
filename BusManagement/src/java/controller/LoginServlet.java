package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("error", "Please enter username and password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        User user = userDAO.login(username.trim(), password.trim());

        if (user == null) {

            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("username", username);

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        session.setAttribute("LOGIN_USER", user);

        session.setMaxInactiveInterval(30 * 60);

        switch (user.getRole()) {

            case "admin":
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;

            case "manager":
                response.sendRedirect(request.getContextPath() + "/manager/dashboard");
                break;

            case "driver":
                response.sendRedirect(request.getContextPath() + "/driver/dashboard");
                break;

            case "parent":
                response.sendRedirect(request.getContextPath() + "/parent/dashboard");
                break;

            default:

                session.invalidate();

                request.setAttribute("error", "Your account does not have permission.");

                request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

}
