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
        <title>TY SYSTEM</title>
        <link href="../resources/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
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
                                    		<h3 class="text-center font-weight-light my-0">로그인</h3>
                                    	</div>

                                    </div>
                                    <div class="card-body">
                                        <form id='loginForm' method="post">
                                            <div class="form-group">
                                                <label class="small mb-1" for="enroll_id">아이디</label>
                                                <input class="form-control py-4" name="enroll_id" id="enroll_id" type="text" placeholder="아이디를 입력해주세요." />
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" for="enroll_password">비밀번호</label>
                                                <input class="form-control py-4" name ="enroll_password" id="enroll_password" type="password" placeholder="비밀번호를 입력해 주세요." />
                                            </div>
                                            
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="password.html">관리자 아이디 잠금 해제</a>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../resources/js/scripts.js"></script>
    </body>
</html>
