<%@ page import="com.elaundry.entity.User" %>
<%@ page import="com.elaundry.service.RateCardService" %>
<%@ page import="com.elaundry.entity.RateCard" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.elaundry.enums.Role" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./placeOrder.css">
    <script src="script.js"></script>
    <title>Document</title>
</head>
<%
    response.setHeader("cache-control","no-cache,no-store,must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setHeader("Expires","0"); //proxy
    Map<String, Integer> price = new HashMap<>();
    User user = new User();
    user.setRole(Role.USER);
    if(session.getAttribute("activeUser") == null){
        response.sendRedirect("index.jsp");
    }
   else{
        user = (User) session.getAttribute("activeUser");
        final RateCardService rateCardService = new RateCardService();
        price = rateCardService.getAll();
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
        <div class="place">
            <%
                if(session.getAttribute("message")!=null){
                    out.print("<h6 style='text-align: center; margin: 6px; font-size: 20px; color: darkviolet'>"+session.getAttribute("message")+"</h6>");
                    session.removeAttribute("message");
                }
            %>
            <h1>Place Order</h1>
            <form action="create-order" method="post">
                <div class="cart" id="cart">
                    <div class="form-group">
                        <label for="shirt">Shirt->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="shirtPrice"><%=price.get("Shirt")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="shirt" name="shirt" onkeyup="updatePrice();">
                    </div>
                    <div class="form-group">
                        <label for="pant">Pant->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="pantPrice"><%=price.get("Pant")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="pant" name="pant" onkeyup="updatePrice();">
                    </div>
                    <div class="form-group">
                        <label for="tshirt">T Shirt->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="tShirtPrice"><%=price.get("T Shirt")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="tshirt" name="tshirt" onkeyup="updatePrice();">
                    </div>
                    <div class="form-group">
                        <label for="winter">Winter cloth-><span id="winterPrice"><%=price.get("Winter Cloth")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="winter" name="winter" onkeyup="updatePrice();">
                    </div>
                    <div class="form-group">
                        <label for="blanket">Blanket->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="blanketPrice"><%=price.get("Blanket")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="blanket" name="blanket" onkeyup="updatePrice();">
                    </div>
                    <div class="form-group">
                        <label for="other">Others->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="otherPrice"><%=price.get("Other")%></span>&nbsp;&nbsp;x</label>
                        <input type="number" id="other" name="other" onkeyup="updatePrice();">
                    </div>
                </div>
                <span style="font-size: 22px; margin: 15px 0;">Total Price <input type="test" class="totalPrice" name="totalPrice" id="totalPrice" value="0" readonly> &#x20B9 </span>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</body>
</html>