<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css">
<style type="text/css">
#header, #content, #footer {
	
}
</style>
</head>
<body>
	<!-- wrap -->
	<div id="warp">
		<!-- header -->
		<div id="header">
			<tiles:insertAttribute name="header"/>
		</div>
		
		<!-- content -->
		<div id="content">
			<tiles:insertAttribute name="content"/>
		</div>
		
		<!-- footer -->
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>
</body>
</html>