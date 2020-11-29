<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
   <title>게시글 작성</title>
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
               <c:if test="${user.getEnroll_depart_id() == '02' }" >
                    <span>솔루션 사업 부서</span>
               </c:if>
               <c:if test="${ user.getEnroll_depart_id() == '03' }" >
                    <span>ICT 사업 부서</span>
               </c:if>
               <c:if test="${ user.getEnroll_depart_id() == '04' }" >
                    <span>기술연구소 부서</span>
               </c:if>

            </div>
         </div>
         <div id="jb-content">
         <!-- Begin Page Content -->
         <div class="container-fluid">   
            
            <div class="card shadow mb-4">
               <div class="card-header py-3">
                  <h4 class="m-0 ">게시글 작성</h4>
               </div>
         
               <div class="card-body">
                  <form name="boardForm" id="boardForm" action="${pageContext.request.contextPath}/board/create" method="POST">
                     <select name="deptSelect" id="deptSelect">
                        <c:forEach items="${departmentVOList}" var="dept">
                           <c:if test="${user.getEnroll_category() eq admin || user.getEnroll_depart_id() == dept.department_id}">
                              <option value="${dept.department_id}">${dept.department_name}</option>
                           </c:if>
                        </c:forEach>
                        
                     </select> <p>제목</p><input type="text" id="board_title" name="board_title" placeholder="게시글 제목"><br>
                    <p>내용</p> <textarea rows="10" cols="100" id="board_content" name="board_content" placeholder="게시글 내용"></textarea>
                     <br>
                     <button name="create_board" id="create_board">게시글 작성</button>
                  </form>
               </div>
            </div>
         </div>
         </div>
         
         <div id="jb-sidebar">
              <!-- 버튼 추가 -->
              <a href="/notice/list"><button class="btn btn-outline-primary">공지사항</button></a>
            <a href="/board/list"><button class="btn btn-outline-primary">게시판</button></a>
              <a href="/manage/main"><button class="btn btn-outline-primary">회원관리</button></a> 
          </div>
          <div id="jb-footer">
              <p>Copyright (주)TY SYS</p>
               <p>Call : 010-0000-0000</p>
            </div>
         
      </div>

            


</body>
</html>