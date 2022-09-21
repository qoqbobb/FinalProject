<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<style>
body {
	  padding-top: 100px;
	  padding-bottom: 30px;
}
</style>

<title>Q&A board</title>
</head>
<body>
	<!-- 게시글 읽기 -->
	<article>
		<div class="container" role="main">
			<h2>Q&A</h2>
			<form name="form" id="form" role="form">

				<div class="mb-3">
					<label for="tag">NO.</label> <input type="text"
						class="form-control" name='qno' id="tag"
						value='<c:out value="${get.qno}" />' readonly="readonly">
				</div>


				<div class="mb-3">
					<label for="title">제목</label> <input class="form-control"
						name='q_title' id="title" value='<c:out value="${get.q_title}" />'
						readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="reg_id">작성자</label> <input class="form-control"
						name='q_writer' id="reg_id"
						value='<c:out value="${get.q_writer}" />' readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name='q_content'
						id="content" readonly="readonly">
					<c:out value="${get.q_content}" />
					</textarea>
				</div>

			</form>

			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnList"
					onclick="location.href='/qna/list'">목록</button>
			</div>


			<!-- 댓글 작성 -->
			<!-- Reply Form {s} -->
			<div class="my-3 p-3 bg-white rounded shadow-sm"
				style="padding-top: 10px">
				<form name="form" id="form" role="form">
					<!-- <form:hidden path="bid" id="bid" /> -->
					<div class="row">
						<div class="col-sm-10">
							<textarea name="reply" id="content" class="form-control" rows="3"
								placeholder="댓글을 입력해 주세요"></textarea>
						</div>
						<div class="col-sm-2">
							<input name="replyer" class="form-control" id="reg_id"
								placeholder=" "></input>

							<button type="button" class="btn btn-sm btn-primary"
								id="btnReplySave" style="width: 100%; margin-top: 10px">
								저 장</button>
						</div>
					</div>
				</form>
			</div>
			<!-- Reply Form {e} -->


			<!-- Reply List {s}-->
			<div class="reply">
				<div class="my-3 p-3 bg-white rounded shadow-sm"
					style="padding-top: 10px">
					<h6 class="border-bottom pb-2 mb-0">댓글</h6>
					<div id="replyList"></div>
					<!-- 댓글번호 -->

					<input class="form-control" id="rno" name="rno" type="hidden"
						value="" />

				</div>
			</div>


			 
			<!-- Reply List {e}-->

		</div>
	</article>

	<!-- Ajax 자바스크립트 -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
