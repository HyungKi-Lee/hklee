<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<br><br><br>
<div id="loginDiv">
	<form action="${pageContext.request.contextPath}/main" name="loginForm" method="post">
	  <fieldset>
	    <legend>Login</legend>
	    <label class="form-label mt-4">Please Insert your Email and Password</label>
 		 <div class="form-floating mb-3">
    		<input type="email" class="form-control" id="email" placeholder="name@example.com">
    		<label for="floatingInput">Email address</label>
  		</div>
  		<div class="form-floating">
    		<input type="password" class="form-control" id="password" placeholder="Password">
    		<label for="floatingPassword">Password</label>
    	</div>
    	<div>
    		<small id="loginError" class="form-text text-muted"></small>
    	</div>
	    <br><br>
	    <button type="button" class="btn btn-primary" id="loginBtn">Login</button>
	    <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/join';">Join</button>
	    <button type="button" class="btn btn-primary" id="kakaoLoginBtn" onclick="kakaoLogin();">Login with KaKao</button>
	    <div id="kakao-login-btn"></div>
	  </fieldset>
	</form>
</div>
<script type="text/javascript">




$("#loginBtn").click(function() {
	var inputEmail = $("#email").val();
	var inputPasswd = $("#password").val();
	var params = {"email":inputEmail,"password" : inputPasswd}
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/loginCheck",
		data : params,
		dataType : "json",
		success : function(result) {
			var isValid = result["isValid"];
			
			if(isValid) {
				loginForm.submit();
			} else {
				$("#loginError").html("ID or Password is not mathed");
			}
		},
		error : function() {
			
		}
	});
});

function kakaoLogin() {
	Kakao.init('fee7a9b0b0704c4721018c5f8bdbce3f');
	//alert(Kakao.isInitialized());
	Kakao.Auth.login({
		success : function(response) {
			Kakao.API.request({
				url : '/v2/user/me',
				success : function(response) {
					var nickname = response.properties['nickname'];
					var code = response.properties['code'];
					location.href="${pageContext.request.contextPath}/kakaoLogin?nickname="+nickname+"&code="+code;
				},
				fail : function (error) {
					alert("api 요청 실패");
				}
			});
		},
		fail : function(error) {
			alert("로그인 실패");
		}
	});	
}

function kakaoLogout() {
	
}
</script>