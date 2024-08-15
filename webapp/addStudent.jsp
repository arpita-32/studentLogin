<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
</head>
<body>
    <% 
    String message = (String) request.getAttribute("MESSAGE"); 
    if (message != null) { 
        // Check if the message indicates success or an error
        if (message.startsWith("Data Inserted Successfully")) {
            out.print("<p style='color:green;'>" + message + "</p>"); 
        } else {
            out.print("<p style='color:red;'>" + message + "</p>"); 
        }
    } 
    %>
    
    <form action="studentCtrl" method="post">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        Mobile: <input type="number" name="mobile" required><br>
        Age: <input type="number" name="age" required><br>
        Password: <input type="password" name="password" required><br>
        UserName: <input type="text" name="user_name" required><br>
        <input type="submit" value="Add Student">
    </form>
</body>
</html>
