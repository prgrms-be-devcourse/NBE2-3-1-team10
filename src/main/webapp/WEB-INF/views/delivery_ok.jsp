<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Integer> failedIds = (ArrayList<Integer>) request.getAttribute("failedIds");
    int flag = (Integer) request.getAttribute("flag");

    out.println("<script type='text/javascript'>");

    if ( flag == 0 ) {
        // 정상
        out.println("alert( '출고 처리 되었습니다' );");
        out.println("location.href='./delivery';");
    } else {

        if (failedIds != null && !failedIds.isEmpty()) {

            String failedIdsStr = failedIds.toString();
            out.println("alert('처리 실패한 주문 ID: " + failedIdsStr + "');");

        } else {
            out.println("alert('실패');");
        }
    }
    out.println( "</script>" );
%>
