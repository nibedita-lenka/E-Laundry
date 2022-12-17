<%@ page import="com.elaundry.service.UserService" %>
<%@ page import="com.elaundry.service.OrderService" %>
<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.enums.Role" %>
<%@ page import="com.elaundry.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.elaundry.entity.RateCard" %>
<%@ page import="com.elaundry.service.RateCardService" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./home.css">
    <link rel="stylesheet" href="./nav.css">
    <title>Prices</title>
</head>
<%
    response.setHeader("cache-control","no-cache,no-store,must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setHeader("Expires","0"); //proxy
    User user = new User();
    user.setRole(Role.USER);
    Map<String, Integer> rateCards = new HashMap<>();
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
    else if(!((User) session.getAttribute("activeUser")).getRole().equals(Role.ADMIN)){
        response.sendRedirect("index.jsp");
    }
    else{
        user = (User) session.getAttribute("activeUser");
        final UserService userService = new UserService();
        rateCards = new RateCardService().getAll();
    }

%>
<body>
    <div class="nav">
        <jsp:include page="./navbar.jsp">
            <jsp:param name="userName" value="<%=user.getName()%>"/>
            <jsp:param name="userRole" value="<%=user.getRole().toString()%>"/>
        </jsp:include>
    </div>
    <%
        if(session.getAttribute("message")!=null){
            out.print("<h6 style='text-align: center; margin-top: 20px; font-size: 20px; color: darkviolet'>"+session.getAttribute("message")+"</h6>");
            session.removeAttribute("message");
        }
    %>
    <div class="mainHome">
      <div style="display:flex; justify-content: space-around;">
        <h1>Price Chart</h1>
      </div>
        <div class="content">
            <table style="width: 500px">
                <thead>
                <tr>
                    <th>
                        Item
                    </th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Map.Entry<String, Integer> entry : rateCards.entrySet()) {
                %>
                    <tr>

                        <td><%=entry.getKey()%></td>
                        <td><%=entry.getValue()%></td>
                        <td><a href="editPrice.jsp?key=<%=entry.getKey()%>">edit</a></td>

                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>