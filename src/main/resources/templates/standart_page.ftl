<#macro standardPage title="">
<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>${title}</title>
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
            <li class="nav-item ml-sm-auto active">
                <a class="nav-link" href="./sign-in">Вход</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container shadow">
    <div class="row page-content">
    <#nested/>
    </div>
</div>
</body>
</html>
</#macro>