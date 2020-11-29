<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"
   contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>TY SYSTEM</title>
<link href="../resources/css/styles.css" rel="stylesheet" />
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>

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
            <span>${user.getEnroll_name() }님 </span> <a href="../update"><button
                  class="btn btn-primary btn-sm">회원 본인 수정</button></a> <a
               href="../logout"><button class="btn btn-dark btn-sm">로그아웃
               </button></a>
            <p>
               <a href="../notice">공지사항</a>을 확인해 주세요
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

            <!-- 헤더 끝 -->
            <!-- DataTales Example -->
            <div class="card shadow mb-4">
               <div class="card-header py-3">
                  <h4 class="m-0 ">공지사항</h4>
               </div>
               <div class="card-body">
                  <br />
                  <!-- 검색 구간 -->
                  <div align="right">
                     <div>
                        <select id="dept_select" onchange="change_dept(this)">
                           <c:forEach items="${departmentVOList}" var="dept"
                              varStatus="status">
                              <c:choose>
                                 <c:when test="${deptId eq null and status.count == 1}">
                                    <option value="${dept.department_id}" selected>${dept.department_name}</option>
                                 </c:when>
                                 <c:when test="${deptId == dept.department_id}">
                                    <option value="${dept.department_id}" selected>${dept.department_name}</option>
                                 </c:when>
                                 <c:otherwise>
                                    <option value="${dept.department_id}">${dept.department_name}</option>
                                 </c:otherwise>
                              </c:choose>
                           </c:forEach>
                        </select> <input id="search_type" name="search_type" value="${keyWord}" />
                        <input type='button' onclick="check()" value="검색" />
                     </div>
                     <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable">
                           <thead>
                              <tr>
                                 <th>번호</th>
                                 <th>제목</th>
                                 <th>작성자</th>
                                 <th>부서</th>
                                 <th>조회수</th>
                              </tr>
                           </thead>
                           <tbody>
                              <c:forEach items="${noticeVOList}" var="notice">
                                 <tr>
                                    <td>${notice.notice_idx}</td>
                                    <td><a
                                       href="${pageContext.request.contextPath}/notice/read?noticeIdx=${notice.notice_idx}">${notice.notice_title   }</a>
                                    </td>
                                    <td>${notice.notice_writer}</td>
                                    <td>${notice.department_name}</td>
                                    <td>${notice.notice_count}</td>
                                 </tr>
                              </c:forEach>
                           </tbody>
                        </table>
                        <div class="col-sm-12 col-md-7">
                           <div class="box-footer">
                              <div class='text-center'>
                                 <ul class='pagination' id='pageMove'>
                                    <c:if test="${pageMaker.prev}">
                                       <li class="paginate_button page-item previous"
                                          id="dataTable_previous"><a href=''>이전</a></li>

                                    </c:if>
                                    <c:forEach begin="${pageMaker.startPage}"
                                       end="${pageMaker.endPage}" var="idx">
                                       <li class="paginate_button page-item"
                                          <c:out value= "${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>><a class="page-link" aria-controls="dataTable"
                                          href="${pageContext.request.contextPath}/notice/list?page=${idx}&keyWord=${keyWord}">${idx}</a></li>
                                    </c:forEach>
                                    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                       <li class="paginate_button page-item active"><a
                                          href=''>다음</a></li>
                                    </c:if>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="btn_right mt15">
                     	<c:if test="${sessionScope.user.getEnroll_category() != '03' }">
                        <button type="button" class="btn black mr5"
                           onclick="create_notice()">공지사항 작성</button>
                           </c:if>
                     </div>
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
         </a> <a href="/board/list">
            <button class="btn btn-outline-primary">게시판</button>
         </a> <a href="/manage/main">
            <button class="btn btn-outline-primary">회원관리</button>
         </a>
      </div>
      <div id="jb-footer">
         <p>Copyright (주)TY SYS</p>
         <p>Call: 010-0000-0000</p>
      </div>
   </div>
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
   <script src="../resources/js/scripts.js"></script>
</body>
</html>