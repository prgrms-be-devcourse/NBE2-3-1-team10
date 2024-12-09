<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.coffee.dto.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    ArrayList<ProductDTO> list = (ArrayList<ProductDTO>) request.getAttribute("list");

    int totalRecord = list.size();

    StringBuilder sb = new StringBuilder();

    for (ProductDTO dto : list) {

        int productId = dto.getProductId();
        String productName = dto.getProductName();
        int price = dto.getPrice();
        int quantity = dto.getQuantity();
        int categoryId = dto.getCategoryId();
        String category = "";

        if(categoryId == 1) {
            category = "coffee";
        } else if (categoryId == 2) {
            category = "coffeeBean";
        } else if (categoryId == 3) {
            category = "tea";
        } else {
            category = "none";
        }


        sb.append("<tr align=\"center\">");
        sb.append("<td>&nbsp;</td>");
        sb.append("<td>" + productId + "</td>");
        sb.append("<td class='left'><a href='./modify?productId=" + productId + "'>" + productName + "</a></td>");
        sb.append("<td>" + price + "</td>");
        sb.append("<td>" + quantity + "</td>");
        sb.append("<td>" + category + "</td>");
        sb.append("<td>");
        sb.append("<input type=\"button\" value=\"상품수정\" class=\"btn_write btn_txt01\" style=\"cursor: pointer;\" onclick=\"location.href='./modify?productId=" + productId + "'\" />");
        sb.append("&nbsp;&nbsp;");
        sb.append("<input type=\"button\" value=\"상품삭제\" class=\"btn_write btn_txt01\" style=\"cursor: pointer;\" onclick=\"location.href='./delete?productId=" + productId + "'\" />");
        sb.append("</td>");
        sb.append("</tr>");
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
</head>
<body>
<!-- 상품 목록을 가져온 후 생성, 수정, 삭제를 하는 관리자 페이지 -->
<h3>Admin Page</h3>
<br><br>
<div class="con_txt">
    <div class="contents_sub">
        <div class="board_top">
            <div class="bold">총 <span class="txt_orange"><%=totalRecord%></span>건</div>
        </div>

        <div class="board">
            <table>
                <tr>
                    <th width="3%">&nbsp;</th>
                    <th width="10%">상품번호</th>
                    <th>상품명</th>
                    <th width="20%">가격</th>
                    <th width="15%">수량</th>
                    <th width="20%">카테고리</th>
                    <th width="20%">상품관리</th>
                </tr>
                <%=sb.toString()%>
            </table>
        </div>

        <div class="btn_area">
            <div class="align_right">
                <input type="button" value="상품목록" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='/'" />
                <input type="button" value="상품등록" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='./add'" />
            </div>
        </div>
        <!--//게시판-->
    </div>
</div>

</body>
</html>