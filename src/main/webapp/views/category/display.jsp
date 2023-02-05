<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListCategory</title>
</head>
<body>
<h1>------------------ListCategory--------------------</h1>
<table border="1"style="border-collapse: collapse;margin-left: 200px">
  <tr>
    <td colspan="4">
      <form action="/CategoryServlet?action=find" style="margin: 0px">
        <input type="text"name="name"placeholder="Enter Name">
        <button>Search</button>
      </form>
    </td>
  </tr>
  <tr>
    <td colspan="4" style="text-align: right">
      <a href="views/category/create.jsp">Create New Category</a></td>
  </tr>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th colspan="2">Action</th>
  </tr>
  <c:forEach items="${listCategory}" var="element">
    <tr>
      <td>${element.id}</td>
      <td>${element.name}</td>
      <td><a href="/CategoryServlet?action=edit&id=${element.id}">Edit</a></td>
      <td><a href="/CategoryServlet?action=delete&id=${element.id}">Delete</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
