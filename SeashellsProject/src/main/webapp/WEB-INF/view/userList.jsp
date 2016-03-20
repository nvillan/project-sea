<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Seashells Customers</title>
</head>
<body>
 
<h2>Customer List for Seashells App</h2>
 

<c:if  test="${!empty userList}">
<table class="data">
<tr>
    <th>Account Number</th>
    <th>Name</th>
    <th>Email</th>
    <th>Edition Purchased</th>
</tr>
<c:forEach items="${userList}" var="user">
    <tr>
    	<td>${user.accountNumber}</td>
        <td>${user.lastname}, ${user.firstname} </td>
        <td>${user.email}</td>
        <td>${user.edition}</td>
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>