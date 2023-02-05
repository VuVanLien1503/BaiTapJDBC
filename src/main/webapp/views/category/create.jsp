<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreatCategory</title>
</head>
<body>
<h1>Create New Category</h1>
<form action="/CategoryServlet?action=create" method="post">
<table border="1">
    <tr>
        <td>NameCategory<input type="text"name="name"></td>
    </tr>
    <tr><td style="text-align: center"><button>Create</button></td></tr>
</table>
</form>
</body>
</html>
