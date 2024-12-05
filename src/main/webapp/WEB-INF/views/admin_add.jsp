<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/board.css">
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("wbtn").onclick = function () {

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

                if (document.wfrm.upload.value == '') {
                    alert('이미지를 등록해주세요.');
                    return false;
                }

                document.wfrm.submit();
            };
        };
    </script>
</head>
<body>
<!-- 상품을 등록하는 관리자 페이지 -->
<h3>Admin Page - product add</h3>
<br><br>
<div class="con_txt">
    <form action="/admin/add_ok" method="post" name="wfrm" enctype="multipart/form-data">
        <div class="contents_sub">
            <!--게시판-->
            <div class="board_write">
                <table>
                    <tr>
                        <th class="top">상품명</th>
                        <td class="top">
                            <input type="text" name="product_name" value="" class="board_view_input" maxlength="50" />
                        </td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td><input type="text" name="price" value="" class=board_view_input_mail /></td>
                    </tr>
                    <tr>
                        <th>수량</th>
                        <td><input type="text" name="quantity" value="" class="board_view_input_mail"/></td>
                    </tr>
                    <tr>
                        <th>카테고리</th>
                        <td>
                            <select name="category" class="board_view_input_mail">
                                <option value=1>Coffee</option>
                                <option value=2>Coffee Bean</option>
                                <option value=3>Tea</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>상품이미지</th>
                        <td>
                            <input type="file" name="upload" value="" />
                        </td>
                    </tr>
                </table>
            </div>

            <div class="btn_area">
                <div class="align_left">
                    <input type="button" value="상품목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./list'" />
                </div>
                <div class="align_right">
                    <input type="button" id="wbtn" value="상품등록" class="btn_write btn_txt01" style="cursor: pointer;" />
                </div>
            </div>
            <!--//게시판-->
        </div>
    </form>
</div>

</body>
</html>