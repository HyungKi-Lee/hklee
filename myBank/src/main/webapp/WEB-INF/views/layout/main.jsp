<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
.cardDiv {
	width: 50%;
	margin: 0 auto;
	overflow: hidden;
}
</style>
<div>
	<img alt="test-Image" src="${pageContext.request.contextPath}/resources/img/test.jpg" style="width: 100%;">
</div>


<br>
<div class="cardDiv">
	<div class="card text-white bg-primary mb-3" style="max-width: 30rem; height:400px; float: left;">
		<c:choose>
			<c:when test="${!empty(sessionScope.loginSession) || !empty(sessionScope.kakaoLoginSession)}">
				<div class="card-header">My Account ${sessionScope.loginSession.name}${sessionScope.kakaoLoginSession}님</div>
				<div class="card-body">
					<h4 class="card-title">balance : </h4>
					<img alt="" src="${pageContext.request.contextPath}/resources/img/test2.jpg" width="100%">
					<p id="authBtn">Connect to Account</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="card-header">Account needs login</div>
				<div class="card-body">
					<h4 class="card-title">balance : </h4>
					<img alt="" src="${pageContext.request.contextPath}/resources/img/test2.jpg" width="100%">
				</div>
			</c:otherwise>	
		</c:choose>
	</div>
	<div class="card text-white bg-secondary mb-3"style="max-width: 30rem; height:400px; float: right;">
		<div class="card-header">Header</div>
		<div class="card-body">
			<h4 class="card-title">Secondary card title</h4>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>
	<div class="card text-white bg-success mb-3" style="max-width: 30rem; height:400px; float: left;">
		<div class="card-header">Header</div>
		<div class="card-body">
			<h4 class="card-title">Success card title</h4>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>

	<div class="card text-white bg-danger mb-3" style="max-width: 30rem; height:400px; float: right;">
		<div class="card-header">Header</div>
		<div class="card-body">
			<h4 class="card-title">Danger card title</h4>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>

	<div class="card text-white bg-warning mb-3" style="max-width: 30rem; height:400px; float: left;">
		<div class="card-header">Header</div>
		<div class="card-body">
			<h4 class="card-title">Warning card title</h4>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>
	<div class="card text-white bg-info mb-3" style="max-width: 30rem; height:400px; float: right;">
		<div class="card-header">Header</div>
		<div class="card-body">
			<h4 class="card-title">Info card title</h4>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>
</div>
<script type="text/javascript">
$("#authBtn").click(function() {
	location.href="${pageContext.request.contextPath}/Oauth";
	/*
	var tmpWindow = window.open('about:blank');
	tmpWindow.location = "https://testapi.openbanking.or.kr/oauth/2.0/authorize?" +
    "response_type=code&"+
    "client_id=a3e010c4-db5e-469b-aa64-c35a5a4726ea&"+
    "redirect_uri=http://localhost:8080/controller/Oauth&"+
    "scope=login inquiry transfer&"+
    "state=b80BLsfigm9OokPTjy03elbJqRHOfGSY&"+
    "auth_type=0"
    */
});
</script>
