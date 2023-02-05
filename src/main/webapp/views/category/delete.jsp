<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>delete</title>
</head>
<body>
<form action="/CategoryServlet?action=delete&id=${category.id}" method="post">
  <table>
    <tr>
      <th colspan="2"><h1> I Want Delete Category ${category.name}?</h1> </th>
    </tr>
    <tr>
      <td><a href="/CategoryServlet">Back</a></td>
      <td  style="text-align: center"><button>Delete</button></td>
    </tr>
  </table>
</form>
</body>
</html>
