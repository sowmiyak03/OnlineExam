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

@WebServlet("/SubmitExamServlet")
public class SubmitExamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get answers from the exam
        String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");

        // Calculate marks
        int marks = 0;
        int totalMarks = 2;
        double percentage = 0;

        if ("A".equals(q1)) {
            marks++;
        }

        if ("B".equals(q2)) {
            marks++;
        }

        // Calculate percentage
        if (totalMarks > 0) {
            percentage = (marks * 100.0) / totalMarks;
        }

        // Get logged-in student's details from session
        String studentId =
                (String) request.getSession().getAttribute("studentId");

        String studentName =
                (String) request.getSession().getAttribute("studentName");

        // Connect to MongoDB
        MongoDatabase database = MongoDBConnection.getDatabase();

        // Get marks collection
        MongoCollection<Document> marksCollection =
                database.getCollection("marks");

        // Create result document
        Document result = new Document()
                .append("studentId", studentId)
                .append("studentName", studentName)
                .append("marks", marks)
                .append("totalMarks", totalMarks)
                .append("percentage", percentage);

        // Store result in MongoDB
        marksCollection.insertOne(result);

        // Display result
        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");

        response.getWriter().println("<head>");
        response.getWriter().println("<title>Exam Result</title>");
        response.getWriter().println("</head>");

        response.getWriter().println("<body>");

        response.getWriter().println(
                "<h2>Exam Submitted Successfully!</h2>"
        );

        response.getWriter().println(
                "<h3>Student Name: " +
                studentName +
                "</h3>"
        );

        response.getWriter().println(
                "<h3>Your Marks: " +
                marks +
                " / " +
                totalMarks +
                "</h3>"
        );

        response.getWriter().println(
                "<h3>Percentage: " +
                percentage +
                "%</h3>"
        );

        response.getWriter().println(
                "<br><a href='MarkListServlet'>View Mark List</a>"
        );

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}