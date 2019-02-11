const prefix = "rest/profile/";
let person;
// console.log("Date.now() = " + Date.now());

function setValueById(id, value) {
    document.getElementById(id).innerText = value;
}

let RestGet = function() {
    checkCsrf();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url:  prefix,
        dataType: 'json',
        // async: true,
        success: function(result) {
            person = result;

            console.log('Id: ' + result.id
                + ', login: ' + result.login
                + ', firstName: ' + result.firstName
                + ', lastName: ' + result.lastName
                + ', enabled: ' + result.enabled
                + ', registered: ' + result.registered
                + ', roles: ' + result.roles
                + ', result is ok');

            setInputValuesByProfile();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
};

let RestPost = function() {
    console.log(person);
    person.login = $('#inputLogin').val();
    person.firstName = $('#inputFirstName').val();
    person.lastName = $('#inputLastName').val();
    console.log(JSON.stringify(person));
    // JSONObj.login = $('#inputLogin').val();
    // JSONObj.firstName = $('#inputFirstName').val();
    // JSONObj.lastName = $('#inputLastName').val();
    // console.log(JSONObj);
    // let JSONObject= {
    //     'id': String.valueOf(${user.login}),
    //     'login': String.valueOf(${user.login}),
    //     'password': String.valueOf(${user.password}),
    //     'firstName': String.valueOf(${user.firstName}),
    //     'lastName': '' + ${user.lastName} + '',
    //     'registered': String.valueOf(${user.registered}),
    //     'roles': '' + ${user.roles} + ''
    // };
    checkCsrf();
    $.ajax({
        type: 'PUT',
        url:  prefix,
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(person),
        dataType: 'json',
        async: true,
        success: function() {
            console.log("PUT");
            // noinspection JSAnnotator
            // $('#inputLogin').val() = result.login;
            // result.login = $('#inputLogin').val();
            // result.firstName = $('#inputFirstName').val();
            // result.lastName = $('#inputLastName').val();
            // console.log(JSONObject);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log('error post');
            if (jqXHR.status !== 200) {
                console.log('error');
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        }
    });
};

    // function deleteRow(id) {
    //     $.ajax({
    //         method: 'DELETE',
    //         data: checkCsrf(),
    //         prefix: prefix + id,
    //         dataType: 'json',
    //         success: function () {
    //             console.log("deleteRow(id)");
    //
    //             // updateTable();
    //         },
    //         error: function(jqXHR, textStatus, errorThrown) {
    //             alert(jqXHR.status + ' ' + jqXHR.responseText);
    //         }
    //     });
    // }

function deleteRow2() {
    $.ajax({
        method: 'DELETE',
        data: checkCsrf(),
        url: prefix,
        dataType: 'json',
        success: function () {
            console.log('deleteRow()');
            // session.destroy();
            window.location.href = 'redirect:/login';
            // updateTable();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

    // function deleteSomethingAjax(id) {
    //     $.ajax({
    //         prefix: prefix + "delete", //API_URI is the API's uri
    //         contentType : 'application/json',
    //         data: checkCsrf(),
    //         type:  'DELETE',
    //         success : function(data, textStatus, jqXHR) {
    //             alert( "Fine!" );
    //         },
    //         error : function(jqXHR, data, textStatus, errorThrown) {
    //             alert('WOOPS, something wrent wrong...');
    //         }
    //     });
    // }

function getJsonAjax() {
    let login = $('#detailsForm #inputLogin').val();
    let inputFirstName = $('#detailsForm #inputFirstName').val();
    let inputLastName = $('#detailsForm #inputLastName').val();
    let inputRegister = $('#detailsForm #inputRegister').val();

    console.log(login);
    console.log(inputFirstName);
    console.log(inputLastName);
    console.log(inputRegister);
}

function setInputValuesByProfile() {
    let date = new Date(person.registered).toISOString().slice(0,10);

    $("#inputLogin").val(person.login);
    $("#inputFirstName").val(person.firstName);
    $("#inputLastName").val(person.lastName);
    $("#inputRegister").val(date.slice(0,10));

    // $("#inputRegister").val("<fmt:formatDate value='person.registered' pattern='dd-MMMM-yyyy'/>");
    // setValueById('inputLogin', person.login);
    // $('#inputLogin').value = result.login;
    // $('#inputFirstName').value = person.firstName;
    // $('#inputLastName').value = person.lastName;
    // $('#inputRegister').value = "<fmt:formatDate value='person.registered' pattern='dd-MMMM-yyyy'/>";
}