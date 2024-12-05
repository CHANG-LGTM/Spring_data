<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <title>paging</title>
</head>
<body>
<div>
    <!-- 테이블 시작: 게시글 목록을 표시 -->
    <table>
        <tr>
            <!-- 테이블 헤더: 각 열의 제목 (id, title, writer, date, hits) -->
            <th>id</th>
            <th>title</th>
            <th>writer</th>
            <th>date</th>
            <th>hits</th>
        </tr>
        
        <!-- boardList에 있는 각 게시글을 반복하여 출력 -->
        <c:forEach items="${boardList}" var="board">
            <tr>
                <!-- 게시글 id 출력 -->
                <td>${board.id}</td>
                <!-- 게시글 제목 출력, 제목을 클릭하면 해당 게시글로 이동 -->
                <td>
                    <a href="/board?id=${board.id}&page=${paging.page}">${board.boardTitle}</a>
                </td>
                <!-- 게시글 작성자 출력 -->
                <td>${board.boardWriter}</td>
                <!-- 게시글 작성일 출력 -->
                <td>${board.boardCreatedTime}</td>
                <!-- 게시글 조회수 출력 -->
                <td>${board.boardHits}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span>[이전]</span>
        </c:when>
      <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/board/paging?page=${paging.page-1}">[이전]</a>
        </c:otherwise>
    </c:choose>

    <%--  for(int i=startPage; i<=endPage; i++)      --%>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
            <c:when test="${i eq paging.page}">
                <span>${i}</span>
            </c:when>

           <c:otherwise>
    <!-- 페이지 번호가 현재 페이지가 아니면, 해당 페이지 번호로 링크를 생성 -->
    <a href="/board/paging?page=${i}">${i}</a>
</c:otherwise>
</c:choose>
</c:forEach>

<!-- 페이지 네비게이션에서 '다음' 버튼 처리 -->
<c:choose>
    <c:when test="${paging.page>=paging.maxPage}">
        <!-- 현재 페이지가 마지막 페이지라면 '다음' 버튼을 비활성화 -->
        <span>[다음]</span>
    </c:when>
    <c:otherwise>
        <!-- 마지막 페이지가 아니면 '다음' 버튼 클릭 시 다음 페이지로 이동 -->
        <a href="/board/paging?page=${paging.page+1}">[다음]</a>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
