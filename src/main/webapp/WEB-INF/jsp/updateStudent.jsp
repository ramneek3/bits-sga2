<form action="/students/update/${student.id}" method="post">
    Name: <input type="text" name="name" value="${student.name}"/><br/>
    Email: <input type="text" name="email" value="${student.email}"/><br/>
    <button type="submit">Update</button>
</form>