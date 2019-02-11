<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body style="background-color: #cdf5ff !important;">
<jsp:include page="fragments/header.jsp"/>
<script type="text/javascript" src="static/js/messages_fn.js" defer></script>

<div class="messages pt-4">
    <div class="container">
        <div class="row">
            <div class="col align-self-start">
                <h3><cite id="message-topic">Message 1</cite></h3>
            </div>
            <div class="col align-self-end">
                <div class="dropdown">
                    <button class="dropbtn dropdown-toggle" type="button">Themes</button>
                    <div class="dropdown-content">
                        <a href="#">Link 1</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div id="messages_div">
            <div class="alert alert-light" role="alert" id="messages">
                <div class="row" id="row_1">
                    <div class="col-2" id="row_message">
                        <div id="div_id" style="display: none;"></div>
                        <div id="div_topic" class="text-primary"></div>
                        <div id="div_date" class="text-muted"></div>
                    </div>
                    <div class="col-9">
                        <div id="div_message"></div>
                    </div>
                    <div class="col-1">
                        <button class="btn" onclick = editMessage($(this).attr('id'))><span class="fa fa-pencil"> Edit</span></button>
                        <button class="btn" onclick = deleteMessage($(this).attr('id'))><span class="fa fa-trash"> Delete</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
