<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" %>
<html>
<head>
    <title>Web full stack</title>
    <link rel="shortcut icon" href="/resources/images/logo.png" type="image/png">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resources/css/materialize.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <script type="text/javascript" src="/resources/js/main.js"></script>

    <style>
        body {
            background-image: url(/resources/images/back.gif); /* Путь к фоновому изображению */
        }
    </style>

    <script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/resources/js/materialize.js"></script>
</head>
<body>

<div class="row">
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper teal darken-1">
                <a href="/" class="brand-logo center">WEB full stack</a>
            </div>
        </nav>
    </div>
</div>
<div class="row">
    <div class="col s12 m6 l3 xl2 left-panel-col">
        <div class="card login-card-panel teal lighten-5 z-depth-4">
            <c:choose>
                <c:when test="${account eq null}">
                    <div class="card-content login-card-content">
                        <form id="login-form" method="post" action="/" class="form-margin">
                            <div class="input-field">
                                <i class="icon-margin-top material-icons prefix">account_box</i>
                                <input type="text" class="validate login-input" name="login" placeholder="Логин">
                            </div>
                            <div class="input-field">
                                <i class="icon-margin-top material-icons prefix">lock</i>
                                <input type="password" class="validate login-input" name="password"
                                       placeholder="Пароль">
                            </div>
                            <div>
                                <button class="waves-effect waves-teal btn login-button" type="submit">Войти</button>
                            </div>
                            <div align="right" class="remember-me-margin">
                                <input type="checkbox" id="remember-me" name="remember-me">
                                <label for="remember-me" class="remember-me">Запомнить меня</label>
                            </div>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                    <div class="card-action green-action padding-card-action" align="center">
                        <a href="#registration" class="modal-trigger">Регистрация</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <h4>${account.login}</h4>
                        <label>Тип профиля: ${account.accountType.type}</label>
                    </div>
                    <div>
                        <a href="/logout" class="red lighten-2 waves-effect red-waves btn">Выйти</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="col s12 m6 l9 xl10">

    </div>

    <div id="registration" class="modal blue-grey lighten-5 modal-custom">
        <div class="modal-close close-area">
            <i class="material-icons prefix close-icon">cancel</i>
        </div>
        <div class="modal-content">
            <h4>Регистрация</h4>
            <form method="post" action="/" class="form-margin">
                <div class="input-field">
                    <input id="login" type="text" class="validate" name="login">
                    <label for="login">Логин*</label>
                </div>
                <div class="input-field">
                    <input id="e-mail" type="text" class="validate" name="e_mail">
                    <label for="e-mail">E-mail*</label>
                </div>
                <div class="input-field">
                    <input id="password" type="password" class="validate" name="password">
                    <label for="password">Пароль*</label>
                </div>
                <div class="input-field">
                    <input id="repeat" type="password" class="validate" name="repeat-pass">
                    <label for="repeat">Подтверждение пароля*</label>
                </div>
            </form>
        </div>
        <div class="modal-footer blue-grey lighten-5">
            <a href="#" class="modal-action modal-close waves-effect btn-flat">Подтвердить</a>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.modal').modal({
            dismissible: false,
            opacity: .9,
            inDuration: 500,
            outDuration: 500
        });
    });
</script>
</body>
</html>
