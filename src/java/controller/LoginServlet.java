package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userStr = request.getParameter("username");
        String passStr = request.getParameter("password");

        dao.DBContext db = new dao.DBContext();
        String sql = "SELECT user_id, full_name, role FROM [Users] WHERE username = ? AND password = ? AND status = 'active'";

        try (Connection con = db.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userStr);
            ps.setString(2, passStr);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    HttpSession session = request.getSession();

                    if ("driver".equals(role)) {
                        session.setAttribute("driverId", rs.getInt("user_id"));
                        session.setAttribute("driverName", rs.getString("full_name"));
                        response.sendRedirect(request.getContextPath() + "/driver");
                        return;
                    } else {
                        request.setAttribute("error", "Tài khoản không phải là driver");
                    }
                } else {
                    request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi liên kết DB");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
