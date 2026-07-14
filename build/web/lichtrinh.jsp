<%-- 
    Document   : parent-dashboard
    Created on : 8 thg 7, 2026, 16:27:52
    Author     : admin
--%>

<%@page import="model.Student"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parent Dasboard</title>
        <script>
            setInterval(function () {
                location.reload();
            }, 10000);
        </script>
    </head>
    <body>
        <%
            Student student = (Student) request.getAttribute("student");
            String student_name = student.getStudent_name();
            String student_code = student.getStudent_code();
            String class_name = student.getClass_name();
            String routeName = (String) request.getAttribute("routeName");
            String routeCode = (String) request.getAttribute("routeCode");
            String startstop = (String) request.getAttribute("startstop");
            String returnstop = (String) request.getAttribute("returnstop");
            String pickupStatus = (String) request.getAttribute("pickupStatus");
            String pickupTime  = (String) request.getAttribute("pickupTime");
            String returnStatus = (String) request.getAttribute("returnStatus");
            String returnTime = (String) request.getAttribute("returnTime");
            
        %>
        <%!
        public String printStatusHTML(String status) {
                if("boarded".equals(status)) return "<span style='color: lime'>Đã lên xe (Boarded)</span>";
                if("dropped_off".equals(status)) return "<span style='color: green'>Đã đến nơi (Dropped Off)</span>";
                if("absent".equals(status)) return "<span style='color: gray'>Báo nghỉ (Absent)</span>";
                if("no_show".equals(status)) return "<span style='color: red'>Không lên xe (No Show)</span>";
                return "<span style='color: orange;'>" + (status != null ? status : "Not yet") + "</span>";
            }
        %>
        <h2>1. Theo dõi lịch trình hôm nay</h2>
        <table class="table" border="1px solid black">
            <p>Học sinh: <%=student_name%></p>
            <p>MSHS: <%=student_code%></p>
            <p>Class Name: <%=class_name%></p>
            <p>Tuyến xe hiện tại : <%=routeName%> | <%=routeCode%> </p>
            <p>Điểm đón: <%=startstop%>     | Trạng thái: <%= printStatusHTML(pickupStatus) %> </p>
            <p>Giờ đón dự kiến: <%=pickupTime%></p>
            <br/>
            <p>Điểm trả: <%=returnstop%>    | Trạng thái: <%= printStatusHTML(returnStatus) %></p>
            <p>Giờ trả dự kiến: <%=returnTime%></p>    
        </table>
    </body>
</html>