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
<script>
   function check() {
      if ($('#search_type').val() == "") {
         alert("검색어를 입력하세요.");
      } else {
         var url = "${pageContext.request.contextPath}/notice/list";
         if ("${idx}" != "")
            url = url + "&page=${idx}";
         url = url + "?deptId=" + $("#dept_select option:selected").val();
         url = url + "&keyWord=" + $('#search_type').val();
         location.href = url;
      }
   }
   function change_dept(data) {
      var url = "${pageContext.request.contextPath}/notice/list";
      url = url + "?deptId=" + data.value;
      location.href = url;
   }
   function create_notice(data) {
      var url = "${pageContext.request.contextPath}/notice/create";
      location.href = url;
   }
</script>
</head>
<body>
   <div id="jb-container">
      <div id="jb-header" class="row">
         <div class="userInfo col-md-9">
            <!-- image text
      <img src='${user.getEnroll_image()}' />
      -->
            <span>${user.getEnroll_name() }님 </span>
            <a href="../update">
               <button class="btn btn-primary btn-sm">회원 본인 수정</button>
            </a>
            <a href="../logout">
               <button class="btn btn-dark btn-sm">로그아웃</button>
            </a>
            <p>
               <a href="../notice">공지사항</a>
               을 확인해 주세요
            </p>
         </div>
         <div class="userInfo2 col-md-3 col-md-offset-3">
            <c:if test="${user.getEnroll_category()=='01'}">
               <p>총괄 관리자</p>
            </c:if>
            <c:if test="${user.getEnroll_category() == '02'}">
               <p>부서 관리자</p>
            </c:if>
            <c:if test="${user.getEnroll_category() == '03'}">
               <p>일반 사용자</p>
            </c:if>

            <c:if test="${ user.getEnroll_depart_id()== '00'}">
               <span>경영지원 부서</span>
            </c:if>
            <c:if test="${user.getEnroll_depart_id() == '01' }">
               <span>글로벌 영업 부서</span>
            </c:if>
            <c:if test="${user.getEnroll_depart_id() == '02' }">
               <span>솔루션 사업 부서</span>
            </c:if>
            <c:if test="${ user.getEnroll_depart_id() == '03' }">
               <span>ICT 사업 부서</span>
            </c:if>
            <c:if test="${ user.getEnroll_depart_id() == '04' }">
               <span>기술연구소 부서</span>
            </c:if>

         </div>
      </div>
      <div id="jb-content">
         <!-- Begin Page Content -->
         <div class="container-fluid">
            <div class="card shadow mb-4">
               <div class="card-header py-3">
                  <h3>공지사항 작성 페이지</h3>
               </div>
               <div class="card-body">
                  <div class="table-responsive">
                     <table class="table table-striped table-hover">
                        <thead>
                           <tr>
                              <th colspan="3" style="background-color: #eeeeee; text-align: center;">글 보기</th>
                           </tr>
                        </thead>
                        <tbody>
                           <tr>
                              <td style="width: 20%">글 제목</td>
                              <td colspan="2">${noticeVO.notice_title}</td>
                           </tr>
                           <tr>
                              <td style="width: 20%">작성자</td>
                              <td colspan="2">${noticeVO.notice_writer}</td>
                           </tr>
                           <tr>
                              <td style="width: 20%">작성일</td>
                              <td colspan="2">${noticeVO.notice_day}</td>
                           </tr>
                           <tr>
                              <td style="width: 20%">내용</td>
                              <td colspan="2">${noticeVO.notice_content}</td>
                           </tr>
                        </tbody>
                     </table>
                     <a href="${pageContext.request.contextPath}/notice/list" class="btn btn-primary">목록</a>
                     <c:if test="${sessionScope.user.getEnroll_name() eq noticeVO.notice_writer or sessionScope.user.getEnroll_category() ne normal}">
                        <a href="${pageContext.request.contextPath}/notice/update?noticeIdx=${noticeVO.notice_idx}" class="btn btn-primary" id="modify">수정</a>
                        <a href="${pageContext.request.contextPath}/notice/delete?noticeIdx=${noticeVO.notice_idx}" class="btn btn-primary">삭제</a>
                     </c:if>
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
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
   <script src="../resources/js/scripts.js"></script>
</body>
</html>