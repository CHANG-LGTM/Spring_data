<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- jQuery 라이브러리 로드 -->
<meta charset="UTF-8">
<title>detail.jsp</title>
</head>
<body>
<!-- 게시글 정보 테이블 -->
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

<!-- 게시글 이동 버튼들 -->
<button onclick="listFn()">목록</button> <!-- 목록 페이지로 이동하는 버튼 -->
<button onclick="updateFn()">수정</button> <!-- 수정 페이지로 이동하는 버튼 -->
<button onclick="deleteFn()">삭제</button> <!-- 게시글 삭제를 실행하는 버튼 -->

<!-- 댓글 작성 입력란 -->
<div>
    <input type="text" id="commentWriter" placeholder="작성자"> <!-- 댓글 작성자 입력 -->
    <input type="text" id="commentContents" placeholder="내용"> <!-- 댓글 내용 입력 -->
    <button id="comment-write-btn" onclick="commentWrite()">댓글작성</button> <!-- 댓글 작성 버튼 -->
</div>

<!-- 댓글 목록 출력 부분 -->
<div id="comment-list">
	<table>
		<tr>
			<th>댓글번호</th>
			<th>작성자</th>
			<th>내용</th>
			<th>작성시간</th>
		</tr>
		<c:forEach items="${commentList}" var="comment"> <!-- 댓글 리스트 반복 출력 -->
			<tr>
				<td>${comment.id}</td> <!-- 댓글 ID -->
				<td>${comment.commentWriter}</td> <!-- 댓글 작성자 -->
				<td>${comment.commentContent}</td> <!-- 댓글 내용 -->
				<td>${comment.commentCreatedTime}</td> <!-- 댓글 작성 시간 -->
		</c:forEach>	
	</table>
</div>

</body>

<script>
// 목록 페이지로 이동하는 함수
const listFn = ()=> {
	const page = '${page}'; // 현재 페이지 정보를 받아옴
	location.href = "/board/paging?page=" + page; // 목록 페이지로 이동
}

// 수정 페이지로 이동하는 함수
const updateFn = ()=>  {
	const id = '${board.id}'; // 게시글 ID를 가져옴
	location.href = "/board/update?id=" +id; // 수정 페이지로 이동
}

// 게시글 삭제 실행하는 함수
const deleteFn = () => {
	const id = '${board.id}'; // 게시글 ID를 가져옴
	location.href = "/board/delete?id=" +id; // 게시글 삭제 실행
}

// 댓글 작성 처리 함수
const commentWrite = () => {
    const writer = document.getElementById("commentWriter").value; // 작성자 값 가져오기
    const contents = document.getElementById("commentContents").value; // 내용 값 가져오기
    const board = '${board.id}'; // 게시글 ID 가져오기
    
    // AJAX 요청을 통해 댓글 작성
    $.ajax({
        type: "post",
        url: "/comment/save", // 댓글 저장 URL
        data: {
            commentWriter: writer, // 댓글 작성자
            commentContents: contents, // 댓글 내용
            boardId: board // 게시글 ID
        },
        dataType: "json", // 응답 데이터 타입
        success: function(commentList) { // 댓글 저장 성공 시
            console.log("작성성공");
            let output = "<table>";
            output += "<tr><th>댓글번호</th>";
            output += "<th>작성자</th>";
            output += "<th>내용</th>";
            output += "<th>작성시간</th></tr>";
            // 댓글 목록을 테이블 형식으로 업데이트
            for(let i in commentList){
                output += "<tr>";
                output += "<td>"+commentList[i].id+"</td>";
                output += "<td>"+commentList[i].commentWriter+"</td>";
                output += "<td>"+commentList[i].commentContents+"</td>";
                output += "<td>"+commentList[i].commentCreatedTime+"</td>";
                output += "</tr>";
            }
            output += "</table>";
            document.getElementById('comment-list').innerHTML = output; // 댓글 목록 갱신
            // 댓글 작성 후 입력란 비우기
            document.getElementById('commentWriter').value='';
            document.getElementById('commentContents').value='';
        },
        error: function() {   // 댓글 저장 실패 시
            console.log("실패");
        }
    });
}

</script>
</html>
