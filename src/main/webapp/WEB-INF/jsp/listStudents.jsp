<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th><th>Edit</th></tr>

<c:forEach var="s" items="${students}">
<tr>
<td>${s.id}</td>
<td>${s.name}</td>
<td>${s.email}</td>
<td><a href="/students/edit/${s.id}">Edit</a></td>
</tr>
</c:forEach>

</table>