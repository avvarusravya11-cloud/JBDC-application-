package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentController extends HttpServlet {

    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            Student student = new Student(id, name, email);
            boolean saved = dao.save(student);

            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family:Arial;text-align:center;padding:50px'>");

            if (saved) {
                out.println("<h2>Student saved successfully!</h2>");
            } else {
                out.println("<h2>Student was not saved.</h2>");
            }

            out.println("<p><a href='index.html'>Back to form</a></p>");
            out.println("</body></html>");

        } catch (Exception e) {
            response.getWriter().println(
                "<h2>Error: " + e.getMessage() + "</h2>"
            );
        }
    }
}
