<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" language="javascript">
$(document).ready(function(){
	$('#Btn').click(function() { 
	var result = confirm('Are you sure you want to do this?');
		if(result) {
			//yes location.replace('index.php'); 
			location.replace(Main);
			
			} else { 
				//no 
				location.replace(myPageUpdate);
				} 
		}); 
	});

</script>
</body>
</html>