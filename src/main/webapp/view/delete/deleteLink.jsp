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
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}
</style>
<body>
	<c:import url="/view/style/sidebar.jsp"/>

	<div style="margin-left:25%">
    <div class="w3-container w3-dark-grey">
      <h1>Home page of JAVA DEVELOPER #6</h1>
    </div>

	<div class="w3-container">

      <form action="/deleteLink" method="POST">
        <select id="table" name="table">
            <option value="customers_companies">Customers-Companies</option>
            <option value="project_developers">Project-Developers</option>
            <option value="developer_skills">Developer-Skills</option>
        </select>
        <input type="number" id="projectId" name="projectId" placeholder="Enter project ID">
        <input type="number" id="customerId" name="customerId" placeholder="Enter customer ID">
        <input type="number" id="developerId" name="developerId" placeholder="Enter developer ID">
        <input type="number" id="companyId" name="companyId" placeholder="Enter company ID">
        <input type="number" id="skillId" name="skillId" placeholder="Enter skill ID">
        <input type="submit" value="Submit">
      </form>
	</div>
 </div>
</body>
</html>