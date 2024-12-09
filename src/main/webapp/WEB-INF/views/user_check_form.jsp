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
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            width: 90%;
            max-width: 500px;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-radius: 1rem;
            border: transparent
        }

        .row {
            margin: 0
        }

        .title b {
            font-size: 1.5rem
        }

    </style>
    <title>Hello, world!</title>
</head>
<body class="container-fluid">

<c:if test="${param.notExist eq 'true'}">
    <script>
        alert('주문 내역이 존재하지 않습니다.');
    </script>
</c:if>

<div class="card">
    <div class="row justify-content-center">
        <div class="col-md-12 mt-4 d-flex flex-column align-items-center p-3 pt-0">
            <h1 class="text-center">주문 내역 확인</h1>
            <form style="width:100%;" action="/orders" method="post">
                <div class="mb-3">
                    <label></label>
                    <input type="email" class="form-control" name="email" placeholder="name@example.com">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-dark col-12 mb-3">제출</button>
                </div>
            </form>
            <button type="submit" class="btn btn-dark col-12" onclick="location.href='/user/menu_list'">상품 목록</button>
        </div>
    </div>
</div>
</body>
</html>