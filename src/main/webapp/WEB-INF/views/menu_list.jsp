<%@ page import="org.example.coffee.dto.ProductDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ArrayList<ProductDTO> menu_list = (ArrayList<ProductDTO>) request.getAttribute("menu_list");
    int totalMenu = menu_list.size();
    StringBuilder sbHtml = new StringBuilder();

    for (ProductDTO to : menu_list) {

        int productId = to.getProduct_id();
        String productName = to.getProduct_name();
        int price = to.getPrice();
        int stock = to.getQuantity();
        String imageName = to.getImagename();
        int categoryId = to.getCategory_id();
        String category = "";

        if (categoryId == 1) {
            category = "coffee";
        } else if (categoryId == 2) {
            category = "coffeeBean";
        } else if (categoryId == 3) {
            category = "tea";
        } else {
            category = "none";
        }

        String imagePath = "/upload/" + imageName;
        System.out.println("[imagePath] : " + imagePath);

        sbHtml.append("<li class='list-group-item d-flex mt-3'>");
        sbHtml.append("<input type='hidden' name='productId' value='" + productId + "'>");
        sbHtml.append("<input type='hidden' name='stock' value='" + stock + "'>");
        sbHtml.append("<div class='col-2'><img class='img-fluid' src='"+imagePath+"' alt='"+productName+"'></div>");
        sbHtml.append("<div class='col'>");
        sbHtml.append("<div class='row text-muted'>" + category + "</div>");
        sbHtml.append("<div class='row'>" + productName + "</div>");
        sbHtml.append("</div>");
        sbHtml.append("<div class='col text-center price'>" + price + "</div>");
        sbHtml.append("<div class='col text-end action d-flex align-items-center justify-content-end'>");
        sbHtml.append("<input type='number' min='1' value='1' style='width: 60px; margin-right: 10px;'>");
        sbHtml.append("<a class='btn btn-small btn-outline-dark' href=''>추가</a>");
        sbHtml.append("</div>");
        sbHtml.append("</li>");
    }
%>

