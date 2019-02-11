<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-fixed-top navbar-dark bg-dark">
    <div class="container">
        <a href="${pageContext.request.contextPath}" class="navbar-brand">image Blog</a>
        <c:if test="${pageContext.request.remoteUser != null}">
            <form class="form-inline my-2" id="logoutForm" method="POST" action="logout">
                <a class="btn btn-info mr-1" href="profile">${pageContext.request.remoteUser}</a>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <a class="btn btn-info mr-1" onclick="document.forms['logoutForm'].submit()">
                    <span class="fa fa-sign-in">Logout</span>
                </a>
            </form>
        </c:if>
    </div>
</nav>