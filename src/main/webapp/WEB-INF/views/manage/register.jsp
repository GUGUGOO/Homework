<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<title>회원등록</title>
		<link href="../resources/css/styles.css" rel="stylesheet" />
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
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
      
      .btn-space {
       margin-right: 10px;
	  }
	</style>
	
	</head>
	
	<body>
		<div id="jb-container">
			<div id="jb-header" class="row">
				<div class="userInfo col-md-9">
		<!-- image text
		<img src='${user.getEnroll_image()}' />
		-->
					<span>${user.getEnroll_name() }님  </span>
					<a href="../update"><button class="btn btn-primary btn-sm">회원 본인 수정</button></a>
					<a href="../logout"><button class="btn btn-dark btn-sm">로그아웃 </button></a>
					<p> <a href="../notice">공지사항</a>을 확인해 주세요</p>
				</div>
				<div class="userInfo2 col-md-3 col-md-offset-3">
					<c:if test="${user.getEnroll_category()=='01'}" >
			  			<p> 총괄 관리자 </p>
					</c:if>
					<c:if test="${user.getEnroll_category() == '02'}" >
			  			<p> 부서 관리자 </p>
					</c:if>
					<c:if test="${user.getEnroll_category() == '03'}" >
			  			<p> 일반 사용자 </p>
					</c:if>
		
		
				
    				
					<c:if test="${ user.getEnroll_depart_id()== '00'}" >
				  		<span>경영지원 부서</span>
					</c:if>
					<c:if test="${user.getEnroll_depart_id() == '01' }" >
				  		<span>글로벌 영업 부서</span>
					</c:if>
					<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '02' }" >
				  		<span>솔루션 사업 부서</span>
					</c:if>
					<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '03' }" >
				  		<span>ICT 사업 부서</span>
					</c:if>
					<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '04' }" >
				  		<span>기술연구소 부서</span>
					</c:if>

				</div>
			</div>
			<div id="jb-content">
			<!-- Begin Page Content -->
			<div class="container-fluid">	
				
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h4 class="m-0 ">회원 등록</h4>
					</div>
				
					<div class="card-body">
						
						<form class="form-group col-md-8" id="registerUser" method="post" enctype="multipart/form-data">
					        <!-- 회원 사진 -->
					        <div class="input-group mb-3">
  								<div class="input-group-prepend">
    								<span class="input-group-text">사진 업로드</span>
  								</div>
  								<div class="custom-file">
    								<input type="file"  id="Enroll_image_file" name="Enroll_image_file" accept="image/*" class="custom-file-input">
    								<label id="fileLable" class="custom-file-label" for="Enroll_image_file">파일을 선택해주세요.</label>
  								</div>
  								<div class="input-group-append">
    								<button class="btn btn-outline-secondary" id="deleteFile" type="button">삭제</button>
  								</div>
							</div>
							<div id="select_img"><img src="" /></div> <br/>
					        <!-- 이름 -->
					        <label for="Enroll_name">이름: </label>
					        <input class="form-control" type="text" id="Enroll_name" name="Enroll_name" placeholder="이름"><br/>			
					        <!-- ID -->
					    
						      	<label for="Enroll_id">ID: </label>
						    <div class="input-group">
						        <input class="form-control" type="text" id="Enroll_id" name="Enroll_id" placeholder="아이디">
					            <button class="btn btn-outline-primary btn-flat" id="checkId" type="button">중복확인</button><br/>
					        </div>    
					        <!-- password -->
						        <label for="Enroll_password">password: </label>
					        	<input class="form-control" type="password" id="Enroll_password" name="Enroll_password" placeholder="비밀번호"><br/>
					    	<!-- 생년월일 -->
					    		<label for="Enroll_birth">생년월일: </label>
					    		<input class="form-control" type="text" id="Enroll_birth" name="Enroll_birth" placeholder="ex) 1993-11-13"><br/>
					    	<!-- 핸드폰 번호 -->
					    		<label for="Enroll_phone">핸드폰 번호: </label>
					    		<input class="form-control" type="text" id="Enroll_phone" name="Enroll_phone" placeholder="ex) 010-3234-2451"><br/>
					    	<!-- 이메일 -->
						    	<label for="Enroll_mail">이메일: </label>
					    		<input class="form-control" type="text" id="Enroll_mail" name="Enroll_mail" placeholder="이메일"><br/>
					    	<!-- 부서 부서관리자는 건들 수 없음 -->
					    	<label for="Enroll_depart_id">소속 부서: </label>
					    	<c:if test="${user.getEnroll_category() != '03'}">
					    		<select class="form-control" id="Enroll_depart_id" name='Enroll_depart_id'>
									<c:if test="${ user.getEnroll_category()=='01' || user.getEnroll_depart_id()== '00'}" >
								  		<option value='00' selected>경영지원부</option>
									</c:if>
									<c:if test="${user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '01' }" >
								  		<option value='01'>글로벌영업부</option>
									</c:if>
									<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '02' }" >
								  		<option value='02'>솔루션사업부</option>
									</c:if>
									<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '03' }" >
								  		<option value='03'>ICT사업부</option>
									</c:if>
									<c:if test="${ user.getEnroll_category() == '01' || user.getEnroll_depart_id() == '04' }" >
								  		<option value='04'>기술연구소</option>
									</c:if>
								</select>
							</c:if>
							<br/>
					    	<!-- 사용자구분 최대 부서 관리자까지만 가능-->	
					    	<label for="Enroll_category">권한: </label>   
					    	<c:if test="${user.getEnroll_category() != '03'}"> 	
						    	<select class="form-control" id="Enroll_category" name='Enroll_category'>
									<c:if test="${user.getEnroll_category()=='01'}" >
								  		<option value='01'>총괄 관리자</option>
									</c:if>
								  		<option value='02'>부서관리자</option>
									<option value='03' selected>일반 사용자</option>
								</select>
							</c:if>
							<br/>
							
							<script>
								$("#Enroll_image_file").change(function(){
									var inputFile = document.getElementById('Enroll_image_file');
									var pathpoint = inputFile.value.lastIndexOf('.');
					
									var filepoint = inputFile.value.substring(pathpoint+1,inputFile.length);
					
									var filetype = filepoint.toLowerCase();
					
									if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {
										// 정상적인 이미지 확장자 파일인 경우
									} else {
										alert('Only image file can be uploaded!');
										inputFile.value = "";
								    	$("#select_img img").attr("src", "").width(0).height(0);
								    	filename = "파일을 선택해주세요.";
										$("#fileLable").text(filename);
									}
									
									//display image
									if(this.files && this.files[0]){
										var reader = new FileReader;
										reader.onload = function(data){
											$("#select_img img").attr("src", data.target.result).width(150).height(200);
											var filename = $("#Enroll_image_file").val().split('/').pop().split('\\').pop();
											console.log(filename);
											if(filename == "")
												filename = "파일을 선택해주세요.";
											$("#fileLable").text(filename);
										}
										reader.readAsDataURL(this.files[0]);
									}
								})
							</script>
							
						</form>       
						
						<c:if test="${user.getEnroll_category() != '03'}">
			       		<div class="form-row float-right">
				   			<button class="btn btn-dark btn-space" id="submitUser">회원 등록</button>
				   			<a href="/manage/main"><button class="btn btn-dark btn-space" > 돌아가기</button></a>
				   		</div>
				   		</c:if>
				</div>
				
			</div>
			
				
			</div>	
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
		<script type="text/javascript" src="../resources/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.form.min.js"></script>

	   	<script type="text/javascript">
			$(document).ready(function() {
				$("#checkId").on("click", function() {
					if ($("#Enroll_id").val() == "") {
						alert("아이디를 입력해주세요.");
						$("#Enroll_id").focus();
						return false;
					}
					var regExp =  /^[A-Za-z0-9]{4,16}$/;
					var id = document.getElementById("Enroll_id").value;
					if(!regExp.test(id)){
						alert("4자리 이상 16자리 이하 영대소문자와 숫자만 포함해주세요.");
						return false;
					}
					$.ajax({
						type : "POST",
		                url: '/manage/checkId',
		                data: $("#Enroll_id"),
		                error: function(request, status, data){
		                	alert("로그인 실패, 홈페이지 리로딩");
		                	location.href="/login";
		                },
		                success: function(response, status, data) {
		                    if(response.result == "001")
		                        alert("사용할 수 있는 아이디입니다.") ;
		                    else if(response.result == "002")
		                    	alert("이미 등록된 아이디입니다.");
		                    else
		                    	alert("시스템 오류.");		
		                }
		            })
				});
				
				$("#submitUser").on("click", function() {
					var regExp =  /^[가-힣a-zA-Z]{2,16}$/;
					if (!regExp.test($("#Enroll_name").val())) {
						alert("이름을 정확히 입력해주세요.");
						$("#Enroll_name").focus();
						return false;
					}
					regExp =  /^[A-Za-z0-9]{4,16}$/;
					if (!regExp.test($("#Enroll_id").val())) {
						alert("아이디에 4자리 이상 16자리 이하 영대소문자와 숫자만 포함해주세요.");
						$("#Enroll_id").focus();
						return false;
					}
					regExp =  /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;
					if (!regExp.test($("#Enroll_password").val())) {
						alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
						$("#Enroll_password").focus();
						return false;
					}
					regExp = /[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
					if (!regExp.test($("#Enroll_birth").val())) {
						alert("생년월일을 정확히 입력해주세요.\n 1993-01-02");
						$("#Enroll_birth").focus();
						return false;
					}
					regExp =  /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
					if (!regExp.test($("#Enroll_phone").val())) {
						alert("휴대폰 번호를 정확히 입력해주세요.\n 010-xxxx-xxxx\n 011-xxx-xxxx\n016-xxxx-xxxx");
						$("#Enroll_phone").focus();
						return false;
					}
					regExp =  /[a-z0-9]{2,}@[a-z0-9-]{2,}.[a-z0-9]{2,}/i;
					if (!regExp.test($("#Enroll_mail").val())) {
						alert("이메일을 영문자와 숫자로 작성해주세요.");
						$("#Enroll_mail").focus();
						return false;
					}
					
					//파일이 없는 경우
					if(!window.File || !window.FileReader || !window.FileList || !window.Blob) {
				          alert('The File APIs are not fully supported in this browser.');
				    }   
			
				    var inputFile = document.getElementById('Enroll_image_file');
				    if (!inputFile || !inputFile.files || !inputFile.files[0]) {
				        //alert("Um, couldn't find the fileinput element.");
						//alert("파일없어");
						$.ajax({
							type : "POST",
			                url: '/manage/registerNoFile',
			                data: $("#registerUser").serialize(),
			                error: function(request, status, data){
			                	alert("등록 실패, 홈페이지 리로딩");
			                	//location.href="/login";
			                },
			                success: function(response, status, data) {
			                	if(response.result == "000")
			                        alert("이름을 확인해주세요.") ;
			                    else if(response.result == "0000")
			                    	alert("파일 확장자나 업로드에 문제가 있습니다.");
			                    else if(response.result == "001")
			                    	alert("아이디에 4자리 이상 10자리 이하 영대소문자와 숫자만 포함해주세요.");
			                    else if(response.result == "0001")
			                    	alert("이미 있는 아이디입니다.");
			                    else if(response.result == "002")
			                    	alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
			                    else if(response.result == "003")
			                    	alert("생년월일을 정확히 입력해주세요.\n 1993-01-02");
			                    else if(response.result == "004")
			                    	alert("휴대폰 번호를 정확히 입력해주세요.\n xxx-xxxx-xxxx");
			                    else if(response.result == "005")
			                    	alert("이메일을 영문자와 숫자로 작성해주세요.");
			                    else if(response.result == "006")
			                    	alert("권한이 없습니다.");
			                    else if(response.result == "007")
			                    	alert("서버에러 발생.");
			                    else
			                    	alert("등록에 성공하였습니다.");
			                    location.href="/manage/main";		
			                }
			            })
				    }else{
					//파일이 있을 경우
						$("#registerUser").ajaxForm({
			                url: '/manage/register',
							enctype: "multipart/form-data",
							type : "POST",
			                error: function(request, status, data){
			                	console.log($("#registerUser").serialize());
			                	alert("등록 실패, 홈페이지 리로딩");
	 		                	//location.href="/manage/register";
			                },
			                success: function(response, status, data) {
			                	console.log($("#registerUser").serialize());
			                    if(response.result == "000")
			                        alert("이름을 확인해주세요.") ;
			                    else if(response.result == "0000")
			                    	alert("파일 업로드에 문제가 있습니다.");
			                    else if(response.result == "001")
			                    	alert("아이디에 4자리 이상 10자리 이하 영대소문자와 숫자만 포함해주세요.");
			                    else if(response.result == "0001")
			                    	alert("이미 있는 아이디입니다.");
			                    else if(response.result == "002")
			                    	alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
			                    else if(response.result == "003")
			                    	alert("생년월일을 정확히 입력해주세요.\n 1993-01-02");
			                    else if(response.result == "004")
			                    	alert("휴대폰 번호를 정확히 입력해주세요.\n xxx-xxxx-xxxx");
			                    else if(response.result == "005")
			                    	alert("이메일을 영문자와 숫자로 작성해주세요.");
			                    else if(response.result == "006")
			                    	alert("권한이 없습니다.");
			                    else if(response.result == "007")
			                    	alert("서버에러 발생.");
			                    else
			                    	alert("등록에 성공하였습니다.");
			                    location.href="/manage/main";
			                }
			            }).submit();
				    }
				   
				    
				});	
				
				$("#deleteFile").on("click", function() {
			    	document.getElementById("Enroll_image_file").value = "";
			    	var filename = "파일을 선택해주세요.";
					$("#fileLable").text(filename);
			    	$("#select_img img").attr("src", "").width(0).height(0);
			    });
			});
	    </script>
	</body>
</html>