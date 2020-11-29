<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 수정</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

	<c:if test="${empty my}">
		<span>${user.getEnroll_name() }님  </span>
		<a href="http://localhost:8080/logout"><button>로그아웃 </button></a>
		<br/>
		<span>${user.getEnroll_depart_id() }부서</span>	
	</c:if>
		
	<h2>회원수정</h2>
	
	<c:if test="${ !empty my || ( !empty my && my.getEnroll_id() != user.getEnroll_id())}">
		<c:if test="${ user.getEnroll_isActivate() == 'true'}" >
			<button id ="doActive">비활성화하기</button>
			<input id = "isActive" name="isActive" value="0000" hidden="hidden"/>
 		</c:if>
 		<c:if test="${ user.getEnroll_isActivate() == 'false'}" >
			<button id ="doActive">활성화하기</button>
			<input id = "isActive" name="isActive" value="0001" hidden="hidden"/>
 		</c:if>
	</c:if>
 	
 	
	<form id="updateUser" method="post" enctype="multipart/form-data">
        <!-- 회원 사진 -->
        <label for="Enroll_image_file">사진: </label>
		<input type="file" id="Enroll_image_file" name="Enroll_image_file" accept="image/*"/>
		<div class="select_img"><img src="${user.getEnroll_image() }"/></div> <br/>
        <!-- 이름 -->
        	<label for="Enroll_name">이름: </label>
            <input type="text" id="Enroll_name" name="Enroll_name" value="${user.getEnroll_name() }"><br/>			
        <!-- ID -->
	        <label for="Enroll_id">ID: </label>
        	<span>${user.getEnroll_id() }</span><br/>
        	<input type="text" id="Enroll_id" name="Enroll_id" value="${user.getEnroll_id() }" hidden="hidden"><br/>			 
        <!-- password -->
	        <label for="Enroll_password">password: </label>
        	<input type="password" id="Enroll_password" name="Enroll_password"><br/>
    	<!-- 생년월일 -->
    		<label for="Enroll_birth">생년월일: </label>
    		<span>${user.getEnroll_birth()}</span><br/>
    	<!-- 핸드폰 번호 -->
    		<label for="Enroll_phone">핸드폰 번호: </label>
    		<input type="text" id="Enroll_phone" name="Enroll_phone" value="${user.getEnroll_phone() }"><br/>
    	<!-- 이메일 -->
	    	<label for="Enroll_mail">이메일: </label>
    		<input type="text" id="Enroll_mail" name="Enroll_mail" value="${user.getEnroll_mail() }"><br/>
    	<!-- 부서 부서관리자는 건들 수 없음 -->
    	
    	<c:if test="${my.getEnroll_category() != '03'}">
    		<select id="Enroll_depart_id" name='Enroll_depart_id'>
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
    	<!-- 사용자구분 최대 부서 관리자까지만 가능-->	   
    	<c:if test="${my.getEnroll_category() != '03'}"> 	
	    	<select id="Enroll_category" name='Enroll_category'>
				<c:if test="${my.getEnroll_category()=='01'}" >
			  		<option value='01'>총괄 관리자</option>
				</c:if>
				<c:if test="${my.getEnroll_category()=='01' || my.getEnroll_category()=='02'}" >
			  		<option value='02'>부서관리자</option>
				</c:if>
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
			    	$(".select_img img").attr("src", "").width(0).height(0);
				}
				
				//display image
				if(this.files && this.files[0]){
					var reader = new FileReader;
					reader.onload = function(data){
						$(".select_img img").attr("src", data.target.result).width(300).height(300);
					}
					reader.readAsDataURL(this.files[0]);
				}
			})
		</script>
		
       </form>       
		<button id="deleteFile"> 파일 삭제 </button>
		
       
	   	<button id="submitUser">회원 수정</button>
	   
	   <a href="http://localhost:8080/manage/main"><button>< 돌아가기</button></a>
	
		<script type="text/javascript" src="../resources/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.form.min.js"></script>

	   	<script type="text/javascript">
			$(document).ready(function() {	
				//활성화 비활성화하기
				$("#doActive").on("click", function() {
					console.log($("#doActive").html());
					$.ajax({
						type : "POST",
		                url: '/doActive',
		                data: $("#isActive"),
		                error: function(request, status, data){
		                	alert("실패하였습니다.");
		                },
		                success: function(response, status, data) {
		                	if(response.result == "00")
		                        alert("비활성화 하였습니다.") ;
		                    else if(response.result == "01")
		                    	alert("활성화하였습니다.");
		                    else
		                    	alert("오류발생.");
		                  	location.href="/manage/main"
		                }
		            })
				});
				
				//수정 시 유효성 검사
				$("#submitUser").on("click", function() {
					var regExp =  /^[가-힣a-zA-Z]{4,16}$/;
					if (!regExp.test($("#Enroll_name").val())) {
						alert("이름을 정확히 입력해주세요.");
						$("#Enroll_name").focus();
						return false;
					}
					regExp =  /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;
					if (!regExp.test($("#Enroll_password").val())) {
						alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
						$("#Enroll_password").focus();
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
			                url: '/updateNoFile',
			                data: $("#updateUser").serialize(),
			                error: function(request, status, data){
			                	alert("등록 실패, 홈페이지 리로딩");
			                	//location.href="/login";
			                },
			                success: function(response, status, data) {
			                	if(response.result == "000")
			                        alert("이름을 확인해주세요.") ;
			                    else if(response.result == "0000")
			                    	alert("파일 확장자나 업로드에 문제가 있습니다.");
			                    else if(response.result == "002")
			                    	alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
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
						$("#updateUser").ajaxForm({
			                url: '/update',
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
			                    else if(response.result == "002")
			                    	alert("비밀번호에 8자 이상 16자 이하 영문자와 숫자로 입력해주세요.");
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
			                    location.href="/update";
			                }
			            }).submit();
				    }
				   
				    
				});	
				
				$("#deleteFile").on("click", function() {
			    	document.getElementById("Enroll_image_file").value = "";
			    	$(".select_img img").attr("src", "").width(0).height(0);
			    });
			});
	    </script>
</body>
</html>