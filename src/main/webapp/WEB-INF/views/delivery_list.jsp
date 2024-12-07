<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.coffee.dto.OrderDTO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>


<%
    ArrayList<OrderDTO> lists = (ArrayList<OrderDTO>) request.getAttribute( "lists" );

    int totalRecord = lists.size();
    ArrayList<Integer> orderIds = new ArrayList<Integer>();


    StringBuilder sbHtml = new StringBuilder();

    for( OrderDTO to : lists ) {

        orderIds.add(to.getOrder_id());

        int order_id = to.getOrder_id();
        String email = to.getEmail();
        String address = to.getAddress();
        String zipcode = to.getZipcode();
        LocalDateTime order_time = to.getOrder_time();
        String order_status = to.getOrder_status();
        int total_price = to.getTotal_price();

        sbHtml.append( "<tr>" );
        sbHtml.append( "<td>&nbsp;</td>" );
        sbHtml.append( "<td>" + order_id + "</td>" );
        sbHtml.append( "<td>" + email + "</td>" );
        sbHtml.append( "<td>" + address + "</td>" );
        sbHtml.append( "<td>" + zipcode + "</td>" );
        sbHtml.append( "<td>" + order_time + "</td>" );
        sbHtml.append( "<td>" + order_status + "</td>" );
        sbHtml.append( "<td>" + total_price + "</td>" );
        sbHtml.append( "<td>&nbsp;</td>" );
        sbHtml.append( "</tr>" );
    }

    System.out.println(orderIds);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../../css/board.css">
    <script type="text/javascript">
        const totalRecord = <%=totalRecord%>;
        window.onload = function () {
            document.getElementById('dbtn').onclick = function () {
                alert('총 ' + totalRecord + '건의 주문이 출고 처리되었습니다.');
                document.deliveryForm.submit();
            };
        };
    </script>
    <style>
        .right-action {
            text-align: right;
            margin: 10px 20px;
        }

        .action-btn {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .action-btn:hover {
            background-color: #218838;
        }
    </style>

</head>

<body>
<div class="con_title" style="text-align: center; margin-top: 50px;">
    <h2>오늘 배송할 주문 목록</h2>
    <form action="delivery_ok" method="post" name="deliveryForm">
        <input type="hidden" name="orderIds" value="<%=orderIds%>">
        <div class="right-action">
            <input type="button" id="dbtn" value="출고" class="action-btn"/>
        </div>
    </form>
</div>
<div class="con_txt">
    <div class="contents_sub">
        <div class="board_top">
            <div class="bold">총 <span class="txt_gray"><%= totalRecord %></span>건</div>
        </div>

        <div class="board">
            <table>
                <tr>
                    <th width="3%">&nbsp;</th>
                    <th width="8%">주문 번호</th>
                    <th>이메일</th>
                    <th width="20%">주소</th>
                    <th width="10%">우편번호</th>
                    <th width="15%">주문 시간</th>
                    <th width="10%">주문 상태</th>
                    <th width="10%">총 가격</th>
                    <th width="3%">&nbsp;</th>
                </tr>

                <%= sbHtml.toString() %>

            </table>
        </div>
    </div>
</div>
</body>
</html>