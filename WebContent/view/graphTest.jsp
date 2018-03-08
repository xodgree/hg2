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
 <table class="w3-table-all" width="700">
      <tr class="w3-babypink" style="background-color:rgba(255, 0, 0, 0.4);">
         <td>날짜</td>
         <td >감정</td>
  
         <c:forEach var="article" items="${graphList}">
         <tr>
		<tr>
      </tr></c:forEach>
</table>

<script>
	var weeklydatas = ${weeklyGraphList};
	var monthlydatas = ${monthlyGraphList};
	console.log(weeklydatas);
	console.log(monthlydatas);
</script>

</body>
</html>