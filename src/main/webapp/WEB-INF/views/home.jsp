<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>TY SYSTEM</title>
<link href="../resources/css/styles.css" rel="stylesheet" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
<!-- header, 왼쪽 side, 메인 나누기용 -->
<style>
#jb-container {
	width: max-width;
	margin: 0px auto;
	padding: 20px;
	border: 1px solid #bcbcbc;
}

#jb-header {
	padding: 20px;
	margin-bottom: 20px;
	border: 1px solid #bcbcbc;
}

#jb-content {
	width: 1220px;
	padding: 20px;
	margin-bottom: 20px;
	float: right;
	border: 1px solid #bcbcbc;
}

#jb-sidebar {
	width: 220px;
	padding: 60px;
	margin-bottom: 20px;
	float: left;
	border: 1px solid #bcbcbc;
}

#jb-footer {
	clear: both;
	padding: 20px;
	border: 1px solid #bcbcbc;
}
</style>
</head>
<body>
	<div id="jb-container">
		<div id="jb-header" class="row">
			<div class="userInfo col-md-9">
				<!-- 유저 이름 -->
				<span>${user.getEnroll_name()} 님 </span>
				<a href="/logout">
					<button class="btn btn-dark btn-sm">로그아웃</button>
				</a>
				<p>
					<a href="/notice/list">공지사항</a>
					을 확인해 주세요
				</p>
			</div>
			<div class="userInfo2 col-md-3 col-md-offset-3">
				<!-- 유저 권한 -->
				<c:if test="${user.getEnroll_category()=='01'}">
					<p>총괄 관리자</p>
				</c:if>
				<c:if test="${user.getEnroll_category() == '02'}">
					<p>부서 관리자</p>
				</c:if>
				<c:if test="${user.getEnroll_category() == '03'}">
					<p>일반 사용자</p>
				</c:if>

				<!-- 유저 부서 -->
				<span>${userDename.department_name} 부서</span>
				
			</div>
		</div>
		<div id="jb-content">
			<h3>회원 관리</h3>
			<p>안녕하세요</p>

		</div>
		<div id="jb-sidebar">
			<!-- 버튼 추가 -->
			<a href="/notice/list">
			<button class="btn btn-outline-primary">공지사항</button>
			</a>
			<a href="/board/list">
			<button class="btn btn-outline-primary">게시판</button>
			</a>
			<a href="/manage/main">
				<button class="btn btn-outline-primary">회원관리</button>
			</a>
		</div>
		<div id="jb-footer">
			<p>Copyright (주)TY SYS</p>
			<p>Call: 010-0000-0000</p>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../resources/js/scripts.js"></script>
</body>
</html>