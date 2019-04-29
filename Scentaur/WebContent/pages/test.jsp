<%@page import="com.github.javaparser.ast.CompilationUnit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<pre>
	<%
		// display the compilation unit
		List<CompilationUnit> compilationUnit = (List<CompilationUnit>)request.getAttribute("compilationUnit");
		out.println(compilationUnit.get(0));	
	%>
	</pre>
</body>
</html>