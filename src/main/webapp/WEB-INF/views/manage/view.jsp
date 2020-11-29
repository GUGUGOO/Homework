<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8" />
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
        width: 240px;
        padding: 20px;
        margin-bottom: 10px;
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
			<!-- 유저 정보 노출 -->
        	<div class="userInfo col-md-9">
				<!-- 유저 이름 -->
				<span>${user.getEnroll_name()} 님  </span>
				<a href="../update"><button class="btn btn-primary btn-sm">회원 본인 수정</button></a>
				<a href="/logout"><button class="btn btn-dark btn-sm">로그아웃 </button></a>
				<p> <a href="/notice/list">공지사항</a>을 확인해 주세요</p>
			</div>
			<div class="userInfo2 col-md-3 col-md-offset-3">
				<!-- 유저 권한 -->
				<c:if test="${user.getEnroll_category()=='01'}" >
			  		<p> 총괄 관리자 </p>
				</c:if>
				<c:if test="${user.getEnroll_category() == '02'}" >
			  		<p> 부서 관리자 </p>
				</c:if>
				<c:if test="${user.getEnroll_category() == '03'}" >
			  		<p> 일반 사용자 </p>
				</c:if>
			
				<!-- 유저 부서 -->
				<span>${userDename.department_name} 부서</span>
			</div>
			<!-- 유저 정보 노출 끝 -->
      	</div>
      	<div id="jb-content">
        <!-- Begin Page Content -->
<div class="container-fluid">

	<!-- 게시글  -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h3 class="m-2 font-weight-bold text-dark">회원 상세 조회 </h3>
		</div>
		<div class="card-body">
			<div align="right" class="mb-4">
				<a href="/manage/main?page=1" class="btn btn-dark btn-icon-split">
				<span
					class="icon text-gray-600"> <i class="fas fa-arrow-right"></i>
				</span> <span class="text">목록</span>
				</a>
			</div>
			<div class="row">
			<div class="col md-12">
			<table class="table table-bordered">
				<tr>
					<th>사진</th>
					<td><img src="${view.enroll_image }" width="150px" height="200px"/></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${view.enroll_id}</td>	
				</tr>
				<tr>
					<th>이름</th>
					<td>${view.enroll_name}</td>
				</tr>	
				<tr>
					<th>생년월일</th>
					<td>${view.enroll_birth}</td>
				</tr>	
				<tr>
					<th>부서</th>
					<td>${view.department_name}</td>
				</tr>
				<tr>
					<th>권한</th>
					
						<c:if test="${view.enroll_category == '01' }">
							<td>총괄 관리자</td>
						</c:if>
						<c:if test="${view.enroll_category == '02' }">
							<td>${view.department_name} 부서 관리자 </td>
						</c:if>
						<c:if test="${view.enroll_category == '03' }">
							<td> 일반 관리자 </td>
						</c:if>

				</tr>
				<tr>
					<th>이메일</th>
					<td>${view.enroll_mail}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${view.enroll_phone}</td>
				</tr>
				<tr>
				</tr>
			</table>
			</div>

			</div>
			<!-- 관리자 별 권한 부여 -->
			<!-- 같은 부서 관리자 수정,삭제 가능 / 총괄 관리자 모두 가능 / 일반 사용자 모두 불가능 -->
			<!-- category 01: 총괄	02: 부서	03: 일반 -->
			
			<div align="right" class="mb-2">
				<!-- 일반 사용자가 아니면서 -->
				<c:if test="${user.getEnroll_category()!='03'}" >
					<!-- 부서 관리자 인데? 부서관리자의 부서 id가 현재 조회되는 부서 id와 동일할 때 -->
					<c:if test="${ (user.getEnroll_category()== '02' && user.getEnroll_depart_id()== view.getEnroll_depart_id()) || user.getEnroll_category() == '01'}">
						<button type="submit" class="btn btn-primary"
						onclick="location.href ='/update?userId=${view.enroll_id}'">회원 수정</button>
						<button type="submit" class="btn btn-outline-secondary"
						onclick="del_button()">회원 삭제</button>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
	
	

<!-- 푸터 복붙 -->
</div>
	
	<!-- 삭제버튼 클릭시 경고 창 -->
	<script type="text/javascript">
		function del_button() {
			if (confirm("삭제할까요?") == true) { //확인
				location.href ='/manage/delete?enroll_id=${view.enroll_id}';
			} else { //취소
				return;
			}
		}
	</script>
    </div>
    <!-- 콘텐트 끝, 사이드 바와 푸터 -->
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