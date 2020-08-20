<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.koreait.pjt.vo.BoardVO"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>게시판</title>
</head>
<style>
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

div {
	text-align: center;
}

.itemRow:hover td {
	background: #F2F2F2;
	cursor: pointer;
	color: black;
}

#user_welcome {
	text-align: right;
}
.container{
	margin: 30px auto;
	width: 800px;
}
</style>
<body>
	<div>
		<h1>게시판 리스트</h1>
	</div>
	<div class="container">
	<div id="user_welcome">✔【${login_user.user_nm}】님 환영합니다.</div>
	<table>
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
	<div>
		<a href="Regmod"><button>글쓰기</button></a>
	</div>
	</div>
	<script type="text/javascript">
	function moveToDetail(PK) {
		console.log(PK);
		location.href = 'Detail?id='+PK;
	}
	</script>
</body>
</html>