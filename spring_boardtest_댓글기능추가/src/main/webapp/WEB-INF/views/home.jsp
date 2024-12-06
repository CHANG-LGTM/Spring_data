<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL Core 라이브러리 사용 선언 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- 페이지 인코딩 및 콘텐츠 유형 설정 -->
<html>
<head>
	<title>Home</title> <!-- 페이지 제목 설정 -->
</head>
<body>
<h1>
	Hello world!  <!-- 페이지 상단에 "Hello world!" 텍스트 출력 -->
</h1>

<P>  The time on the server is ${serverTime}. </P> <!-- 서버에서 전달된 시간 값을 화면에 출력 -->
</body>
</html>
