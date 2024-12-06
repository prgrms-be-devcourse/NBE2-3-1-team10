<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    int flag = (int) request.getAttribute("flag");

    // 에러 중심의 후처리 => 자바스크립트 중심으로 처리
    out.println("<script type='text/javascript'>");
    if (flag == 0) {
        // 정상
        out.println("alert('주문취소 성공');");
        out.println("location.href='/orders';");
    } else {
        // 비정상
        out.println("alert('주문취소 실패');");
        out.println("history.back();");
    }
    out.println("</script>");
%>