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
        String productName;
        int price;
        int quantity;
        String imagename;
        int categoryId;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
admin_product.jsp
<!-- 상품 목록을 가져온 후 생성, 수정, 삭제를 하는 관리자 페이지 -->
<br><br>
<div>
    <table>

    </table>
</div>

</body>
</html>