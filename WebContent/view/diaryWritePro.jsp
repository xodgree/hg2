<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:if test="${emotion == '���'}">
	<script type="text/javascript">
	<a href = "Main" Onclick = "alert('����� ����!')";>
	</script>
</c:if>

<c:if test="${emotion == '����'}">
	<script type="text/javascript">
	//MainDb�� ������ �ϱ� list�ѷ�����.
		<a href = "Main" Onclick = "alert('����� �����̾�')";/>
	</script>
</c:if>

<c:if test="${emotion == '����'}">
	<script type="text/javascript">
	<a href = "Main" Onclick = "alert('���� �� �־�?')";/>
	</script>
</c:if>

</body>
</html> 