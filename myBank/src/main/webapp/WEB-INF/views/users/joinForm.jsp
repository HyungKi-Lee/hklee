<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<br><br><br>
<div id="loginDiv">
	<form action="${pageContext.request.contextPath}/joinConfirm" name="joinform" method="post">
		<fieldset>
			<legend>Join Form</legend>
			<div class="form-group row">
				<label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text" readonly="" class="form-control-plaintext"
						id="staticEmail" value="email@example.com">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1" class="form-label mt-4">Email address</label> 
					<input type="email" class="form-control"id="email" name="email" aria-describedby="emailHelp"placeholder="Enter email" style="width: 300px;"><!-- email -->
				<small id="emailHelp" class="form-text text-muted"></small>
				<c:if test="${message!=null}">
					<small id="emailError" class="form-text text-muted">${message}</small>			
				</c:if>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="form-label mt-4">Password</label>
				<input type="password" class="form-control"id="password" name="password" placeholder="Enter Password"style="width: 300px;" size="16"><!-- Password -->
				<p id="passwordHelp" class="form-text text-muted"></p>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="form-label mt-4">Password Check</label>
				<input type="password" class="form-control"id="passwordChk" placeholder="Enter Password Check"style="width: 300px;"><!-- Password Check-->
				<span id="passwordChkMsg" class="form-text text-muted"></span>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="form-label mt-4">Name</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" style="width: 300px;"><!-- Name -->
				<small id="nameHelp" class="form-text text-muted"></small>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="form-label mt-4">MobileNumber</label>
				<input type="text" class="form-control"id="mobile" name="mobile" placeholder="Enter Mobile Number"style="width: 300px;"><!-- Mobile Number -->
				<small id="mobileHelp" class="form-text text-muted"></small>
			</div>
			
			<!-- valid <-> invalid class chang
			<div class="form-group has-success">
				<label class="form-label mt-4" for="inputValid">Valid input</label>
				<input type="text" value="correct value"class="form-control is-valid" id="inputValid">
				<div class="valid-feedback">Success! You've done it.</div>
			</div>

			<div class="form-group has-danger">
				<label class="form-label mt-4" for="inputInvalid">Invalid input</label> 
				<input type="text" value="wrong value"class="form-control is-invalid" id="inputInvalid">
				<div class="invalid-feedback">Sorry, that username's taken.
					Try another?</div>
			</div>
			 -->
			
			<br><br>
			<button type="button" class="btn btn-primary" onclick="joinSub();">Join</button>
		</fieldset>
	</form>
</div>

<script type="text/javascript">
var emailChk = false;
var passwdChk = false;
var rePasswdChk = false;
var mobileChk = false;
var nameChk = false;


$("#email").keyup(function() {
	var emailReg = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	var email = $(this).val();
	if(emailReg.test(email)) {
		$("#email").removeClass("form-control is-invalid");
		$("#email").removeClass("form-control is-valid");
		
		$.ajax({
			type: "GET",
			data : "email="+email,
			url:"${pageContext.request.contextPath}/emailChk",
			dateType: "json",
			success : function(data) {
				var selectedEmail = data["email"];
				if(selectedEmail == email) {
					$("#email").removeClass("form-control is-valid");
					$("#email").addClass("form-control is-invalid");
					//invalid(email);
					$("#emailHelp").html("Already exist email");
					//$("#email").css("border", "1px solid #e83e8c");
					emailChk = false;
				} else {
					$("#email").removeClass("form-control is-invalid");
					$("#email").addClass("form-control is-valid");
					//valid(email);
					$("#emailHelp").html("You can use this Email");
					//$("#email").css("border", "1px solid #ced4da");
					emailChk = true;
				}
			},
			error : function() {
				alert("unknown Error")
			}
		});
	} else {
		$("#email").removeClass("form-control is-valid");
		$("#email").addClass("form-control is-invalid");
		//invalid();
		$("#emailHelp").text("invalid address");
		//$("#email").css("border", "1px solid #e83e8c");
		emailChk = false;
	}
});

