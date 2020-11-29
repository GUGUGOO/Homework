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
		
		<!-- 헤더 끝 -->
			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h4 class="m-0 ">회원 관리</h4>
				</div>
				<div class="card-body">
					<!-- 회원 등록으로 바꿀 예정 -->
					<div align="right">
						<c:if test="${user.getEnroll_category() != '03'}" >
							<a href="/manage/register"><button class="btn btn-dark btn-sm">회원등록 </button></a>
						</c:if>
					</div><br/>
					<!-- 검색 구간 -->
					<div class="input-group">
						<div class="form-group col-sm-2">
							<select class="form-control" name="searchdpType">
								<option value="n">----------</option>
								<c:forEach items="${dplist }" var="dplist">
									<option value="${dplist.department_id }">${dplist.department_name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-sm-2">
							<select class="form-control" name="searchType">
								<option value="n"<c:out value="${spag.searchType == null ? 'selected' : '' }"/>>----------</option>
								<option value="d"<c:out value="${spag.searchType eq 'd' ? 'selected' : '' }"/>>부서명</option>
								<option value="e"<c:out value="${spag.searchType eq 'e' ? 'selected' : '' }"/>>회원명</option>
							</select>
						</div>
						<div class="form-group col-sm-6">
							<input type="text" class="form-control" name="keyword" id="keywordInput" value="${spag.keyword }"/>
						</div>
						<div class="form-group col-sm-2">
							<span class="input-group-btn">
								<button type="button" id="searchBtn" class="btn btn-outline-primary btn-flat">
								<i class="fa fa-search"></i>회원 검색</button>
							</span>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>아이디</th>
									<th>이름</th>
									<th>부서</th>
									<th>전화번호</th>
									<th>이메일</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${list}" var="list">
									
									<tr>
										<td><a href="/manage/view?enroll_id=${list.getEnroll_id()}"> ${list.getEnroll_id()} </a></td>
										<td>${list.getEnroll_name()}</td>
										<td>${list.department_name}</td>
										<td>${list.getEnroll_phone()}</td>
										<td>${list.getEnroll_mail()}</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
						
						<!-- 페이징 시작 -->
						<div class="row">
						<div class="col-sm-12 col-md-5"> </div>
						<div class="col-sm-12 col-md-7">
						<div class="box-footer">
							<div class='text-center'>
							  <ul class="pagination" id="pageMove">
							  	<!-- 이전 -->
							    <c:if test="${pageMaker.prev}">
							    	<li class="paginate_button page-item previous" id="dataTable_previous">
										<a aria-controls="dataTable" class="page-link" 
										href="main${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
									</li>
							    </c:if> 
								<!-- 중간 숫자 -->
							    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							    	<li class="paginate_button page-item">
							    		<a class="page-link" aria-controls="dataTable"
							    		 href="main${pageMaker.makeSearch(idx)}">${idx}</a>
							    	</li>
							    </c:forEach>
								<!-- 다음 -->
							    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							    	<li class="paginate_button page-item active">
							    		<a class="page-link" aria-controls="dataTable"
							    		 href="main${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
							    	</li>
							    </c:if> 
							  </ul>
							</div>
						</div>
						</div>
						</div>
						<!-- 페이징 끝 -->
						
					</div>
				</div>
			</div>
		
		<!-- 푸터 복붙 -->
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

	<script type="text/javascript">
		document.getElementById("searchBtn").onclick = function() {
			
			var searchType = document.getElementsByName("searchType")[0].value;
			var keyword = document.getElementsByName("keyword")[0].value;
			
			if(searchType == 'n'){
				alert("검색 타입을 설정해주세요.");
				return false;
			}
			else{
				alert("검색을 시작합니다.");
			}
			location.href = "./main?page=1" + "&searchType=" + searchType + "&keyword=" + keyword;
		};
	</script>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../resources/js/scripts.js"></script>
</body>
</html>