<%@ page import="org.example.coffee.dto.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <style>
        body {
            background: #ddd;
        }

        .card {
            margin: auto;
            max-width: 950px;
            width: 90%;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-radius: 1rem;
            border: transparent;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .summary {
            background-color: #ddd;
            border-top-right-radius: 1rem;
            border-bottom-right-radius: 1rem;
            padding: 4vh;
            color: rgb(65, 65, 65)
        }

        @media (max-width: 767px) {
            .summary {
                border-top-right-radius: unset;
                border-bottom-left-radius: 1rem
            }
        }

        .row {
            margin: 0;
        }

        .title b {
            font-size: 1.5rem
        }

        .col {
            padding: 0 1vh;
        }
        .position-relative {
            position: relative; /* 자식 요소의 절대 위치를 위한 상대 위치 */
        }

        .position-absolute {
            position: absolute; /* 부모 요소 내에서 절대 위치 */
        }

        .top-0 {
            top: 0; /* 상단에 위치 */
        }
        .end-0 {
            right: 0; /* 우측에 위치 */
        }

        .m-2 {
            margin-left: auto; /* 여백 설정 */
        }

        img {
            width: 3.5rem
        }

        hr {
            margin-top: 1.25rem
        }
        .table-scrollable {
            max-height: 540px;
            overflow-y: auto;
        }
    </style>
    <title>Hello, world!</title>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
    <h1 class="text-center">주문 내역</h1>
</div>
<c:forEach var="order" items="${orders}">
    <div class="card">
        <div class="row">
            <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0 table-scrollable">
                <table class="table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>주문금액</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="orderProduct" items="${order.orderProducts}">
                        <tr>
                            <td>${orderProduct.imagename}</td>
                            <td>${orderProduct.product_name}</td>
                            <td>${orderProduct.total_product_quantity}</td>
                            <td class="text-center price">${orderProduct.total_product_price}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-4 summary p-4">
                <div>
                    <h5 class="m-0 p-0"><b>Summary</b></h5>
                </div>
                <hr>

                <div class="row">
                    <h5 class="col">배송 상태</h5>
                    <h5 class="col text-end"><span class="badge bg-dark text-">${order.orderSummary.order_status}</span></h5>
                </div>
                <div class="row">
                    <h5 class="col">총 수량</h5>
                    <h5 class="col text-end"><span class="badge bg-dark text-">${order.orderSummary.total_order_quantity}</span></h5>
                </div>
                <div class="row pt-2 pb-2 border-top">
                    <h5 class="col">총 금액</h5>
                    <h5 class="col text-end"><span class="badge bg-dark text-">${order.orderSummary.total_order_price}</span></h5>
                </div>
                <button class="btn btn-dark col-12 mb-3" onclick="location.href='/orders/delete?order_id=${order.order_id}'">주문 취소</button>
                <button class="btn btn-dark col-12 mb-3" onclick="location.href='/orders/modify?order_id=${order.order_id}'">주문 수정</button>
                <button type="submit" class="btn btn-dark col-12" onclick="location.href='/user/menu_list'">상품 목록</button>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>