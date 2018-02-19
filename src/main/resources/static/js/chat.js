var currentChatId = null;
var chatsArea = document.querySelector('#chats-area');
var messagesArea = document.querySelector('#messages-area');
var partnersArea = document.querySelector('#partners-area');
var chatAddText = document.querySelector('#add-chat-text');
var partnerAddText = document.querySelector('#add-partner-text');
var messageSendText = document.querySelector('#send-message-text');
var chatLabel = document.querySelector('#chat-label');


var stompClient = null;

function onLoadPage() {
    connect();
    getFullInfo();
}

function getFullInfo() {
    $.get("/get_full_info", {},
        function (data) {
            currentChatId = data.chats[0].id;
            renderAllChats(data.chats);
            renderCurrentMessages(data.currentChatMessages);
            renderCurrentPartners(data.currentChatPartners);
            setChatLabel(data.currentChatLabel);
        });
}

function renderAllChats(chats) {
    $.each(chats, function (index, value) {
        renderOneChat(value);
    })
    document.getElementById(currentChatId).classList.add('list-group-item-secondary')
}

function renderOneChat(chat) {
    var chatElement = document.createElement('li');
    chatElement.className = 'chat-message'
    chatElement.classList.add('list-group-item');
    chatElement.id = chat.id
    chatElement.innerHTML = '<div onclick="onChatClick(' + chat.id + ')">' +
        '<i style="background-color: green;">' + chat.chatName.charAt(0) + '</i>' +
        '<h6 class="m-0">' + chat.chatName + '</h6>' +
        '<p class=" m-0">' + chat.lastText + '</p>' +
        '</div>';
    chatsArea.appendChild(chatElement);
}

function renderCurrentMessages(messages) {
    $.each(messages, function (index, value) {
        renderOneMessage(value);
    });
    messagesArea.parentElement.scrollTop = messagesArea.parentElement.scrollHeight;

}

function renderOneMessage(message) {
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');
    messageElement.classList.add('list-group-item');

    var avatarElement = null;
    if (message.imageUrl) {
        avatarElement = document.createElement("div");
        avatarElement.className = 'message-img';
        avatarElement.innerHTML ='<img id="my-avatar" src="' + message.imageUrl + '"alt="Аватар">'
    } else {
        avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.firstName.charAt(0));
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = 'green';
    }
    messageElement.appendChild(avatarElement);

    var usernameElement = document.createElement('h6');
    usernameElement.classList.add('m-0');
    var usernameText = document.createTextNode(message.firstName + " " + message.lastName);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    textElement.classList.add('m-0');
    var messageText = document.createTextNode(message.text);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messagesArea.appendChild(messageElement);
}

function renderCurrentPartners(partners) {
    $.each(partners, function (index, value) {
        renderOnePartner(value);
    })
}

function renderOnePartner(partner) {
    var partnerElement = document.createElement('h6');
    partnerElement.innerText = partner.fullName;
    partnersArea.appendChild(partnerElement);
}

function setChatLabel(label) {
    chatLabel.innerText = label;
}


function onChatClick(id) {
    document.getElementById(currentChatId).classList.remove('list-group-item-secondary');
    currentChatId = id;
    document.getElementById(currentChatId).classList.add('list-group-item-secondary');
    while (messagesArea.firstChild) {
        messagesArea.removeChild(messagesArea.firstChild);
    }
    while (partnersArea.firstChild) {
        partnersArea.removeChild(partnersArea.firstChild);
    }
    $.get("/get_messages",
        {chatId: id},
        function (data) {
            renderCurrentMessages(data.currentChatMessages);
            renderCurrentPartners(data.currentChatPartners);
            setChatLabel(data.currentChatLabel);
        })
}


function onChatAddClick() {
    $.post("/add_chat",
        {chatName: chatAddText.value});
    chatAddText.value = '';
}


function onPartnerAddClick() {
    $.post("/add_partner",
        {
            chatId: currentChatId,
            partnerName: partnerAddText.value
        });
    partnerAddText.value = '';
}


function onMessageSendClick() {
    $.post("/send_message",
        {
            chatId: currentChatId,
            message: messageSendText.value
        });
    messageSendText.value = '';

}


function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, {});
}

function onConnected() {
    stompClient.subscribe('/user/subscribe', onMessageReceived);
}

function onMessageReceived(payload) {
    var body = JSON.parse(payload.body)

    if (body.message) {
        if (body.message.chatId == currentChatId) {
            renderOneMessage(body.message);
        }
        changeOneChat(body.message);
        insertChatToTop(body.message.chatId);
        messagesArea.parentElement.scrollTop = messagesArea.parentElement.scrollHeight;
    } else if (body.chat) {
        renderOneChat(body.chat);
        insertChatToTop(body.chat.id);
    } else if (body.partner) {
        renderOnePartner(body.partner);
    }
}

function insertChatToTop(chatId) {
    if (chatsArea.childElementCount > 1) {
        chatsArea.insertBefore(document.getElementById(chatId), chatsArea.firstChild);
    }
}

function changeOneChat(message) {
    var currentChatElement = document.getElementById(message.chatId);
    currentChatElement.firstElementChild.lastElementChild.innerHTML = message.firstName + ': ' + message.text;

    //TODO:

}

function deleteCurrentChat(){
    $.post("delete_chat"
    ,{id: currentChatId}, deleteChat)
}

function deleteChat(){
    document.getElementById(currentChatId).remove();

}

onLoadPage();