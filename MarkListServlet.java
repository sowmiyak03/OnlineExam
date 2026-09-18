package com.onlineexam;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MarkListServlet")
public class MarkListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Connect to MongoDB
        MongoDatabase database = MongoDBConnection.getDatabase();

        // Get marks collection
        MongoCollection<Document> marksCollection =
                database.getCollection("marks");

        // Get all mark records
        FindIterable<Document> results = marksCollection.find();

        // HTML response
        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Student Mark List</title>");
        response.getWriter().println("</head>");

        response.getWriter().println("<body>");

        response.getWriter().println("<h2>Student Mark List</h2>");

        response.getWriter().println("<table border='1' cellpadding='10'>");

        response.getWriter().println("<tr>");
        response.getWriter().println("<th>Student ID</th>");
        response.getWriter().println("<th>Student Name</th>");
        response.getWriter().println("<th>Marks</th>");
        response.getWriter().println("<th>Total Marks</th>");
        response.getWriter().println("</tr>");

        // Display records
        for (Document result : results) {

            response.getWriter().println("<tr>");

            response.getWriter().println(
                    "<td>" + result.getString("studentId") + "</td>");

            response.getWriter().println(
                    "<td>" + result.getString("studentName") + "</td>");

            response.getWriter().println(
                    "<td>" + result.getInteger("marks") + "</td>");

            response.getWriter().println(
                    "<td>" + result.getInteger("totalMarks") + "</td>");

            response.getWriter().println("</tr>");
        }

        response.getWriter().println("</table>");

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}