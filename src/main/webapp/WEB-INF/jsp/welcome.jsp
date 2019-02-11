<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">

        <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
        <!--
        <h5>${pageContext.request.remoteUser}</h5>          username
            <br>
        <h5>${pageContext.request.userPrincipal.principal}</h5> UserTo{id=2, username='login2'}
        <h5>${pageContext.request.userPrincipal.principal.id}</h5>  userId
        -->
            <br>
            <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
            <br/>
            <a class="btn btn-info mr-1" href="messages">Messages</a>
        <%--</c:if>--%>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
