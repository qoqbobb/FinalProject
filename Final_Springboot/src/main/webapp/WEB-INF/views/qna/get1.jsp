<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<!-- 글읽기  -->
<div class="form-group">
          <label>NO.</label> <input class="form-control" name='qno'
             value='<c:out value="${get.qno}" />' readonly="readonly">
        </div>

          <div class="form-group">
            <label>Title</label> 
            <input class="form-control" name='q_title'
            value='<c:out value="${get.q_title}" />' readonly="readonly">
          </div>

          <div class="form-group">
          <label>Text area</label>
          <textarea class="form-control" rows="3" name='q_content'
            readonly="readonly"><c:out value="${get.q_content}" /></textarea>
        </div>

          <div class="form-group">
            <label>Writer</label>
       		<input class="form-control" name='q_writer'
       		 value='<c:out value="${get.q_writer}" />' readonly="readonly"
       		>
       
          </div>
     
 
	<button data-oper='list' class="" onclick="location.href='/qna/list'">리스트</button>
	
	
	
	
<!--댓글부분 -->
	   <div id="replyEditor">
        <div class="card">
            <div class="card-header">
                <h6><i class="fas fa-pencil"></i> 댓글 작성</h6>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <textarea class="form-control" id="newReply" name="reply"></textarea>
                </div>
                <div class="form-group">
                    <input class="form-control" id="writer" type="text" name="replyer" placeholder="작성자 이름을 입력해주세요">
                </div>
            </div>
            <div class="card-footer">
                <button type="button" class="btn btn-primary btn-sm float-right" id="replyAddBtn"><i
                        class="fa fa-save"></i> 댓글 저장
                </button>
            </div>
        </div>
    </div>
    <hr/>
    <div id="replies">
        <div class="card reply">
                <div class="card-header">
                    <div class="row">
                        <div class="col col-6">
                            <a href="#"><i class="fa fa-user"></i> </a>
                        </div>
                        <div class="col col-6">
                            <div class="btn-group float-right">
                                <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-cog"></i>
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" id="replyModBtn" href="#"><i class="fa fa-edit"></i> 수정</a>
                                    <a class="dropdown-item" id="replyDelBtn" href="#"><i class="fa fa-trash"></i> 삭제</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="form-group contentBody">

                    </div>
                    <div class="form-group">
                        <input class="form-control" id="rno"  name="rno" value=""/>
                    </div>
                    <div class="form-group btnArea">

                    </div>
                </div>
                <div class="card-footer">
                    <div class="small text-muted">
                        <span class="float-right"><i class="far fa-clock"></i> </span>
                    </div>
                </div>
            </div>
        </div>
   





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

//댓글 목록
$(document).ready(function(e) {
	
	$("#newReply").click(function(){
		placeholder : "댓글을 입력해 주세요"
		
	});
	
	(function() {
		replyManager.getAll(${get.qno}, printList);
	})();
	
	function printList(list){
		var str = "";
		var replyObj;
		for (var i = 0; i < list.length; i++){
			replyObj = list[i];
			str += "<div class=\"card reply\">\n" +
            "            <div class=\"card-header\">\n" +
            "                <div class=\"row\">\n" +
            "                    <div class=\"col col-6\">\n" +
            "                        <a href=\"#\"><i class=\"fa fa-user\"></i> " + replyObj.replyer + "</a>\n" +
            "                    </div>\n" +
            "                    <div class=\"col col-6\">\n" +
            "                        <div class=\"btn-group float-right\">\n" +
            "                            <button class=\"btn btn-secondary btn-sm dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
            "                                <i class=\"fa fa-cog\"></i>\n" +
            "                            </button>\n" +
            "                            <div class=\"dropdown-menu\">\n" +
            "                                <a class=\"dropdown-item\" id=\"replyModBtn\" href=\"#\"><i class=\"fa fa-edit\"></i> 수정</a>\n" +
            "                                <a class=\"dropdown-item\" id=\"replyDelBtn\" href=\"#\"><i class=\"fa fa-trash\"></i> 삭제</a>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"card-body\">\n" +
            "                <div class=\"form-group contentBody\">\n" + replyObj.reply +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <input class=\"form-control\" id=\"rno\" type=\"hidden\" name=\"rno\" value=\"" +replyObj.rno + "\"/>\n" +
            "                </div>\n" +
            "                <div class=\"form-group btnArea\">\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"card-footer\">\n" +
            "                <div class=\"small text-muted\">\n" +
            "                    <span class=\"float-right\"><i class=\"far fa-clock\"></i> " + formatDate(replyObj.replyDate) + " </span>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>" +
            "        <hr/>";
		}
		$("#replies").html(str);
	}
	
	function formatDate(timeValue) {
		var date = new Date(timeValue);
		return date.getFullYear()
		+ "-" + (date.getMonth() + 1 >= 10 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1))
        + "-" + date.getDate()
        + " " + date.getHours()
        + ":" + date.getMinutes();
	}




//댓글 추가
var qno = ${get.qno};
console.log(qno);
var replyContentObj = $("textarea[name='reply']");
console.log(replyContentObj);
var replyWriterObj = $("input[name='replyer']");
console.log(replyWriterObj);
// 댓글 추가 처리
$("#replyAddBtn").click(function () {
    if (confirm("댓글을 저장 하시겠습니까")) {
        var reply = replyContentObj.val();
        var replyer = replyWriterObj.val();
        var obj = {reply: reply, replyer: replyer, qno: qno};

        replyManager.add(obj, function (list) {
            printList(list);
            alert("새로운 댓글이 추가되었습니다.");
           /*  $("#newReply").summernote("code", ""); */
            replyWriterObj.val("");
            
        });
    }
});

//댓글 수정
$(document).on("click","#replyModBtn",function(event){
	event.preventDefault();
	console.log("수정 수정~~~");
	
	var thisReply = $(this).parents(".reply");
	var contentBody = thisReply.find(".contentBody");
	var btnArea = thisReply.find(".btnArea");
	
	
	var buttonStr = "<div class=\"btn-group buttons\" role=\"group\">\n" +
    "    <button type=\"button\" class=\"btn btn-secondary\" id=\"saveBtn\">저장</button>\n" +
    "    <button type=\"button\" class=\"btn btn-secondary\" id=\"cancelBtn\">취소</button>\n" +
    "</div>";
btnArea.html(buttonStr);
	
});


$(document).on("click","#saveBtn", function (event) {
	if(confirm("댓글을 수정하시겠습니까?")){
		var thisReply = $(this).parents(".reply");
		var contentBody = thisReply.find(".contentBody");
		var rno = thisReply.find("#rno").val();
		var obj = {reply: reply, qno: qno, rno: rno};
		
		replyManager.modify(obj, function(list){
			printList(list);
			alert("댓글이 수정되었습니다.");
			
		});
		
	}
});

//댓글 수정 취소
$(document).on("click", "#cancelBtn", function(event) {
	if(confirm("댓글 수정을 취소하시겠습니까?")){
		replyManager.getAll(${get.qno}, printList);
	}

});

//댓글 삭제
$(document).on("click", "#replyDelBtn", function(event) {
	event.preventDefault();
	if(confirm("댓글을 삭제하시겠습니까?")){
		var thisReply = $(this).parents(".reply");
		var rno = thisReply.find("#rno").val();
		var obj  = {qno : qno, rno : rno};
		replyManager.remove(obj, function (list) {
			printList(list);
			
			alert("댓글이 삭제 되었습니다");
			
		});
	}
	
});




});






</script>



</body>
</html>