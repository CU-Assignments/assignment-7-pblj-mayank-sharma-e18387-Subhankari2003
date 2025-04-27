<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Student Attendance</title></head>
<body>
    <form action="attendance" method="post">
        Student Name: <input type="text" name="name" required><br/>
        Date: <input type="date" name="date" required><br/>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
