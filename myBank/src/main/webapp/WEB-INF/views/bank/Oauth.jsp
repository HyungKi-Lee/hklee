<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	 <h1>인증완료 페이지(임시)</h1>
	 <p>받아온 인증 code : ${code}</p>
	 code : <input type="text" id="code" name="code" value="${code}">
	 client_id : <input type="text" id="client_id" name="client_id">
	 client_secret : <input type="text" id="client_secret" name="client_secret">
	 redirect_uri : <input type="text" id="redirect_uri" name="redirect_uri">
	 grant_type : <input type="text" id="grant_type" name="grant_type">
	 <button type="button" id="getAccessToeknBtn">토큰받기</button>
</body>
<script type="text/javascript">
$("#getAccessToeknBtn").click(function() {
	var code = $("#code").val();
	alert(code);
	$.ajax({
		url : "${pageContext.request.contextPath}/getToken",
		type : "post",
		data : code,
		dataTyep : "json",
		success : function(result) {
			var getCode = result["code"];
			var getClient_id = result["client_id"];
			var getClient_secret = result["client_secret"];
			var getRedirect_uri = result["redirect_uri"];
			var getGrant_type = result["grant_type"];
			
			code.html(getCode);
			client_id.html(getClient_id);
			client_secret.html(getClient_secret);
			redirect_uri.html(getRedirect_uri);
			grant_type.html(getGrant_type);
		},
		error : function(response) {
			alert("에러발생");
		}
	})
});
</script>
</html>