var replyManager = (function () {

	//댓글목록
    var getAll = function (obj, callback) {
        console.log("get all ...");
        $.getJSON("/reply/" + obj, callback);
    };

    //댓글쓰기
    var add = function (obj, callback) {
        console.log("add ...");
        $.ajax({
            type: "post",
            url: "/reply/" + obj.qno,
            data : JSON.stringify(obj),
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
        console.log(obj);
    };
	//댓글 수정
    var modify = function (obj, callback) {
        console.log("modify ...");
        $.ajax({
            type: "put",
            url: "/reply/" + obj.qno,
            data : JSON.stringify(obj),
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
    };
	//댓글삭제
    var remove = function (obj, callback) {
        console.log("remove ...");
        $.ajax({
            type: "delete",
            url: "/reply/" + obj.qno + "/" + obj.rno,
            dataType : "JSON",
            contentType : "application/json",
            success:callback
        });
    };

    return {
        getAll : getAll,
        add : add,
        modify : modify,
        remove : remove
    }

})();
</script>

	<script type="text/javascript">
//댓글목록
$(document).ready(function(e) {
	$("#content").click(function() {
		placeholder : "댓글을 입력해 주세요."
	});
	(function() {
		replyManager.getAll(${get.qno}, printList);
	})();
	
	function printList(list) {
		var str = "";
		var replyObj;
		for(var i = 0; i < list.length; i++){
			replyObj = list[i];
			str += 
			 '<div class="reply">'+
			 '<div class="media text-muted pt-3" >'+
		     '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">'+
		     '<title>Placeholder</title>'+
		     '<image href="/resources/img/icon.png" width = 100% height =100%></image>'+
		     
		     '</svg>'+
		     '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">'+
		     '<span class="d-block">'+
		     '<strong class="text-gray-dark">' + replyObj.replyer + '</strong>'+
		     '<span style="padding-left: 7px; font-size: 9pt">'+
		     '<a href="javascript:void(0)" id="replyModBtn" style="padding-right:5px">수정</a>'+
		     '<a href="javascript:void(0)" id="replyDelBtn" >삭제</a>'+
		     '</span>'+
		     '</span>'+
		     replyObj.reply +
		     '</p>'+
		     '</div>'+
		     '<input class="form-control" id="rno"  name="rno" type="hidden" value="' + replyObj.rno + '" />' +
		     '</div>'
		     ;
		}
		$("#replyList").html(str);
	}
	
//댓글 추가
var qno = ${get.qno};
console.log(qno);	
var replyContentObj = $("textarea[name = 'reply']");	
console.log(replyContentObj);
var replyWriterObj = $("input[name = 'replyer']");
console.log(replyWriterObj);
$("#btnReplySave").click(function() {
	if(confirm("댓글을 등록하시겠습니까?")){
		var reply = replyContentObj.val();
		var replyer = replyWriterObj.val();
		var obj = {reply: reply, replyer: replyer, qno: qno};
		
		replyManager.add(obj, function(list) {
			printList(list);
			alert("새로운 댓글이 추가되었습니다");
			replyWriterObj.val("");
		});
	}
});

//댓글 삭제
$(document).on("click", "#replyDelBtn", function(event){
	event.preventDefault();
	if(confirm("댓글을 삭제하시겠습니까?")){
		var thisReply = $(this).parents(".reply");
		var rno = thisReply.find("#rno").val();
		
		var obj = {qno : qno, rno : rno};
		
		replyManager.remove(obj, function (list){
			printList(list);
			
			alert("댓글이 삭제되었습니다");
			
		});
	}
	
});


//댓글수정

$(document).on("click", "#replyModBtn", function(event) {
	event.preventDefault();
	console.log("수정수정");
		
	
	var thisReply = $(this).parents(".reply");
	var btnArea = thisReply.find(".reply");
	var htmls = "";
	htmls += 
	'<div class="reply">'+
	'<div class="media text-muted pt-3" id="rno' + rno + '" >'+
	'<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">'+
	'<title>Placeholder</title>'+
	'<image href="/resources/img/icon.png" width = 100% height =100%></image>'+
	
	'</svg>'+
	'<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">'+
	'<span class="d-block">'+
	'<strong class="text-gray-dark">' + reg_id + '</strong>'+ 
	'<span style="padding-left: 7px; font-size: 9pt">'+
	'<a href="javascript:void(0)" id = "saveBtn" style="padding-right:5px">저장</a>'+
	'<a href="javascript:void(0)" onClick="history.go()">취소<a>'+
	'</span>'+
	'</span>'+
	'<textarea name="editContent" id="editContent" class="form-control" rows="3">'+
	content +
	'</textarea>'+
	'</p>'+
	'</div>'+
	'<input class="form-control" id="rno"  name="rno" type="hidden" value="' + rno + '" />' +
	'</div>'; 
	btnArea.html(htmls);
	
})



	$(document).on("click", "#saveBtn", function(event) {
		if(confirm("댓글을 수정하시겠습니까?")){
			var thisReply = $(this).parents(".reply");
			var rno = thisReply.find("#rno").val();
			var obj = {reply: reply, qno: qno, rno: rno};
			
			replyManager.modify(obj, function(list){
				printList(list);
				alert("댓글이 수정되었습니다.");
				
			});
		}
		
	})

	
	
	
});






	




</script>




</body>
</html>