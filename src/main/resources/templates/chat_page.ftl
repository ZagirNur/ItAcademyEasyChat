<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Простой чат</title>
</head>
<body>

<nav class=" container navbar navbar-expand-sm navbar-dark bg-primary shadow">
    <a class="navbar-brand" href="./">Простой чат</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav w-100">
            <li class="nav-item ml-sm-auto">
                <a class="nav-link active" href="./logout">Выход</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container shadow">
    <div class="row page-content">
        <div class="col-4 border-right p-3">
            <div class="row about-me mr-1 border-bottom">
                <div class="col-4">
                    <div class="avatar rounded rounded-circle">
                        <img id="my-avatar" src="/files/${(me.image.storageFileName)!}"
                             alt="Аватар">
                    </div>
                </div>
                <div class="col-7">
                    <h2 id="my-first-name">${me.firstName}</h2>
                    <h2 id="my-last-name">${me.lastName}</h2>
                </div>
            </div>
            <div class="row chats-bar-content cursor-default pre-scrollable">
                <ul class="w-100 list-group" id="chats-area">
                </ul>
            </div>
            <div class="row form-group fixed-bottom position-absolute">
                <div class="col-sm-11 m-auto">
                    <div class="input-group input-group-lg rounded-0">
                        <input type="text" class="mr-2 form-control rounded-0" placeholder="Название..."
                               aria-label="Recipient's username" aria-describedby="basic-addon2" id="add-chat-text"
                               onkeydown="if (event.keyCode == 13) { onChatAddClick(); return false; }">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary rounded-0" id="add-chat-btn" type="button"
                                    onclick="onChatAddClick()">Добавить
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8 p-3">
            <div class="row mb-2">
                <div class="w-100 position-absolute fixed-top">
                    <nav class="navbar navbar-light bg-light">
                        <div class="navbar-text h5 ml-auto" id="chat-label"></div>
                        <button class="ml-auto navbar-toggler border-0" type="button" data-toggle="collapse"
                                data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                    </nav>
                    <div class="collapse" id="navbarToggleExternalContent">
                        <div class="bg-light p-4">
                            <div class="row">
                                <div class="col-5"><p class="h5">Участники:</p>
                                    <div id="partners-area">
                                    </div>
                                </div>
                                <div class="col-7 p-0">
                                    <div class="input-group input-group-lg mb-3">
                                        <input type="text" class="form-control rounded-0"
                                               id="add-partner-text"
                                               placeholder="Имя пользователя..."
                                               aria-label="Recipient's username" aria-describedby="basic-addon2"
                                               onkeydown="if (event.keyCode == 13) { onPartnerAddClick(); return false; }">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary rounded-0" type="button"
                                                    onclick="onPartnerAddClick()">Добавить
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
            <div class="w-100 h-100 d-flex">
                <div class="w-100 pre-scrollable mb-10">
                    <ul class="w-100 list-group messages-list cursor-default" id="messages-area">

                    </ul>
                </div>
            </div>

            <div class="mr-1 form-group row fixed-bottom position-absolute">
                <div class="col-sm-9">
                    <textarea rows="1" class="rounded-0 form-control form-control-lg ml-3" id="send-message-text"
                              placeholder="Сообщение..."
                              onkeydown="if (event.keyCode == 13) { onMessageSendClick(); return false; }"></textarea>
                </div>
                <div class="col-sm-3 m-auto w-100">
                    <button class="m-auto w-100 btn btn-lg btn-outline-secondary rounded-0" type="button"
                            onclick="onMessageSendClick()">Отправить
                    </button>
                </div>
            </div>
        </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/sockjs.min.js" language="JavaScript"></script>
<script src="js/stomp.min.js" language="JavaScript"></script>
<script src="js/chat.js" language="JavaScript"></script>
    </div>
</div>
</body>
</html>