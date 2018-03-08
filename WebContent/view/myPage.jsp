<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="/HugHug2/assets/assets_signup/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/css/util.css">
	<link rel="stylesheet" type="text/css" href="/HugHug2/assets/assets_signup/css/main.css">
<!--===============================================================================================-->
<title>Mypage</title>
</head>

<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
				<form class="login100-form validate-form" method = "post" name ="myPage" action="myPageUpdate">
					<span class="login100-form-title p-b-59">
						My Page
					</span>
					
					 
					<div class="wrap-input100 validate-input" data-validate="Name is required">
						<span class="label-input100">Username</span>
	
						<p>
						<br>
						${member.name}
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
						<span class="label-input100">Email</span>
				
						<p>
						<br>
						${member.email}
						<span class="focus-input100"></span>
					</div>


		

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
			
							<button class="login100-form-btn">
								수정
							</button>
						</div>
						

	
						<a href="Main" class="dis-block txt3 hov1 p-r-30 p-t-10 p-b-10 p-l-30">
							Main
							<i class="fa fa-long-arrow-right m-l-5"></i>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>

<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/bootstrap/js/popper.js"></script>
	<script src="/HugHug2/assets/assets_signup/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/daterangepicker/moment.min.js"></script>
	<script src="/HugHug2/assets/assets_signup/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="/HugHug2/assets/assets_signup/js/main.js"></script>

</body>
</html>