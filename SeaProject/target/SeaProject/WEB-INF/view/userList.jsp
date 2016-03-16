<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Seashells Customers</title>
</head>
<body>
 
<h2>Customer List for Seashells Project</h2>
 
<h3>Customers that purchased app</h3>
<c:if  test="${!empty userList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Email</th>
</tr>
<c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.lastname}, ${user.firstname} </td>
        <td>${user.email}</td> 
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>