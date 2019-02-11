let messages;
const prefix = '/rest/profile/message/';

$(document).ready(function() {
    $.ajax({
        type:'GET',
        contentType: 'application/json;charset=utf-8',
        url:prefix + 'all-by-user',
        dataType: 'json',
        success: function (response) {
            if (response.length > 0) {
                $('#messages_div').css('display', 'unset');
                // messages = response;
                setMessage(response);
            }
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
        let element = document.getElementById('message_pattern');
        let cln = element.cloneNode(true);
        setMessageBody(cln, messages[i]);
        $('#messages_div').append(cln);
        $(cln).show();
    }
}
// element.insertAdjacentHTML(position, cln);
function setMessageBody(messageHTML, message) {
    let mm = [message.id, message.topic, message.dateTime.slice(0,10), message.message];
    let message_row = messageHTML.firstElementChild;
    $(message_row).attr('id', message.id);
    let children = message_row.children;
    let count = 0;
    for (let i = 0; i < children.length; i++) { // - 1 = - buttons (edit, delete)
        let child = children[i];
        if (i === children.length - 1) {
            $(children[i]).children().attr('id', message.id);
            break;
        }
        for (let j = 0; j < child.children.length; j++) {
            child.children[j].innerHTML = mm[count++];
        }
    }
}

function editMessage(id) {
    // let messageById = $.grep(message, function(e){ return e.id === id; });
    let messageById;
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
function deleteMessage(id) {
    $.ajax({
        method: 'DELETE',
        data: checkCsrf(),
        url: prefix + id,
        dataType: 'json',
        success: function () {
            console.log('deleteRow(id)');
            console.log(document.getElementById(id));
            // $(document.getElementById(id)).parent().css('display', 'none');
            $(document.getElementById(id)).parent().remove();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}