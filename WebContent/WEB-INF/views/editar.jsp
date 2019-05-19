<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/editInfo" method ="POST" modelAttribute = "student">
		<form:input type="hidden" name="id" path="cStudent"/>
		<label>Ingrese el nombre</label>
		<form:input type = "text" name="name" path="sName"/><br>
		<label>Ingrese un Apellido</label>
		<form:input type = "text" name="lname" path="lName"/><br>
		<label>Ingrese edad</label>
		<form:input type = "text" name="age" path="sAge"/><br>
		<label>Estado del Estudiante</label>
		<form:radiobutton name="status" path="bActivo" value ="true"/><label>Activo</label>
		<form:radiobutton name="status" path="bActivo" value ="false"/><label>Inactivo</label><br>
		<input type="submit" value="Actualizar Estudiante">
		
		
	</form:form>
</body>
</html>