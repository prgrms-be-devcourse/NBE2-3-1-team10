<%@ page import="org.example.coffee.dto.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    ProductDTO to = (ProductDTO) request.getAttribute("to");

    int productId = to.getProduct_id();
    String productName = to.getProduct_name();
    int price = to.getPrice();
    int quantity = to.getQuantity();
    int categoryId = to.getCategory_id();
    String category;

    switch (categoryId) {
        case 1 :
            category = "Coffee";
            break;
        case 2:
            category = "CoffeeBean";
            break;
        case 3:
            category = "tea";
        default:
            category = "select";
    }
%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("updateBtn").onclick = function () {

                if (document.wfrm.product_name.value == '') {
                    alert('상품명을 입력해주세요.');
                    return false;
                }

                if (document.wfrm.price.value == '') {
                    alert('가격을 입력해주세요.');
                    return false;
                }

                if (document.wfrm.quantity.value == '') {
                    alert('수량을 입력해주세요.');
                    return false;
                }

                if (document.wfrm.category.value == '') {
                    alert('카테고리를 입력해주세요.');
                    return false;
                }
                if (document.wfrm.upload.value != '') {
                    let extension = document.wfrm.upload.value.split( '.' ).pop();
                    if( extension != 'png' && extension != 'jpg' && extension != 'gif' ) {
                        alert( '이미지 파일을 등록하셔야 합니다.' );
                        return false;
                    }
                }

                if (document.wfrm.deleteImage.checked) {
                    document.getElementById("flag").value = "1";
                }

                document.wfrm.submit();
            };
        };
    </script>
</head>
<body>
<h3>Admin Page - product add</h3>
<br><br>
<div class="con_txt">
    <form action="/admin/modify_ok" method="post" name="wfrm" enctype="multipart/form-data">
        <input type="hidden" name="product_id" value="<%=productId%>"/>
        <input type="hidden" name="flag" id="flag" value="0"/>
        <div class="contents_sub">
            <div class="board_write">
                <table>
                    <tr>
                        <th class="top">상품명</th>
                        <td class="top">
                            <input type="text" name="product_name" value="<%=productName%>" class="board_view_input" maxlength="50" />
                        </td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td><input type="text" name="price" value="<%=price%>" class=board_view_input_mail /></td>
                    </tr>
                    <tr>
                        <th>수량</th>
                        <td><input type="text" name="quantity" value="<%=quantity%>" class="board_view_input_mail"/></td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>
                            <select name="category" class="board_view_input_mail">
                                <option value="<%=categoryId%>" selected><%=category%></option>
                                <option value="1">Coffee</option>
                                <option value="2">Coffee Bean</option>
                                <option value="3">Tea</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>상품이미지</th>
                        <td>
                            기존 이미지 : <img src="${imagePath}" alt="기존 상품 이미지" style="max-width: 200px; height: auto;" />
                            <button type="button" onclick="document.getElementById('flag').value='1';">기존 이미지 삭제
                            </button>
                            <%--                             둘중 가독성 좋은걸로 하겠습니다. 버튼형식은 클릭시 색 변화를 줘야할듯--%>
                            <input type="checkbox" name="deleteImage" id="deleteImage"/> 기존 이미지 삭제

                            <br/><br/>
                            <input type="file" name="upload"/>

                        </td>
                    </tr>
                </table>
            </div>

            <div class="btn_area">
                <div class="align_left">
                    <input type="button" value="상품목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./list'" />
                </div>
                <div class="align_right">
                    <input type="button" id="updateBtn" value="상품수정" class="btn_write btn_txt01" style="cursor: pointer;" />
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>