let message;
const prefix = '/rest/profile/message/';

function setValueById(id, value) {
    document.getElementById(id).innerText = value;
}

$(document).ready(function() {
    // console.log(document.getElementById('messages_div'));
    $.ajax({
        type:'GET',
        contentType: 'application/json;charset=utf-8',
        url:prefix + 'all-by-user',
        dataType: 'json',
        success: function (response) {
            setMessage(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status !== 200) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
                alert('textStatus = ' + textStatus);
                alert('errorThrown = ' + errorThrown);
            }
        }
    });
});

$('#button1').click(function () {
    $.ajax({
        type:'GET',
        contentType: 'application/json;charset=utf-8',
        url:prefix + 1,
        dataType: 'json',
        success: function (response) {
            console.log(response);
            message = response;
            setValueById('id', response.id);
            setValueById('topic', response.topic);
            setValueById('message', response.message);
            setValueById('dateTime', response.dateTime);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status !== 200) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
                alert('textStatus = ' + textStatus);
                alert('errorThrown = ' + errorThrown);
            }
        }
    });
});

function setMessage(messages) {
    for (let i = 0; i < messages.length; i++) {
        // let element = document.getElementById('messages').firstElementChild;
        let element = document.getElementById('messages');
        // console.log(element);
        if (i === 0) {
            setMessageBody(element.firstElementChild, messages[i]);
        } else {
            let cln = element.cloneNode(true);
            setMessageBody(cln.firstElementChild, messages[i]);
            $('#messages_div').append(cln);
        }
    }
}
// element.insertAdjacentHTML(position, cln);
function setMessageBody(position, message) {
    let mm = [message.id, message.topic, message.dateTime.slice(0,10), message.message];
    // let mm = [message.topic, message.dateTime.slice(0,10), message.message];
    let children = position.children;
    let count = 0;
    for (let i = 0; i < children.length; i++) { // - 1 = - buttons (edit, delete)
        let child = children[i];
        if (i === children.length - 1) {
            // console.log(children[i].firstElementChild);
            $(children[i]).children().attr('id', message.id);
            break;
        }
        for (let j = 0; j < child.children.length; j++) {
            // console.log(child.children[j]);
            // $(child.children[j]).attr('id', 'message_' + message.id);
            child.children[j].innerHTML = mm[count++];
        }
    }
    // console.log("$(position)");
    // console.log($(position));
    // console.log("$(position).get()");
    // console.log($(position).get());
    // console.log("$(position).get(0)");
    // console.log($(position).get(0));
    // console.log("$(position).get(0).children");
    // console.log($(position).get(0).children);
    // console.log("$(position).get(0).children[0]");
    // console.log($(position).get(0).children[0]);
    // console.log("$(position).get(0).children[1]");
    // console.log($(position).get(0).children[1]);
    // console.log("$(position).get().customElements");
    // console.log($(position).get().customElements);
    // console.log("");
    // console.log(position);
    // console.log(message);
    // console.log("position.childNodes");
    // console.log(position.childNodes);
    // console.log("position.childNodes[0]");
    // console.log(position.childNodes[0]);
    // console.log(position.childList);
    // console.log(position.firstElementChild);
    // let elementsByTagName = position.getElementsByTagName('div');
    // for (let i = 0; i < elementsByTagName.length; i++) {
    //     console.log(i);
    //     console.log(position.getElementsByTagName('div')[i]);
    // }
    // console.log(elementsByTagName);
    // position.getElementById('div_topic').innerHTML = message.topic;
    // position.getElementById('div_date').innerHTML = message.dateTime;
    // position.getElementById('div_message').innerHTML = message.message;
}

function editMessage(id) {
    // let messageById = $.grep(message, function(e){ return e.id === id; });
    let messageById;
    console.log(messageById);
    checkCsrf();
    $.ajax({
        type: 'PUT',
        url:  prefix,
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(messageById),
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
}
function deleteMessage(data) {

}