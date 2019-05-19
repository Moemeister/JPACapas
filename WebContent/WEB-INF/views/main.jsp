<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<form method="POST" action="${pageContext.request.contextPath}/form">
		<h1>Buscador de Estudiantes</h1>
		<label>Ingrese el ID</label>
		<input type="text" name="code">
		<input type="submit" name="enviar" value="Buscar">
	</form>
	<br>
	<form action="${pageContext.request.contextPath}/save" method = "post">
		<input type="submit" value="Agregar nuevo usuario">
	</form>
	<br>
	<form action="${pageContext.request.contextPath}/delete" method="post"> 
		<label>Eliminar: </label><input type="text" name="name">
		<input type="submit" value="Delete">
	</form>
	<br>
	
	<table>
		<tr><th>Option</th>
			<th>Name</th>
			<th>Last Name</th>
			<th>Age</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${students}" var="students">
			<tr>
					<th><form action="${pageContext.request.contextPath}/edit" method="post">
            		<input type="hidden" name="code" value= "${students.cStudent }">
            		<input type="Submit" value="Editar">
					</form></th>
					<td>${students.sName}</td>
					<td>${students.lName}</td>
					<td>${students.sAge}</td>
					<td>${students.activoDelegate}</td>
					
			</tr>
		</c:forEach>
	
	</table>
	
	
</body>
</html>