package com.onlineexam;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        MongoDatabase database = MongoDBConnection.getDatabase();

        MongoCollection<Document> students =
                database.getCollection("students");

        Document student = students.find(
                new Document("email", email)
                .append("password", password)
        ).first();

        if (student != null) {

            // Login successful
        	 request.getSession().setAttribute("studentId",
        	            student.getString("studentId"));

        	    request.getSession().setAttribute("studentName",
        	            student.getString("name"));

        	    response.sendRedirect("ExamServlet");

        } else {

            // Login failed
            response.setContentType("text/html");

            response.getWriter().println("<h2>Invalid Email or Password</h2>");
            response.getWriter().println("<a href='login.jsp'>Try Again</a>");
        }
    }
}