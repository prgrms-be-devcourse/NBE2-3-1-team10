# NBE2-3-1-team10

## Coffee Bean Project

<img width="1083" alt="home1" src="https://github.com/user-attachments/assets/4d552573-3085-44e2-bdda-4479fe50384e">

## 프로젝트 소개

- 커피와 관련된 다양한 상품들을 주문할 수 있는 사이트입니다.


## 개발 환경

- Front : jsp(server side rendering)
- Back : Spring boot, Java, MyBatis
- 협업 툴 : Zoom, Notion, Github, Figma
- commit convention
    
    
    | **태그 이름** | **설명** |
    | --- | --- |
    | feat | 새로운 기능을 추가할 경우 |
    | fix | 버그를 고친 경우 |
    | docs | 문서를 추가/수정한 경우 |
    | style | 코드 의미에 영향을 주지 않는 변경사항 |
    | chore | 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
    | rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
    | remove | 파일 혹은 폴더명을 삭제하는 작업만 수행한 경우 |
    | init | 초기 설정 작업을 수행한 경우 |
    | refactor | 코드 수정한 경우.. |


- code convention
    
    
    | 변수명 | **설명** |
    | --- | --- |
    | 일반적인 변수 | camelCase 사용 |
    | 생성자, 클래스 | PascalCase 사용 |
    | 함수 | ‘동사형’으로 작성. 무엇을 담고 있는지 명확히 표현 |



## 프로젝트 구조

```
.
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── example
│   │   │           └── coffee
│   │   │               ├── CoffeeApplication.java
│   │   │               ├── config
│   │   │               │   └── PropertyConfig.java
│   │   │               ├── controller
│   │   │               │   ├── AdminController.java
│   │   │               │   ├── DeliveryController.java
│   │   │               │   ├── MenuController.java
│   │   │               │   └── OrderController.java
│   │   │               ├── dao
│   │   │               │   ├── DeliveryDAO.java
│   │   │               │   ├── OrderDAO.java
│   │   │               │   ├── OrderItemDAO.java
│   │   │               │   └── ProductDAO.java
│   │   │               ├── dto
│   │   │               │   ├── OrderDTO.java
│   │   │               │   ├── OrderItemDTO.java
│   │   │               │   ├── OrderProductDTO.java
│   │   │               │   ├── ProductDTO.java
│   │   │               │   ├── RequestOrderDTO.java
│   │   │               │   └── RequestOrderProductDTO.java
│   │   │               ├── mapper
│   │   │               │   ├── CoffeeMapper.java
│   │   │               │   ├── DeliveryMapper.java
│   │   │               │   ├── OrderItemMapper.java
│   │   │               │   └── OrderMapper.java
│   │   │               └── service
│   │   │                   └── OrderService.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   ├── mappers
│   │   │   │   ├── coffeeMapper.xml
│   │   │   │   ├── deliveryMapper.xml
│   │   │   │   ├── orderItemMapper.xml
│   │   │   │   └── orderMapper.xml
│   │   │   ├── schema.sql
│   │   │   ├── static
│   │   │   │   ├── css
│   │   │   │   │   ├── board.css
│   │   │   │   │   └── board_list.css
│   │   │   └── templates
│   │   └── webapp
│   │       └── WEB-INF
│   │           └── views
│   │               ├── admin_add.jsp
│   │               ├── admin_add_ok.jsp
│   │               ├── admin_delete.jsp
│   │               ├── admin_list.jsp
│   │               ├── admin_product_modify.jsp
│   │               ├── admin_product_modify_ok.jsp
│   │               ├── delivery_list.jsp
│   │               ├── delivery_ok.jsp
│   │               ├── menu_list.jsp
│   │               ├── order_delete_ok.jsp
│   │               ├── order_list.jsp
│   │               ├── order_modify.jsp
│   │               ├── order_modify_ok.jsp
│   │               └── user_check_form.jsp
│   └── test
│       └── java
│           └── org
│               └── example
│                   └── coffee
│                       └── CoffeeApplicationTests.java
└── uploads
```


## 역할 분담


### 김영서

- [유저] 상품 목록 조회
- [관리자] 상품 배송 알림


### 박채은

- [유저] 주문 수정
- [관리자] 배송 주문 취합


### 유수호

- [관리자] 상품 목록 조회
- [관리자] 상품 등록
- [유저] 주문 취소


### 장대영

- [관리자] 상품 삭제
- [유저] 상품 장바구니에 담기
- [유저] 주문 조회


### 최성빈

- [관리자] 상품 수정
- [유저] 상품 장바구니에서 제거
- [유저] 상품 주문


## 개발 기간 및 작업 관리


### 개발 기간

