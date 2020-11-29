<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>TY SYSTEM - admin</title>
        <link href="../resources/css/styles.css" rel="stylesheet" />
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
    </head>

<body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
            	<br><br><br>
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                    	<div class="form-group d-flex align-items-center justify-content-between mt-3 mb-2">
                                    		<img src="/resources/images/tylogo1.png" width="50" height="50">
                                    		<h3 class="text-center font-weight-light my-0">관리자</h3>
                                    	</div>

                                    </div>
                                    <div class="card-body">
                                        <form id='authForm' method="post">
                                        	<div> <label class="small mb-1" for="description" style="color:red"> <b> 관리자 잠금 해제를 위해 1,2차 비밀번호를 입력해 주세요.</b></label></div>
                                            <div>
                                                <label class="small mb-1" for="admin_auth1">1차 비밀번호</label>
                                                <input class="form-control py-4" name="admin_auth1" id="admin_auth1" type="password" placeholder="1차 비밀번호" />
                                            </div>
                                            <div>
                                                <label class="small mb-1" for="admin_auth2">2차 비밀번호</label>
                                                <input class="form-control py-4" name ="admin_auth2" id="admin_auth2" type="password" placeholder="2차 비밀번호" />
                                            </div>
                                            
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
												<button type="button" id="backBtn" name="backBtn" class="btn btn-outline-primary">홈으로</button>
                                                <button type="button" id="authBtn" name="authBtn" class="btn btn-primary">해제하기</button>
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
        
        <!-- 관리자 비밀번호 검사 -->
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#backBtn").on("click", function() {
				location.href = "/login";
			});

			$("#authBtn").on("click", function() {
				if ($("#admin_auth1").val() == "") {
					alert("1차 비밀번호를 입력해주세요.");
					$("#admin_auth1").focus();
					return false;
				}
				if ($("#admin_auth2").val() == "") {
					alert("2차 비밀번호를 입력해주세요.");
					$("#admin_auth2").focus();
					return false;
				}
				$.ajax({
					type : "POST",
	                url: '/manage/adminForAuth',
	                data: {
	                	'admin_auth1' : $('#admin_auth1').val(),
	                	'admin_auth2' : $('#admin_auth2').val()
	                	},
	                error: function(request, status, data){
	                	alert("관리자 페이지 로딩 실패. 리로딩");
	                	location.href="/manage/adminForAuth";
	                },
	                success: function(response, status, data) {
	                    if(response.result == "001")
	                        alert("1차 비밀번호가 올바르지 않습니다.");
	                    else if(response.result == "002")
	                    	alert("2차 비밀번호가 올바르지 않습니다.");
	                    else if(response.result == "000"){
	                    	alert("관리자 잠금을 해제하였습니다.");
	                    	alert("비밀번호는 아이디와 동일하게 설정하였습니다. 본인 수정을 통해 비밀번호를 변경해 주세요.");
	                    	location.href="/login";
	                    }
	                    else{
	                    	alert("알 수 없는 오류.\n 에러 코드: " + response.result);
	                    	location.href="/manage/adminForAuth";
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