$("#mobile").keyup(function() {
	var mobileNumberReg = RegExp(/^01[0179][0-9]{7,8}$/);
	var mobile = $(this).val();
	$("#mobile").removeClass("form-control is-invalid");
	$("#mobile").removeClass("form-control is-valid");
	
	if(mobileNumberReg.test(mobile)) {
		$("#mobile").removeClass("form-control is-invalid");
		$("#mobile").addClass("form-control is-valid");
		$("#mobileHelp").text("valid number");
		//$("#mobile").css("border", "1px solid #ced4da");
		mobileChk = true;
	} else {
		$("#mobile").removeClass("form-control is-valid");
		$("#mobile").addClass("form-control is-invalid");
		$("#mobileHelp").text("invalid number");
		//$("#mobile").css("border", "1px solid #e83e8c");
		mobileChk = false;
	}
});

$("#password").keyup(function() {
	var passwdReg = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
	var password = $("#password").val();
	$("#password").removeClass("form-control is-invalid");
	$("#password").removeClass("form-control is-valid");
	
	if(passwdReg.test(password)) {
		$("#password").removeClass("form-control is-invalid");
		$("#password").addClass("form-control is-valid");
		$("#passwordHelp").text("valid passwod");
		//$("#password").css("border", "1px solid #ced4da");
		passwdChk = true;
	} else {
		$("#password").removeClass("form-control is-valid");
		$("#password").addClass("form-control is-invalid");
		$("#passwordHelp").text("Password must include number, capital letter, small letter and at least one special letters. Additionally counts must be up to 16 from 8");
		//$("#password").css("border", "1px solid #e83e8c");
		passwdChk = false;
	}
});

$("#passwordChk").keyup(function() {
	var password = $("#password").val();
	var content = $(this).val();
	$("#passwordChk").removeClass("form-control is-invalid");
	$("#passwordChk").removeClass("form-control is-valid");
	
	if(password != content) {
		$("#passwordChk").removeClass("form-control is-valid");
		$("#passwordChk").addClass("form-control is-invalid");
		$("#passwordChkMsg").html("incorrect password");
		//$("#passwordChk").css("border", "1px solid #e83e8c");
		rePasswdChk = false;
	} else {
		$("#passwordChk").removeClass("form-control is-invalid");
		$("#passwordChk").addClass("form-control is-valid");
		$("#passwordChkMsg").html("correct password");
		//$("#passwordChk").css("border", "1px solid #ced4da");
		rePasswdChk = true;
	}
	
});

$("#name").keyup(function() {
	var nameCheck = RegExp(/^[A-Za-z가-힣]{2,20}$/);
	var name = $(this).val();
	$("#name").removeClass("form-control is-invalid");
	$("#name").removeClass("form-control is-valid");
	
	if(nameCheck.test(name)) {
		$("#name").removeClass("form-control is-invalid");
		$("#name").addClass("form-control is-valid");
		nameChk = true;
	} else {
		$("#name").removeClass("form-control is-valid");
		$("#name").addClass("form-control is-invalid");
		nameChk = false;
	}
});


/* valid - invalid change function
function initClass(cname) {
	cname.removeClass("form-control is-invalid");
	cname.removeClass("form-control is-valid");
}


function valid(cname) {
	cname.removeClass("form-control is-invalid");
	cname.addClass("form-control is-valid");
}

function invalid(cname) {
	cname.removeClass("form-control is-valid");
	cname.addClass("form-control is-invalid");
}
*/

function joinSub() {
	if(!emailChk) {
		alert("Check your Email Please");
		return false;
	}
	
	if(!mobileChk) {
		alert("Check your Mobile Number Please");
		return false;
	}
	
	if(!passwdChk) {
		alert("Check your Password Please");
		return false;
	}
	
	if(!rePasswdChk) {
		alert("Check your Password Please");
		return false;
	}
	
	if(!nameChk) {
		alert("Check your Name Please");
		return false;
	}
	//alert("Join Success");
	joinform.submit();
}
</script>