<#include "standart_page.ftl" />
<@standardPage title="Крутой чат">
<div id="username-page" class="m-auto">
    <div class="reg-page-container">
        <h1 class="title">Введите данные для регистрации</h1>


        <#if error??>
                <div class="alert alert-danger" role="alert">${error}</div>
        </#if>


        <form id="usernameForm" name="usernameForm" action="/sign-up" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="login" placeholder="Логин" value="${login!}" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="Пароль" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="password" name="confirmPassword" placeholder="Повторите пароль" autocomplete="off"
                       class="form-control"/>
            </div>
            <div class="form-group">
                <input type="email" name="email" placeholder="eMail" autocomplete="off" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="text" name="firstName" placeholder="Имя" autocomplete="off"
                       class="form-control"/>
            </div>
            <div class="form-group">
                <input type="text" name="lastName" placeholder="Фамилия" autocomplete="off" class="form-control"/>
            </div>
            <div class=" form-group custom-file">
                <input type="file" name="image" class="custom-file-input">
                <label class="custom-file-label" for="customFile">Выберите аватарку</label>
            </div>
            <button type="submit" class="btn btn-primary">Регистрация</button>
            <a href="/sign-in">Вход</a>

        </form>
    </div>
</div>
</@standardPage>