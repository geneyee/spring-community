<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>
<script>
	alert('저장되었습니다.')
	location.href = '${root}board/read?board_info_idx=${writeContent.content_board_idx}&content_idx=${writeContent.content_idx}&page=1'
</script>
<!-- write.jsp에서 hidden path="content_board_idx"이렇게 게시판 번호 히든으로 넘겨줌 -->