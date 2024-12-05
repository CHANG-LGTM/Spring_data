<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지의 문자 인코딩 설정 -->

<!DOCTYPE html> <!-- HTML5 문서 유형 선언 -->
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지의 문자 인코딩을 UTF-8로 설정 -->
<title>Insert title here</title> <!-- 페이지 제목 설정 -->
</head>
<body>
<!-- 게시글 작성 폼 -->
<form action="/board/save" method="post"> <!-- 폼 전송 시 '/board/save' 경로로 POST 방식으로 데이터 전송 -->
		<!-- 작성자 입력 필드 -->
		<input type="text" name="boardWriter" placeholder="작성자"><br><br>
		<!-- 비밀번호 입력 필드 -->
		<input type="text" name="boardPass" placeholder="비밀번호"><br><br>
		<!-- 제목 입력 필드 -->
		<input type="text" name="boardTitle" placeholder="제목"><br><br>
		<!-- 내용 입력 필드 (여러 줄의 텍스트 입력) -->
		<textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea><br><br>
		<!-- 제출 버튼 -->
		<input type="submit" value="작성">
</form>
</body>
</html>
