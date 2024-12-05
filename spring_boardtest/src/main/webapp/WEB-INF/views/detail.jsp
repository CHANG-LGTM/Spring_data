<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
</head>
<body>
<table>
	<tr>
		<th>id</th>
		<td>${board.id}</td> <!-- 게시글 ID 출력 -->
	</tr>	
	
	<tr>
		<th>writer</th>
		<td>${board.boardWriter}</td> <!-- 게시글 작성자 출력 -->
	</tr>	
	
	<tr>
		<th>date</th>
		<td>${board.boardCreatedTime}</td> <!-- 게시글 작성일 출력 -->
	</tr>	
	
	<tr>
		<th>hits</th>
		<td>${board.boardHits}</td> <!-- 게시글 조회수 출력 -->
	</tr>	
	
	<tr>
		<th>title</th>
		<td>${board.boardTitle}</td> <!-- 게시글 제목 출력 -->
	</tr>	
	
	<tr>
		<th>contents</th>
		<td>${board.boardContents}</td> <!-- 게시글 내용 출력 -->
	</tr>	
</table>
<button onclick="listFn()">목록</button> <!-- 목록 페이지로 이동하는 버튼 -->
<button onclick="updateFn()">수정</button> <!-- 수정 페이지로 이동하는 버튼 -->
<button onclick="deleteFn()">삭제</button> <!-- 게시글 삭제를 실행하는 버튼 -->
</body>
<script>
	const listFn = ()=> {
		const page = '${page}'; // 현재 페이지 정보를 받아옴
		location.href = "/board/paging?page=" + page; // 목록 페이지로 이동
	}
	const updateFn = ()=>  {
		const id = '${board.id}'; // 게시글 ID를 가져옴
		location.href = "/board/update?id=" +id; // 수정 페이지로 이동
	}
	const deleteFn = () => {
		const id = '${board.id}'; // 게시글 ID를 가져옴
		location.href = "/board/delete?id=" +id; // 게시글 삭제 실행
	}
</script>
</html>
