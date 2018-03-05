<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/HugHug2/assets/assets_diary/css/main.css" />
<title>일기쓰기</title>
</head>
<body>
		<!-- Header -->
			<!-- �ϱ⺸�� ������ ���� -->
			<header id="header">
				<div class="logo txt_ko"><a href="#">일기 쓰기</a></div>
			</header>

		<!-- Main -->
			<section id="main">
				<!-- inner �ȿ� �ִ� �͵��� ���� �����ӿ� �׷����ϴ�. -->
				<div class="inner inputbox">
					<textarea name="message" id="message" placeholder="Enter your message" rows="13"></textarea>
				</div>
				
				<!-- ���� ���ε� -->
				<div class="inner inputbox file-upload">
					<div style="text-align: center">
						<a href="#" class="button special fit" onclick="$('.file-upload-input').trigger( 'click' )">Add Image</a>
					</div>
				
					<div class="image-upload-wrap">
						<input class="file-upload-input" type='file' onchange="readURL(this);" accept="image/*" />
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
				
				<div style="text-align: center; margin-top: 10px; margin-bottom: 10px; ">
					<a href="#" class="button special">취소</a>
					<a href="#" class="button special">작성</a>
					<a href="#" class="button special">메인확면</a>
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
			<script src="assets/assets_diary/js/jquery.min.js"></script>
			<script src="assets/assets_diary/js/jquery.poptrox.min.js"></script>
			<script src="assets/assets_diary/js/skel.min.js"></script>
			<script src="assets/assets_diary/js/util.js"></script>
			<script src="assets/assets_diary/js/main.js"></script>
			
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