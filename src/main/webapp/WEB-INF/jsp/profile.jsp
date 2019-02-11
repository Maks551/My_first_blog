<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="static/js/profile_fn.js" defer></script>
<jsp:include page="fragments/header.jsp"/>

<div class="container-full-bg">
<div class="jumbotron pt-4">
    <div class="container">
        <form id="detailsForm" action="profile">
            <div class="form-group row">
                <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
                <div   class="col-sm-5">
                    <input type="text" class="form-control" name="inputLogin" id="inputLogin" value="">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputFirstName" class="col-sm-2 col-form-label">First name</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="inputFirstName" value="">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputLastName" class="col-sm-2 col-form-label">Last name</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="inputLastName" value="">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputRegister" class="col-sm-2 col-form-label">Register</label>
                <div class="col-sm-5">
                    <input type="text" readonly class="form-control" id="inputRegister" value="">
                </div>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-primary" onclick="RestPost()">Update</button>
                <button type="button" class="btn btn-danger" onclick="deleteRow2()">Delete</button>
            </div>
        </form>
    </div>
</div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>

<script type="text/javascript">
    $(document).ready(function () {
        RestGet();
    });
</script>

</html>
