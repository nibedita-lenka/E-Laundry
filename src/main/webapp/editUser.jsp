<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.enums.Role" %>
<%@ page import="com.elaundry.service.UserService" %>
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
    User user = new User();
    user.setRole(Role.USER);
    User editUser = new User();
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
    else if(!((User) session.getAttribute("activeUser")).getRole().equals(Role.ADMIN)){
        response.sendRedirect("login.jsp");
    }
    else{
        user = (User) session.getAttribute("activeUser");
        editUser = new UserService().getById(Integer.parseInt(request.getParameter("editUserId")));
    }
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
        <h2 style="border-bottom-color: white; font-weight: lighter ">Edit User</h2>
        <form action="edit-user" method="post">
            <input type="hidden" name="id" value="<%=editUser.getId()%>">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="<%=editUser.getName()%>">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" value="<%=editUser.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="enter password to change password or blank">
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" name="confirmPassword" id="confirmPassword">
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" name="address" id="address" value="<%=editUser.getAddress()%>">
            </div>
            <input style="background-color: white; color: blue" type="submit" name="submit" id="submit" >

            
        </form>
    </div>
    </div>
</body>

</html>