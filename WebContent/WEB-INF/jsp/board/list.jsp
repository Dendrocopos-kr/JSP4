<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.koreait.pjt.vo.BoardVO"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>게시판</title>
</head>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

*:focus {
	outline: none;
}

a button {
	color: #58585a;
	text-decoration: none;
}

#user_welcome {
	text-align: right;
	margin: 20px;
}

#logout {
	background-color: #f5d1ca;
	text-align: center;
	padding: 5px;
	color: #58585a;
	border: none;
	border-radius: 10px;
	font-weight: bold;
}

#write {
	width: 100px;
	background-color: #f5d1ca;
	text-align: center;
	border: none;
	padding: 8px;
	color: #58585a;
	border-radius: 10px;
	font-weight: bold;
}

table {
	border: black 1px solid;
	margin: 10px auto;
	border-collapse: collapse;
}

th, td {
	padding: 1em;
	text-align: center;
	color: black;
	border: none;
}

tr:nth-child(odd) {
	background: #F2DFDF;
}

tr:nth-child(even) {
	background: #F2BBBB;
}

th {
	background: #F19393;
	color: white;
	border-bottom: 1px black solid;
}

td:nth-child(2) {
	text-align: left;
}

.itemRow:hover {
	background: #F2F2F2;
	cursor: pointer;
	color: black;
}

.container {
	text-align: center; margin : 30px auto;
	width: 800px;
	margin: 30px auto;
}

.container h1 {
	text-align: center;
}

#usr-color {
	color: #ef9173;
	font-weight: bold;
}
.write_div{
text-align: right;
margin: 20px;
}
</style>
<body>
	<div class="container">
		<div>
			<h1>게시판 리스트</h1>
		</div>
		<div id="user_welcome">
			✔【<span id="user-color">${login_user.user_nm}</span>】님 환영합니다. <a href="/Logout">
				<button id="logout">로그아웃</button>
			</a>
		</div>
		<div class="write_div">
			<a href="Regmod">
				<button id="write">글쓰기</button>
			</a>
		</div>
		<table class="table">
			<tr>
				<th style="width: 10%;">번호</th>
				<th style="width: 35%;">제목</th>
				<th style="width: 15%;">조회수</th>
				<th style="width: 15%;">작성자</th>
				<th style="width: 15%;">작성일</th>
			</tr>
			<c:if test="${!empty data}">
				<c:forEach items="${data}" var="item">
					<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
						<td>${item.i_board }</td>
						<td>${item.title }</td>
						<td>${item.hits }</td>
						<td>${item.user_nm }</td>
						<td>${item.r_dt }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty data}">
				<tr>
					<td colspan=5 style="text-align: center;">작성글이 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>
	<script type="text/javascript">
	function moveToDetail(PK) {
		console.log(PK);
		location.href = 'Detail?id='+PK;
	}
	</script>
</body>
</html>