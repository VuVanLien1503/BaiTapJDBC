<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditCategory</title>
</head>
<body>
<h1>EditCategory</h1>
<form action="/CategoryServlet?action=edit&id=${category.id}"method="post">
  <table border="1" style="border-collapse: collapse">
    <tr>
      <th>ID</th>
      <td>${category.id}</td>
    </tr>
    <tr>
      <th>name</th>
      <td><input type="text" name="name" value="${category.name}"></td>
    </tr>
    <tr>
      <td><a href="/CategoryServlet">Back</a></td>
      <td  style="text-align: center"><button>Update</button></td>
    </tr>
  </table>
</form>
</body>
</html>
