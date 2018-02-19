<#include "standart_page.ftl" />
<@standardPage title="Крутой чат">

<div id="username-page" class="m-auto">
    <div class="username-page-container">
        <h1 class="title">Type your username</h1>
        <#if error??>
            <div class="alert alert-danger" role="alert">${error}</div>
        </#if>
        <form id="usernameForm" name="usernameForm" action="/sign-in" method="post">
            <div class="form-group">
                <input type="text" name="login" placeholder="Логин" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="Пароль" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="remember-me">
                    <label class="form-check-label">
                        Запомнить меня
                    </label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Вход</button>
            <a href="/sign-up">Регистрация</a>
        </form>
    </div>
</div>
</@standardPage>
