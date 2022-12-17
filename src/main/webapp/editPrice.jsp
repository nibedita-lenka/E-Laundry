<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.enums.Role" %>
<%@ page import="com.elaundry.dao.RateCardDao" %>
<%@ page import="com.elaundry.service.RateCardService" %>
<%@ page import="com.elaundry.entity.RateCard" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    <title>Edit Price</title>
</head>
<%
    response.setHeader("cache-control","no-cache,no-store,must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setHeader("Expires","0"); //proxy
    User user = new User();
    user.setRole(Role.USER);
    RateCard byName = new RateCard();
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
    else if(!((User) session.getAttribute("activeUser")).getRole().equals(Role.ADMIN)){
        response.sendRedirect("login.jsp");
    }
    else{
        user = (User) session.getAttribute("activeUser");
        String key = request.getParameter("key");
        byName = new RateCardService().getByName(key);

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
        <h2 style="border-bottom-color: white; font-weight: lighter ">Create new admin</h2>
        <form action="update-price" method="post">
            <input type="hidden" name="id" value="<%=byName.getId()%>">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required value="<%=byName.getItemName()%>">
            </div>
            <div class="form-group">
                <label for="price">Email</label>
                <input type="text" name="price" id="price" value="<%=byName.getPrice()%>" autofocus required>
            </div>
            <input style="background-color: white; color: blue" type="submit" name="submit" id="submit">

            
        </form>
    </div>
    </div>
</body>

</html>