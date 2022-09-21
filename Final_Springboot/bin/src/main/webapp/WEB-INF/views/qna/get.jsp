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
	
<!--  댓글부분 -->
	   <div id="replyEditor">
        <div class="card">
            <div class="card-header">
                <h6><i class="fas fa-pencil"></i> 댓글 작성</h6>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <textarea class="form-control" id="newReply" name="content"></textarea>
                </div>
                <div class="form-group">
                    <input class="form-control" id="writer" type="text" name="writer" placeholder="작성자 이름을 입력해주세요">
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
                        <input class="form-control" id="replyNo" type="hidden" name="replyNo" value=""/>
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

    var getAll = function (obj, callback) {
        console.log("get all ...");
        $.getJSON("/reply/" + obj, callback);
    };

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
    };

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
//댓글 추가
var articleNo = [[${qna.qno}]];
var replyContentObj = $("textarea[name='reply']");
var replyWriterObj = $("input[name='replyer']");

// 댓글 추가 처리
$("#replyAddBtn").click(function () {
    if (confirm("댓글을 저장 하시겠습니까")) {
        var content = replyContentObj.val();
        var writer = replyWriterObj.val();
        var obj = {content: reply, writer: replyer, articleNo: qno};

        replyManager.add(obj, function (list) {
            printList(list);
            alert("새로운 댓글이 추가되었습니다.");
            $("#newReply").summernote("code", "");
            replyWriterObj.val("");
        });
    }
});
</script>



</body>
</html>