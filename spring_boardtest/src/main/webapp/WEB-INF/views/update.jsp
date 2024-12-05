<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지의 문자 인코딩 설정 -->

<!DOCTYPE html> <!-- HTML5 문서 유형 선언 -->
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지의 문자 인코딩을 UTF-8로 설정 -->
    <title>update.jsp</title> <!-- 페이지 제목 설정 -->
</head>
<body>

<!-- 게시글 수정 폼 -->
<form action="/board/update" method="post" name="updateForm"> <!-- 폼 전송 시 '/board/update' 경로로 POST 방식으로 데이터 전송 -->
	<!-- 게시글 ID (수정할 게시글의 ID, disabled로 수정 불가능) -->
	<input type="hidden" name="id" value="${board.id}" disabled><br><br>
	
	<!-- 작성자 (수정 불가능, 단지 표시용) -->
	<input type="text" name="boardWriter" value="${board.boardWriter}" disabled><br><br>
	
	<!-- 비밀번호 입력 필드 (수정 시 확인용) -->
	<input type="text" name="boardPass" id="boardPass" placeholder="비밀번호"><br><br>
	
	<!-- 제목 입력 필드 (기존 제목이 자동으로 표시됨) -->
	<input type="text" name="boardTitle" value="${board.boardTitle}"><br><br>
	
	<!-- 내용 입력 필드 (기존 내용이 자동으로 표시됨) -->
	<textarea name="boardContents" cols="30" rows="10">${board.boardContents}</textarea><br><br>
	
	<!-- 수정 버튼 클릭 시 updateReqFn() 호출 -->
	<input type="button" value="수정" onclick="updateReqFn()">
</form>

</body>
<script>
	// 수정 요청 함수
	const updateReqFn = ()=> {
		// 입력된 비밀번호 값과 DB에 저장된 비밀번호 값 비교
		const passInput = document.getElementById("boardPass").value;
		const passDB = "${board.boardPass}";
		
		// 비밀번호가 일치하면 폼을 제출, 아니면 경고 메시지 표시
		if (passInput == passDB) {
			document.updateForm.submit();
		} else {
			alert("비밀번호가 일치하지 않습니다!!!!!!!.")
		}
	}
</script>
</html>
