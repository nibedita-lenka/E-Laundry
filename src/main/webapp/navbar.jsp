<%@ page import="com.elaundry.enums.Role" %>
<%@ page import="com.elaundry.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./nav.css">
    <title>Document</title>
</head>
<%
    String userRole = Role.USER.toString();
    String userName = "";
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("login.jsp");
    }
    else {
        userName = request.getParameter("userName");
        userRole = request.getParameter("userRole");
    }
%>

<body>
    <nav>
        <div class="logo"><img src="./images/logo.png" height="70px" alt="E-Laundry"></div>
        <ul>

            <%
                if(userRole.equals(Role.USER.toString())){
                    out.print("<li><a href='placeOrder.jsp'>Place Order</a></li>");
                }
                else{
                    out.print("<li><a href='./allUsers.jsp'>Users</a></li>");
                    out.print("<li><a href='./allPrices.jsp'>Rate Chart</a></li>");
                }
            %>
            <li><a href="./allOrders.jsp">All orders</a></li>
            <li><a href="myProfileEdit.jsp"><%=userName%></a></li>
            <li><a href="logout">logout</a></li>
        </ul>
    </nav>
</body>
</html>