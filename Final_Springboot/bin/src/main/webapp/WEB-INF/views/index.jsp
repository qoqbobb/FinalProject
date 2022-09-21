<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>인덱스파일</h1>
<p>principal : <sec:authentication property="principal" /> </p>
<p>사용자아이디 : <sec:authentication property="principal.username" /> </p>
<p>사용자비밀번호 : <sec:authentication property="principal.password" /> </p>
<p>사용자닉네임 : <sec:authentication property="principal.nickname" /> </p>


<%-- <p>UsersVO : <sec:authentication property="principal.user" /> </p>
<p>사용자이름 : <sec:authentication property="principal.user.USER_NICKNAME" /> </p>
<p>사용자아이디 : <sec:authentication property="principal.user.USER_ID" /> </p>
<p>사용자 권한 리스트 : <sec:authentication property="principal.user.authList" /> </p>  --%>
</body>
</html>