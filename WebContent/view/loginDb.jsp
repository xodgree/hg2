<%@page import="java.sql.ResultSet"%>
<%@page import="memberDb.MemberDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  request.setCharacterEncoding("euc-kr"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--result�� 1�� �°�, email�� admin@hughug.com�̸� ȸ������ �������� �����ϴ�. 
	result�� 1�� �°�, email�� adim�� �ƴϸ� ����.jsp�� �̵��մϴ�. 
	eq = equal , ne = not equal 
	���� ���Ҷ��� ���� ó�� ���� ���� ���Ҷ��� ==, != �̷��� ��.
	--> 
<c:if test="${result == 1}">
	<script type="text/javascript">
		window.location.href = "/view/Main.jsp";
	</script>
</c:if>
<c:if test="${result == 0}">
	<script type="text/javascript">
		alert("��й�ȣ�� ���� �ʽ��ϴ�");
		history.go(-1);
	</script>
</c:if>

<c:if test="${result == -1}">
	<script type="text/javascript">
		alert("�̸����� �������� �ʽ��ϴ�.");
		history.go(-1);
	</script>
</c:if>

</body>
</html>