<%@ page import="org.bson.Document" %>
<%@ page import="com.mongodb.client.FindIterable" %>

<!DOCTYPE html>
<html>

<head>
    <title>Online Examination</title>
</head>

<body>

    <h2>Online Examination</h2>

    <form action="SubmitExamServlet" method="post">

        <%
            FindIterable<Document> questions =
                    (FindIterable<Document>) request.getAttribute("questions");

            int questionNumber = 1;

            for (Document q : questions) {
        %>

            <h3>Question <%= questionNumber %></h3>

            <p>
                <%= q.getString("question") %>
            </p>

            <input type="radio"
                   name="q<%= questionNumber %>"
                   value="A"
                   required>
            <%= q.getString("optionA") %>
            <br>

            <input type="radio"
                   name="q<%= questionNumber %>"
                   value="B">
            <%= q.getString("optionB") %>
            <br>

            <input type="radio"
                   name="q<%= questionNumber %>"
                   value="C">
            <%= q.getString("optionC") %>
            <br>

            <input type="radio"
                   name="q<%= questionNumber %>"
                   value="D">
            <%= q.getString("optionD") %>
            <br><br>

        <%
                questionNumber++;
            }
        %>

        <button type="submit">Submit Exam</button>

    </form>

</body>

</html>