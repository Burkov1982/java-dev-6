<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java DEV 6</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<c:import url="/view/style/sidebar.jsp"/>

<div style="margin-left:25%">

<div class="w3-container w3-dark-grey">
  <h1>Home page of JAVA DEVELOPER #6</h1>
</div>

<div class="w3-container">
		<p>List of customers</p>
        <c:forEach items="${customers}" var="customer">
            Customer id: ${customer.customer_id}<br>
            Customer name: ${customer.customer_name}<br><br>
        </c:forEach>
</div>

</div>
</body>
</html>