<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <base href="${pageContext.request.contextPath}/"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>


    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>

    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

    <title>Blog</title>

    <link href="static/css/common.css" rel="stylesheet">
    <link href="static/css/dropdown.css" rel="stylesheet">
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="webjars/datetimepicker/build/jquery.datetimepicker.min.css" rel="stylesheet">

    <script type="text/javascript" src="static/js/functions.js" defer></script>
    <script type="text/javascript" src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datetimepicker/build/jquery.datetimepicker.full.min.js" defer></script>
</head>

