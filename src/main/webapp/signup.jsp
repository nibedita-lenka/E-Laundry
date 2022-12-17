<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    <title>Signup</title>
</head>

<body>
    <div class="main">
        <div class="form">

        <%
            if(session.getAttribute("message")!=null){
                out.print("<h6 style='text-align: center; margin: 6px; font-size: 20px; color: darkviolet'>"+session.getAttribute("message")+"</h6>");
                session.removeAttribute("message");
            }
        %>

            <h2>Create your account</h2>
            <form action="signup" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" required autofocus>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" required>
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" id="address" required></input>
                </div>
                <div class="btns">
                    <input type="submit" name="submit" id="submit">
                    <a href="./index.jsp">Login</a>
                </div>

            </form>
        </div>
    </div>
</body>

</html>