- 전체 개발 기간 : 2024-12-02 ~ 2024-12-10
- 이벤트 스토밍, ERD설계 : 2024-12-02 ~ 2024-12-03
<img width="1836" alt="event_stroming" src="https://github.com/user-attachments/assets/21d12263-12ba-4ffe-9f0d-0c627a7c923f">
<img width="1638" alt="erd1" src="https://github.com/user-attachments/assets/b0751851-1ae0-43eb-aa7d-0ae23aac4521">
- 기능 구현 : 2024-12-04 ~ 2024-12-09


### 작업 관리

- 프로젝트 시작 전 스크럼을 진행하여 현재 진행 상황 공유
- 중간 회의를 통해 프로젝트 작업 순서와 방향성에 대한 고민을 Notion에 기록


## 페이지 별 기능


### [홈 페이지]

<img width="1100" alt="home2" src="https://github.com/user-attachments/assets/76970143-c897-41d9-a0e4-97699d39b8d3">

- 상품 목록의 추가 버튼을 클릭하여 상품을 장바구니에 담을 수 있습니다.

<img width="1050" alt="list1" src="https://github.com/user-attachments/assets/5f50c14d-0141-414f-9306-81d681e78f74">

- 장바구니에 담긴 상품을 취소 버튼을 클릭하여 뺄 수 있습니다.

<img width="1016" alt="list2" src="https://github.com/user-attachments/assets/a66944d4-cd2b-4f74-8485-7003f91caf29">

- 이메일과 주소, 우편번호를 입력하고 결제하기 버튼을 누르면 주문이 완료됩니다.

<img width="1002" alt="list3" src="https://github.com/user-attachments/assets/06657114-58ae-4125-bd40-8ffc272f2fb8">

- 주문내역 확인하기 버튼을 클릭하면 주문을 한 이메일을 입력받고 주문 목록 페이지로 넘어갑니다.

<img width="986" alt="lis4" src="https://github.com/user-attachments/assets/fbffa9f7-98f2-4115-8191-636f4876295a">


### [주문 목록 페이지]

<img width="1055" alt="list5" src="https://github.com/user-attachments/assets/f6781faa-6ede-40df-a62f-e821880f8465">

- 주문 취소 버튼을 누르면 주문이 취소됩니다.

<img width="1098" alt="list6" src="https://github.com/user-attachments/assets/b4712f32-7855-468e-ba62-9f80ec92b641">

<img width="1060" alt="list7" src="https://github.com/user-attachments/assets/7c57dd4a-fda7-4c35-8fdf-703a13d63f85">

- 주문 수정 버튼을 누르면 배송 주소와 우편번호를 수정할 수 있습니다.

<img width="960" alt="list8" src="https://github.com/user-attachments/assets/e78f0894-315c-4a6a-bc01-2881dd204c51">


### [관리자 상품 목록 페이지]

<img width="2560" alt="product_list1" src="https://github.com/user-attachments/assets/99371373-df0c-4fbe-915a-2b372f36f658">

- 관리자가 상품을 관리할 수 있도록 관리자 페이지에 상품을 등록/수정/삭제가 가능하게 구현하였습니다.
- 배송 관리를 누르면 상품을 출고할 수 있는 페이지로 이동할 수 있습니다.


### [관리자 상품 등록 페이지]

<img width="2556" alt="list10" src="https://github.com/user-attachments/assets/6081396c-4585-4e3f-add9-333edb6b3305">

- 판매할 상품을 관리자 페이지에서 등록할 수 있습니다.
- 등록된 상품은 수정 및 삭제가 가능하고 삭제할 시 flag값으로 구분하여 삭제된 상품은 표시되지 않게 합니다.


### [관리자 상품 수정 페이지]

<img width="2556" alt="list11" src="https://github.com/user-attachments/assets/32b46644-cfcc-433d-9d25-b0dea110f554">

- 등록된 상품을 수정할 수 있습니다.
- 상품 번호를 제외한 모든 값을 변경할 수 있습니다.


### [관리자 상품 출고 페이지]

<img width="2560" alt="product_delivery1" src="https://github.com/user-attachments/assets/b9bae12f-6b07-465d-b7f1-6512328b6ff6">

- 오늘 배송할 주문 목록들을 확인할 수 있고 출고 버튼을 누르면 제품을 출고하여 주문의 상태를 배송중으로 변경합니다.
- 배송중인 주문은 수정이 불가능합니다.


## 트러블 슈팅

- 관리자 페이지에서 등록한 상품을 삭제하면 유저가 주문한 상품이 삭제되는 이슈
    - 상품 삭제를 할 때 delete 대신 update 구문을 이용하여 flag 값을 0 →1 로 수정하는 방식으로 해결
