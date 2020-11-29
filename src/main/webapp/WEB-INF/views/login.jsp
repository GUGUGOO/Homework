<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<br>
			<br>
			<br>
			<main>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-5">
						<div class="card shadow-lg border-0 rounded-lg mt-5">
							<div class="card-header">
								<div class="form-group d-flex align-items-center justify-content-between mt-3 mb-2">
									<img src="/resources/images/tylogo1.png" width="50" height="50">
									<h3 class="text-center font-weight-light my-0">로그인</h3>
								</div>
							</div>
							<div class="card-body">
								<form id='loginForm' method="post">
									<div>
										<label class="small mb-1" for="enroll_id">아이디</label> <input class="form-control py-4" name="enroll_id" id="enroll_id" type="text" placeholder="아이디를 입력해주세요." />
									</div>
									<div>
										<label class="small mb-1" for="enroll_password">비밀번호</label> <input class="form-control py-4" name="enroll_password" id="enroll_password" type="password" placeholder="비밀번호를 입력해 주세요." />
									</div>

									<div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
										<a class="small" href="/manage/adminForAuth">관리자 아이디 잠금 해제</a>
										<button type="button" id="loginBtn" name="loginBtn" class="btn btn-primary">로그인</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			</main>
		</div>
	</div>
	<!-- 로그인 공백 검사 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginBtn").on("click", function() {
				if ($("#enroll_id").val() == "") {
					alert("아이디를 입력해주세요.");
					$("#enroll_id").focus();
					return false;
				}
				if ($("#enroll_password").val() == "") {
					alert("비밀번호를 입력해주세요.");
					$("#enroll_password").focus();
					return false;
				}
				$.ajax({
					type : "POST",
	                url: '/login',
	                data: $("#loginForm").serialize(),
	                error: function(request, status, data){
	                	alert("로그인 실패, 홈페이지 리로딩");
	                	location.href="${pageContext.request.contextPath}/login";
	                },
	                success: function(response, status, data) {
	                    if(response.result == "001")
	                        alert("등록되지 않은 이용자 입니다.") ;
	                    else if(response.result == "002")
	                    	alert("비밀번호가 틀렸습니다.");
	                    else if(response.result == "003")
	                    	alert("비밀번호 틀린 횟수가 5회 이상 초과되어 계정이 비활성화되었습니다");
	                    else if(response.result == "103")
	                    	alert("총괄 관리자 아이디가 비활성화 되었습니다. 아래 링크를 통해 잠금을 해제해주세요");
	                    	 else{
	                    	alert("로그인 성공");
	                    	location.href="${pageContext.request.contextPath}/home";
	                    }
	                }
				})
				
			});
		})
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../resources/js/scripts.js"></script>
</body>
</html>