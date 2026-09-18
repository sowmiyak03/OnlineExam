<!DOCTYPE html>
<html>
<head>
    <title>Online Examination</title>
</head>

<body>

    <h2>Online Examination</h2>

    <form action="SubmitExamServlet" method="post">

        <h3>Question 1</h3>
        <p>Which keyword is used to create a class in Java?</p>

        <input type="radio" name="q1" value="A"> class<br>
        <input type="radio" name="q1" value="B"> Class<br>
        <input type="radio" name="q1" value="C"> new<br>
        <input type="radio" name="q1" value="D"> create<br>


        <h3>Question 2</h3>
        <p>Which method is the entry point of a Java program?</p>

        <input type="radio" name="q2" value="A"> start()<br>
        <input type="radio" name="q2" value="B"> main()<br>
        <input type="radio" name="q2" value="C"> run()<br>
        <input type="radio" name="q2" value="D"> init()<br>


        <h3>Question 3</h3>
        <p>Which keyword is used to inherit a class in Java?</p>

        <input type="radio" name="q3" value="A"> implements<br>
        <input type="radio" name="q3" value="B"> inherits<br>
        <input type="radio" name="q3" value="C"> extends<br>
        <input type="radio" name="q3" value="D"> super<br>

        <br>

        <button type="submit">Submit Exam</button>

    </form>

</body>
</html>