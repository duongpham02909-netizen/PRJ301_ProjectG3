<%-- 
    Document   : xinnghi
    Created on : 11 thg 7, 2026, 16:41:17
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>2. Báo nghỉ đón bus</h2>

        <%
            ArrayList<String> error = (ArrayList<String>)request.getAttribute("error");
            if(error!=null && error.size()>0){
                for(String e: error){
        %>
        <p style="color: red"><%=e%></p>
        <%
                }
            }
            
            String succ = (String) request.getAttribute("succ");
            if(succ != null) {
        %>
        <p style="color: green"><%=succ%></p>
        <%
            }
        %>

        <form action="ParentDash" method="POST">
            <table>
                <tr>
                    <td>Chọn ngày nghỉ: <input type="date" id="absenceDate" name="absenceDate" required></td>
                </tr>
                <tr>
                    <td>Lý do xin nghỉ: <input type="text" name="reason" required></td>
                </tr>
            </table>
            <br/>
            <button type="submit">Gửi thông báo nghỉ</button>
        </form>
        <script>
            // 1. Lấy thời gian hiện tại của hệ thống máy tính phụ huynh
            const today = new Date();

            // 2. Tăng thêm 1 ngày để ra "Ngày mai"
            const tomorrow = new Date(today);
            tomorrow.setDate(tomorrow.getDate() + 1);

            // 3. Định dạng ngày mai thành chuỗi YYYY-MM-DD phù hợp với input date
            const yyyy = tomorrow.getFullYear();
            let mm = tomorrow.getMonth() + 1; // Tháng trong JS tính từ 0-11 nên phải +1
            let dd = tomorrow.getDate();

            // Thêm số 0 phía trước nếu ngày/tháng nhỏ hơn 10 (ví dụ: 07 thay vì 7)
            if (mm < 10)
                mm = '0' + mm;
            if (dd < 10)
                dd = '0' + dd;

            const minDateString = yyyy + '-' + mm + '-' + dd;

            // 4. Áp đặt thuộc tính min vào ô input lịch
            document.getElementById('absenceDate').setAttribute('min', minDateString);
        </script>
    </body>
</html>
