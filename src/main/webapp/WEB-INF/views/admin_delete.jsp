<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int flag = (Integer)request.getAttribute( "flag" );

    out.println( "<script type='text/javascript'>" );
    if ( flag == 1 ) {
        out.println("alert( '상품 삭제 성공' );");
        out.println("location.href='./list';");
    } else {
        out.println( "alert( '상품 삭제 실패' );" );
        out.println( "history.back();" );
    }
    out.println( "</script>" );
%>