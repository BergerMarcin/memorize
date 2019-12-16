<%@ taglib prefix="set" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Memorize</title>

    <%-- Elementy dotyczące treści strony --%>
    <%--    <meta name="description"--%>
    <%--          content="Projekt Spring Transitions zawierający zbiór przekształceń projektów opartych na Spring Boot 2 do nowych wersji"/>--%>
    <%--    <meta name="keywords" content="Spring,boot,java,jsp,project,code"/>--%>
    <meta name="author" content="Marcin Berger, https://github.com/BergerMarcin"/>
    ​
    <%-- Elementy dotyczące wyświetlania --%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    ​
    <%-- Linki do szablonów css trafią tutaj --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">

    <%-- Linki do skryptów js trafią tutaj --%>
<%-- old version    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>--%>
    <%-- Below let give tips by IntelliJ --%>
    <script defer src="/webjars/font-awesome/5.10.1/js/all.min.js"></script>

</head>

<body class="has-navbar-fixed-top">
<header>
    <nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
        <div class="container">
            <div class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item" href="/">Start page</a>
                    <%-- Tutaj pozostałe linki, które chcemy mieć widoczne --%>
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">More actions</a>
                        <div class="navbar-dropdown">
                            <a class="navbar-item">Empty link 1</a>
                            <a class="navbar-item">Empty link 2</a>
                            <a class="navbar-item">Empty link 3</a>
                            <a class="navbar-item">Empty link 4</a>
                        </div>
                    </div>
                </div>

                ​<div class="navbar-center">
                    <div class="content has-text-centered">
                        <c:if test="${pageContext.request.userPrincipal.authenticated}">
                            <p><strong>Hello ${pageContext.request.userPrincipal.name} (of id = ${webAppParamDTO.userId})!</strong></p>
                        </c:if>
                        <c:if test="${not pageContext.request.userPrincipal.authenticated}">
                            <p><strong>Hello! Please login!</strong></p>
                        </c:if>
                        <c:forEach items="${webAppParamDTO.levelDTOList.levelDTOS}" var="levelDTO" varStatus="stat">
                            <c:if test="${stat.count == 1}">
                                <span>DataBase: ${levelDTO.levelShortName}&emsp;&emsp;Levels:&emsp;</span>
                            </c:if>
                            <c:if test="${stat.count > 1}">
                                <span>${levelDTO.levelShortName}&emsp;</span>
<%--                                <form:form action="post" modelAttribute="webAppParamDTO">--%>
<%--                                    <span>--%>
<%--                                        sprawdzanie ${levelDTO.levelSiblingDTOS.get(0).siblingShortName}--%>
<%--                                        <form:select path="levelId" items="${levelDTO.levelSiblingDTOS}"--%>
<%--                                                     itemLabel="siblingShortName" itemValue="siblingId" multiple="true">--%>
<%--                                        </form:select>--%>
<%--                                    <form:select path="level${stat.count - 1}Id" items="${levelDTO.levelSiblingDTOS}"--%>
<%--                                                   itemLabel="siblingShortName" itemValue="siblingId" multiple="true">--%>
<%--                                    </form:select>--%>
<%--                                    </span>--%>
<%--                                </form:form>--%>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <c:if test="${not pageContext.request.userPrincipal.authenticated}">
                                <a class="button is-primary" href="/registration"><strong>Register</strong></a>
                                <a class="button is-success" href="/login"><strong>Login</strong></a>
                            </c:if>
<%--                            <sec:authorize access="not isAuthenticated()">--%>
<%--                                <a class="button is-primary" href="/registration"><strong>Register</strong></a>--%>
<%--                                <a class="button is-success" href="/login"><strong>Login</strong></a>--%>
<%--                            </sec:authorize>--%>
                            <%--  Wyświetla przyciski jeżeli zweryfikowany (zalogowany) jest użytkownik
                                  Jest to weryfikacja ścieżki --%>
                            <c:if test="${pageContext.request.userPrincipal.authenticated}">
                                <a class="button is-primary" href="/data"><strong>Show data</strong></a>
                                <a class="button is-primary" href="/user"><strong>Your account</strong></a>
                                <form method="post" action="/logout">
                                    <button class="button is-link" type="submit">Logout</button>
                                    <set:csrfInput/>
                                </form>
                            </c:if>
<%--                            <sec:authorize access="isAuthenticated()">--%>
<%--                                <a class="button is-primary" href="/user"><strong>Your account</strong></a>--%>
<%--                                <form method="post" action="/logout">--%>
<%--                                    <button class="button is-link" type="submit">Logout</button>--%>
<%--                                    <set:csrfInput/>--%>
<%--                                </form>--%>
<%--                            </sec:authorize>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>
