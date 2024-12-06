<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지의 언어와 인코딩 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL Core 태그 라이브러리 사용 선언 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지의 문자 인코딩 설정 -->
<title>list</title> <!-- 페이지 제목 설정 -->
</head>
<body>
<table>
	<tr>
		<th>id</th> <!-- id 열 제목 -->
		<th>title</th> <!-- title 열 제목 -->
		<th>writer</th> <!-- writer 열 제목 -->
		<th>date</th> <!-- date 열 제목 -->
		<th>this</th> <!-- hits 열 제목 -->
	</tr>
	
	<!-- boardList로 전달된 데이터 목록을 반복하여 출력 -->
	<c:forEach items="${boardList}" var="board">
		<tr>
			<td>${board.id}</td> <!-- 게시글 id 출력 -->
			<td>
				<a href="/board?id=${board.id}">${board.boardTitle}</a> <!-- 게시글 제목에 링크 추가, 클릭 시 해당 게시글로 이동 -->
			</td>
			<td>${board.boardWriter}</td> <!-- 게시글 작성자 출력 -->
			<td>${board.boardCreatedTime}</td> <!-- 게시글 작성 시간 출력 -->
			<td>${board.boardHits}</td> <!-- 게시글 조회수 출력 -->
		</tr>
	</c:forEach>

</table>
</body>
</html>
