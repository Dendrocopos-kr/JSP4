<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title}</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<style>
.main_body {
	width: 800px;
	margin: 0 auto;
	display: flex;
}

.main_body textarea {
	resize: none;
	border: none;
	border-radius: 10px;
	background: #FDF2F0;
}

.like {
	background: #EACACB;
	text-align: center;
	border: none;
}

.menu_btn {
	width: 95%;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	font-weight: bold;
	margin-bottom: 20px;
}

.ctnt {
	display: flex;
	height: 400px;
	padding: 0px;
	flex-direction: column;
	justify-content: space-between;
	border-radius: 10px;
	border: #DEB3CF 1px solid;
	margin: 10px;
}

.menu {
	padding: 10px;
	width: 20%;
	text-align: center;
	background: #FADCDA;
	border-radius: 10px;
	margin: 10px;
}

.board_body {
	width: 75%;
}

.err {
	color: red;
	font-weight: bold;
}

.board_ctnt_title, .profile, .board_profile {
	justify-content: space-between;
	background: #EACACB;
	display: flex;
	margin-bottom: 30px;
	border-radius: 10px;
	padding: 10px;
	margin: 10px;
}

.menu_info {
	padding: 30px;
}

hr {
	border: white 2px solid;
	margin-bottom: 30px;
}

.material-icons {
	font-family: 'Material Icons';
	font-weight: normal;
	font-style: normal;
	font-size: 24px; /* Preferred icon size */
	display: inline-block;
	line-height: 1;
	text-transform: none;
	letter-spacing: normal;
	word-wrap: normal;
	white-space: nowrap;
	direction: ltr;
	color: red;
	/* Support for all WebKit browsers. */
	-webkit-font-smoothing: antialiased;
	/* Support for Safari and Chrome. */
	text-rendering: optimizeLegibility;
	/* Support for Firefox. */
	-moz-osx-font-smoothing: grayscale;
	/* Support for IE. */
	font-feature-settings: 'liga';
}
.like:hover{
cursor: pointer;
}
</style>
<body>
	<div class="main_body">
		<div class="menu">
			<div class="menu_info">
				<B>게시판 메뉴</B>
			</div>
			<hr>
			<!-- 
			<div>
				게시글 번호 :${data.i_board}
				</div>
			 -->
			<div>
				<a href="List">
					<button class="menu_btn">리스트보기</button>
				</a>
			</div>
			<c:if test="${ data.i_user == login_user.i_user }">
				<div>
					<form action="Del" id="delfrm" method="post">
						<input type="hidden" name="id" value="${data.i_board}"> <a href="#" onclick="submitDel()"><button class="menu_btn">삭제</button></a>
					</form>
				</div>
				<div>
					<a href="Regmod?id=${data.i_board}"><button class="menu_btn">수정</button></a>
				</div>
			</c:if>
		</div>
		<div class="board_body">
			<div class="board_ctnt_title">
				<div class="title">
					제목 :${data.title} <span class="err">${err}</span>
				</div>
			</div>
			<div class="board_profile">
				<div>작성일 : ${data.r_dt}</div>
				<c:if test="${data.like == 1}">
					<!-- <form action="Like" id="like" method="post"> -->
					<form action="" id="like" method="post">
						<input type="hidden" name="like" value="0"><input type="hidden" name="id" value="${data.i_board}"><a href="?id=${data.i_board}&like=0"><button class="like">
								<span class="material-icons"> favorite </span>
							</button></a>
					</form>
				</c:if>
				<c:if test="${data.like == 0}">
					<!--<form action="Like" id="like" method="post"> -->
					<form action="" id="like" method="post">
						<input type="hidden" name="like" value="1"><input type="hidden" name="id" value="${data.i_board}"><a href="?id=${data.i_board}&like=1"><button class="like">
								<span class="material-icons"> favorite_border </span>
							</button></a>
					</form>
				</c:if>
				<div>조회수 : ${data.hits}</div>
			</div>
			<div class="ctnt">
				<textarea rows="50" readonly="readonly">${data.ctnt}</textarea>
			</div>
			<div class="profile">
				<div>작성자 : ${data.user_nm}</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function submitDel() {
			event.preventDefault();
			var result = confirm('삭제하시겠습니까?');
			if (result) {
				delfrm.submit();
			}
		}
	</script>
</body>
</html>