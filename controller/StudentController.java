package sis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sis.dao.StudentDao;
import sis.model.Student;

@WebServlet("/studentCtrl")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creating a Student object from the request parameters
        Student stud = new Student();
        stud.setName(request.getParameter("name"));
        stud.setEmail(request.getParameter("email"));
        stud.setMobile(request.getParameter("mobile"));
        stud.setAge(Integer.parseInt(request.getParameter("age")));
        stud.setPassword(request.getParameter("password"));
        stud.setUserName(request.getParameter("user_name"));
       
        // Call the DAO and add data to the database
        StudentDao dao = new StudentDao();
        String message = null;
        try {
            if (dao.addstudent(stud)) {
                message = "Data Inserted Successfully";
            } else {
                message = "There is some issue with data insertion.";
            }
        } catch (Exception e) {
            // Capture the stack trace as a String
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            
            // Set the stack trace as the message to be displayed
            message = "An error occurred: <br><pre>" + stackTrace + "</pre>";
        }

        // Forward the request and response to the JSP page with the message
        request.setAttribute("MESSAGE", message);
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }
}
