<%@ page import="org.example.coffee.dto.OrderDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    OrderDTO to = (OrderDTO)request.getAttribute( "to" );

    String email = to.getEmail();
    String address = to.getAddress();
    String zipcode = to.getZipcode();

%>

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

        .form-group input {
            margin-bottom: 10px; /* 위아래 간격 추가 */
        }

    </style>
    <title>Hello, world!</title>

    <script type="text/javascript">
        window.onload = function() {
            document.getElementById("mbtn").onclick = function() {
                if(document.mfrm.address.value == ''){
                    alert('주소를 입력하셔야 합니다.');
                    return false;
                }
                if(document.mfrm.zipcode.value == ''){
                    alert('우편번호를 입력하셔야 합니다.');
                    return false;
                }
                document.mfrm.submit();
            };
        };
    </script>
</head>

<body class="container-fluid">
<div class="card">
    <div class="row justify-content-center">
        <div class="col-md-12 mt-4 d-flex flex-column align-items-center p-3 pt-0">
            <h1 class="text-center">배송지 변경</h1>
            <form style="width:100%;" action="./modify_ok" name="mfrm" method="post">
                <input type="hidden" name="email" value="<%=email%>" />
                <div class="mb-3 form-group">
                    <label>주소</label>
                    <input type="text" class="form-control" id="address" name="address" value="<%= address %>">
                    <label>우편번호</label>
                    <input type="text" class="form-control" id="zipcode" name="zipcode" value="<%= zipcode %>">
                </div>
                <div class="text-center">
                    <button id="mbtn" class="btn btn-dark col-12 mb-3">변경</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>