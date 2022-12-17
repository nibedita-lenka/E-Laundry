<%@ page import="com.elaundry.service.UserService" %>
<%@ page import="com.elaundry.service.OrderService" %>
<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.enums.Role" %>
<%@ page import="com.elaundry.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./home.css">
    <link rel="stylesheet" href="./nav.css">
    <title>Users</title>
</head>
<%
    response.setHeader("cache-control","no-cache,no-store,must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setHeader("Expires","0"); //proxy
    User user = new User();
    user.setRole(Role.USER);
    List<User> users = new ArrayList<>();
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
    else if(!((User) session.getAttribute("activeUser")).getRole().equals(Role.ADMIN)){
        response.sendRedirect("index.jsp");
    }
    else{
        user = (User) session.getAttribute("activeUser");
        final UserService userService = new UserService();
        users = userService.getAll();
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
        <h1>All Users</h1>
        <h3><a style="border: 2px solid blue; text-decoration:none; padding: 5px;" href="./createAdmin.jsp">Add new admin</a></h3>
      </div>
        <div class="content">
            <table>
                <thead>
                <tr>
                    <th>
                        Id
                    </th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th colspan="2">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (User u : users) {
                %>
                    <tr>

                        <td><%=u.getId()%></td>
                        <td><%=u.getName()%></td>
                        <td><%=u.getEmail()%></td>
                        <td><%=u.getAddress()%></td>
                        <td><%=u.getRole()%></td>
                        <%
                            if(u.getEmail().equals("admin@gmail.com")){
                                out.print("<td>---</td>");
                                out.print("<td>---</td>");
                            }
                            else{
                                out.print("<td><a href='editUser.jsp?editUserId="+u.getId()+"'>edit</a></td>");
                                out.print("<td><a href='delete-user?userId="+u.getId()+"'>delete</a></td>");
                            }
                        %>

                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>