<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title}</title>
</head>
<style>
.main_body {
	width: 800px;
	margin: 0 auto;
	display: flex;
}

.main_body div, .main_body textarea{
	margin: 10px;
}
.main_body textarea{
resize: none;
margin:0px;
}

.ctnt {
	display: flex;
	border: black 1px solid;
	height: 400px;
	padding: 0px;
	flex-direction: column;
	justify-content: space-between;
}

.board_ctnt_title, .profile {
	border: black 1px solid;
	display: flex;
	margin-bottom: 30px;
}

.board_ctnt_title div {
	margin: 10px;
}

.menu {
	border: 1px solid black;
	padding: 10px;
	width: 20%;
}

.board_body {
	width: 75%;
}

.profile {
	justify-content: space-between;
}
.err{
	color:red;
	font-weight: bold;
}
.title{
font-weight: bold;
	font-size: 2em;
}
</style>
<body>
	<div class="main_body">
		<div class="menu">
			<div>
				<B>게시판 메뉴</B>
			</div>
			<hr>
			<div>
				게시글 번호 :${data.i_board}</div>
			<div>
				<a href="List">리스트보기</a>
			</div>
			<c:if test="${ data.i_user == login_user.i_user }"> 
			<div>
			<form action="Del" id="delfrm" method="post">
			<input type="hidden" name="id" value="${data.i_board}">
			<a href="#" onclick="submitDel()">삭제</a>
			</form>
			</div>
			<div>
				<a href="Regmod?id=${data.i_board}">수정</a>
			</div>
			</c:if>
		</div>
		<div class="board_body">
			<div class="board_ctnt_title">
				<div class="title">
					제목 :${data.title}
					<span class="err">${err}</span></div>
			</div>
			<div class="profile">
				<div>
					작성일 : ${data.r_dt}</div>
					<div>
					조회수 : ${data.hits}</div>
				<div>
					작성자 : ${data.user_nm}</div>
			</div>
			<div class="ctnt">
				<textarea rows="50" readonly="readonly">${data.ctnt}</textarea>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function submitDel(){
		event.preventDefault();
		var result = confirm('삭제하시겠습니까?');
		if(result){
			delfrm.submit();
		}
	}</script>
</body>
</html>