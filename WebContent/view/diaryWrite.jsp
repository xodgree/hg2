<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/HugHug2/assets/assets_diary/css/main.css" />
<title>Write Diary</title>
</head>
<body>	
		<!-- Header -->
			
			<header id="header">
				<div class="logo txt_ko"><a href="#">Write Diary</a></div>
			</header>
			

		<!-- Main -->

			<section id="main">
				<!-- input regdate -->
				<div class="inner inputbox" style="text-align: center;">
					<!-- Date: <input type="date" name="regdate" form="submitForm" style="border: 0; width: 400px;"> -->
					제목 <input type="text" name ="title"  form="submitForm">
				</div> 

				<!-- Input Message -->
				<div class="inner inputbox">
					<textarea name="content" id="message" placeholder="Enter your message" rows="13" form="submitForm"></textarea>
				</div>
				
				<!-- 감정을 표현하는 라디오 버튼입니다.
				라디오 버튼은 다수를 선택할 수 없도록 만들어진 체크박스입니다. -->
				<!-- Input Radio Emotion -->
				<div class="inner inputbox" style="text-align: center">
					<input type="radio" name="emotion" value="기쁨" checked form="submitForm">Joy
					<input type="radio" name="emotion" value="보통" form="submitForm">So so
					<input type="radio" name="emotion" value="나쁨" form="submitForm">Sad
				</div>
				
				<!-- input file Image upload -->
				<div class="inner inputbox file-upload">
					<div style="text-align: center">
						<a href="#" class="button special fit" onclick="$('.file-upload-input').trigger( 'click' )">Add Image</a>
					</div>
				
					<div class="image-upload-wrap">
						<input class="file-upload-input" type="file" name="uploadImage" onchange="readURL(this);" accept="image/*" form="submitForm"/>
					<div class="drag-text">
						<h3>Drag and drop a file<br>or select add Image</h3>
					</div>
				</div>
				
				<div class="file-upload-content">
					<img class="file-upload-image" src="#" alt="your image" />
						<div class="image-title-wrap">
							<div style="text-align: center">
								<a href="#" class="button special fit" onclick="removeUpload()">Remove <span class="image-title">Uploaded Image</span></a>
							</div>
						</div>
					</div>
				</div>
					
				<input type="hidden" name="useremail" value="${useremail}">
				
				<div style="text-align: center; margin-top: 10px; margin-bottom: 10px;">
					<form id="submitForm" action="diaryWritePro" method="post" enctype="multipart/form-data"></form>
					
					<!-- input submit rewrite -->
					<input type="reset" form="submitForm" class="button special" value="Rewrite">
					
					<!-- input submit confirm-->
					<input type="submit" form="submitForm" class="buttion special" value ="작성">

					<!-- input submit Go to Main-->
					<a href="Main" class="button special">Go to Main</a>
				</div>
			</section>
			
		<!-- Footer -->
			<footer id="footer">
				<div class="container">
				</div>
				<div class="copyright">
				</div>
			</footer>

		<!-- Scripts -->
			<script src="/HugHug2/assets/assets_diary/js/jquery.min.js"></script>
			<script src="/HugHug2/assets/assets_diary/js/jquery.poptrox.min.js"></script>
			<script src="/HugHug2/assets/assets_diary/js/skel.min.js"></script>
			<script src="/HugHug2/assets/assets_diary/js/util.js"></script>
			<script src="/HugHug2/assets/assets_diary/js/main.js"></script>
			
			<script>
			
			function readURL(input) {
				if (input.files && input.files[0]) {
				    var reader = new FileReader();

				    reader.onload = function(e) {
				    	$('.image-upload-wrap').hide();

				    	$('.file-upload-image').attr('src', e.target.result);
				    	$('.file-upload-content').show();

				    	$('.image-title').html(input.files[0].name);
				    };

				    reader.readAsDataURL(input.files[0]);

					} else {
						removeUpload();
					}
				}

				function removeUpload() {
					$('.file-upload-input').replaceWith($('.file-upload-input').clone());
					$('.file-upload-content').hide();
					$('.image-upload-wrap').show();
				}
				$('.image-upload-wrap').bind('dragover', function () {
					$('.image-upload-wrap').addClass('image-dropping');
				});
				$('.image-upload-wrap').bind('dragleave', function () {
					$('.image-upload-wrap').removeClass('image-dropping');
				});
			</script>
	</body>
</html>