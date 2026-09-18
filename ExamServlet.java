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

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MongoDatabase database = MongoDBConnection.getDatabase();

        MongoCollection<Document> questions =
                database.getCollection("questions");

        FindIterable<Document> questionList = questions.find();

        request.setAttribute("questions", questionList);

        request.getRequestDispatcher("dynamicExam.jsp")
               .forward(request, response);
    }
}