<script>
    // 아이템 정보를 저장할 배열
    const orderProducts = [];
    let total_price = 0;
    document.addEventListener('DOMContentLoaded', function() {
        const itemListElement = document.querySelector('.item-list');
        const totalPriceElement = document.querySelector('#total-price');
        const paymentBtn = document.querySelector('#paymentBtn');

        paymentBtn.addEventListener('click', async function (event) {
            event.preventDefault();

            // 배송지 정보
            const email = document.querySelector('#email').value;
            const address = document.querySelector('#address').value;
            const zipcode = document.querySelector('#zipcode').value;

            if (!email || !address || !zipcode) {
                alert("배송지 정보를 모두 입력해주세요.");
                return;
            }

            const orderData = {
                orderProducts: orderProducts,
                email: email,
                address: address,
                zipcode: zipcode,
                total_price: total_price
            }

            try {
                const response = await fetch('/orders/order', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(orderData)
                });

                const data = await response.json();

                if (data.success) {
                    alert("주문이 성공적으로 처리되었습니다.");
                    window.location.href = '/user/menu_list';
                } else {
                    alert(`주문 실패: ${data.message || "주문이 실패했습니다."}`);
                }
            } catch (error) {
                console.error("서버와의 통신 중 오류:", error);
                alert("서버와의 통신 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });

        // 요약 정보 업데이트
        const updateSummary = () => {
            itemListElement.innerHTML = '';
            total_price = 0; // 초기화
            orderProducts.forEach(item => {
                const itemSummary = document.createElement('div');
                itemSummary.classList.add('row');
                itemSummary.innerHTML = '<h6 class="p-0">' + item.productName + ' <span class="badge bg-dark">' + item.quantity + '개</span>' +
                    ' <span class="badge bg-danger ms-2 cancel-btn badge-sm" style="cursor: pointer;">취소</span></h6>';

                itemListElement.appendChild(itemSummary);
                // 결제 금액 반영
                total_price += (item.price * item.quantity);

                const cancelButton = itemSummary.querySelector('.cancel-btn');
                cancelButton.addEventListener('click', () => {
                    orderProducts.splice(orderProducts.indexOf(item), 1);
                    updateSummary();
                });
            });
            totalPriceElement.textContent = total_price.toLocaleString() + ' 원';
        };

        // 추가 버튼 클릭 시 동작
        const buttons = document.querySelectorAll('.btn-outline-dark');
        buttons.forEach((button) => {
            button.addEventListener('click', function(event) {
                event.preventDefault();

                // 아이템의 정보를 가져오기
                const itemElement = button.closest('li');
                const productId = parseInt(itemElement.querySelector('input[name="productId"]').value, 10);
                const stock = parseInt(itemElement.querySelector('input[name="stock"]').value, 10);
                const productName = itemElement.querySelector('.row:nth-child(2)').textContent.trim();
                const price = parseInt(itemElement.querySelector('.price').textContent.trim(), 10);
                const inputQuantity = parseInt(itemElement.querySelector('input[type="number"]').value, 10);

                if (inputQuantity <= 0 || isNaN(inputQuantity)) {
                    alert("정확한 수량을 입력해주세요.");
                    return;
                }

                // 이미 추가된 아이템인지 확인하고 수량 업데이트
                const existedItem = orderProducts.find(item => item.productId === productId);
                if (existedItem) {
                    if (existedItem.quantity + inputQuantity > stock) {
                        alert("재고보다 많은 상품은 주문하실 수 없습니다.");
                        return;
                    }
                    existedItem.quantity += inputQuantity;
                } else {
                    if (inputQuantity > stock) {
                        alert("재고보다 많은 상품은 주문하실 수 없습니다.");
                        return;
                    }
                    orderProducts.push({productId, stock, productName, price, quantity: inputQuantity});
                }
                updateSummary();
            });
        });
    });
</script>

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
            border: transparent
        }

        .summary {
            background-color: #ddd;
            border-top-right-radius: 1rem;
            border-bottom-right-radius: 1rem;
            padding: 4vh;
            color: rgb(65, 65, 65);
        }
        .item-list {
            max-height: 50px;
            overflow-y: auto;
            margin-bottom: 20px;
        }

        @media (max-width: 767px) {
            .summary {
                border-top-right-radius: unset;
                border-bottom-left-radius: 1rem
            }
        }

        .row {
            margin: 0
        }

        .title b {
            font-size: 1.5rem
        }

        .col-2,
        .col {
            padding: 0 1vh
        }

        img {
            width: 3.5rem
        }

        hr {
            margin-top: 1.25rem
        }

        .products {
            width: 100%;
        }

        .products .price, .products .action {
            line-height: 38px;
        }

        .products .action {
            line-height: 38px;
        }

    </style>
    <title>Grids & Circle 입니다</title>
</head>
<body class="container-fluid">
<div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul class="list-group products" style="height: 500px; overflow-y: auto;">
                <%= sbHtml.toString() %>
            </ul>
        </div>
        <div id="summary" class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr>
            <div class="item-list">
            </div>
            <form>
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email" class="form-control mb-1" id="email">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control mb-1" id="address">
                </div>
                <div class="mb-3">
                    <label for="zipcode" class="form-label">우편번호</label>
                    <input type="text" class="form-control" id="zipcode">
                </div>
                <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
            </form>
            <div class="row pt-2 pb-2 border-top">
                <h5 class="col">총금액</h5>
                <h5 class="col text-end" id="total-price">0원</h5>
            </div>
            <button class="btn btn-dark col-12" id="paymentBtn" style="margin-bottom: 10px;">결제하기</button>
            <button type="submit" class="btn btn-dark col-12" onclick="location.href='/orders'">주문내역확인하기</button>
        </div>
    </div>
</div>
</body>
</html>