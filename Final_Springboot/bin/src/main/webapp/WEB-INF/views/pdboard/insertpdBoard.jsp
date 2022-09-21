<%@page contentType="text/html; charset=UTF-8" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
    <style>
        table {
            width: 100%;
            border: 1px solid #444444;
            border-collapse: collapse;
        }
        table th {
            border: 1px solid #444444;
            text-align: center;
            height: 40px;
            background-color: dodgerblue;
            color: cornsilk;
        }
        table td {
            border: 1px solid #444444;
            text-align: left;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
<h3>글쓰기</h3>
<hr>
<form action="insertBoard" method="post">
    <table style="width: 700px; margin: auto">
        <tr>
            <td width="70" style="background-color: dodgerblue; color: cornsilk">제목</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td style="background-color: dodgerblue; color: cornsilk">작성자</td>
            <td><input type="text" name="admin"/></td>
        </tr>
        <tr>
            <td style="background-color: dodgerblue; color: cornsilk">내용</td>
            <td><textarea name="content" cols="40" rows="10"></textarea></td>
        </tr>
        <tr>
            <td style="background-color: dodgerblue; color: cornsilk">가격</td>
            <td><input type="text" name="price"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="등록"/>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>