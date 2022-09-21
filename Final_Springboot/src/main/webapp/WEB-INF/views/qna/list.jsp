<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
 
<style>
body {
padding-top: 70px;
padding-bottom: 30px;

}
</style>
 
</head>

<body>
	
	<div class="page-wrapper">
    <div class="container-fluid">
      <!--   <div class="col-lg-8"> --><!--게시판 넓이 -->
        <div class="col-lg-8" style="float: none; margin:0 auto;">
	<a onclick="location.href='/main'" style="color: black">Elesco Admin</a>
            <div class="col-lg-12">
                <h1 class="page-header">Q&A 게시판</h1>
            </div>
         
            <div class="panel panel-default">
                <div class="panel-heading"> Q&A </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>No.1</th>
                                <th>제목</th>
                                <th>내용</th>
                              
                                <th>작성자</th>
                                <th>작성일자</th>
                                <th>수정일자</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        <c:forEach items="${list}" var="QnaVO">
                            <tr>
                                <td><c:out value="${QnaVO.qno}"/></td>
                            
                                <%-- <td><a class='move' href='<c:out value="${QnaVO.qno}"/>'>
									<c:out value="${QnaVO.q_title}" /> --%>
									
                              <td><a href='/qna/get?qno=<c:out value="${QnaVO.qno}"/>'>
                                <c:out value="${QnaVO.q_title}"/></a></td>
                                <td><c:out value="${QnaVO.q_content}"/></td>
                                <td><c:out value="${QnaVO.q_writer}"/></td>
                               
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${QnaVO.regdate}"/></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${QnaVO.updatedate}"/></td>
                            </tr>
                         </c:forEach>   
                        </tbody>
                    </table>
				</div>
			</div>
		</div>
		<!-- </div> -->
	</div>
</div>
	
	 <!-- Footer-->
        <footer class="bg-light py-5">
            <div class="container px-4 px-lg-5"><div class="small text-center text-muted">Copyright &copy; 2022 - Elesco</div></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- SimpleLightbox plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
        <!-- Core theme JS-->
        <script src="js1/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	
</body>
</html>