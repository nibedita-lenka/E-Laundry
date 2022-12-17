<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.enums.Role" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    <title>Signup</title>
</head>
<%
    response.setHeader("cache-control","no-cache,no-store,must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setHeader("Expires","0"); //proxy

    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
    else if(!((User) session.getAttribute("activeUser")).getRole().equals(Role.ADMIN)){
        response.sendRedirect("login.jsp");
    }
    User user = (User) session.getAttribute("activeUser");
%>
<body style="background-color: white">
    <div class="nav">
        <jsp:include page="./navbar.jsp">
            <jsp:param name="userName" value="<%=user.getName()%>"/>
            <jsp:param name="userRole" value="<%=user.getRole().toString()%>"/>
        </jsp:include>
    </div>
    <div class="main">
    <div class="form" style="background-color: blue; color: white">
        <%
            if(session.getAttribute("message")!=null){
                out.print("<h6 style='text-align: center; font-size: 20px; color: yellow; margin-top: 8px'>"+session.getAttribute("message")+"</h6>");
                session.removeAttribute("message");
            }
        %>
        <h2 style="border-bottom-color: white; font-weight: lighter ">Create new admin</h2>
        <form action="create-admin" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" autofocus required>
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
            <input style="background-color: white; color: white" type="submit" name="submit" id="submit">

            
        </form>
    </div>
    </div>
</body>

</html>