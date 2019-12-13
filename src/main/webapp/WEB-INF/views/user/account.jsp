<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<section class="hero is-bold is-medium">
    <div class="container">
        <div class="hero-body">
            <h1 class="title">
                Welcome User
            </h1>
            <h2 class="subtitle">
                Hello user (of username: ${pageContext.request.userPrincipal.name},
                login?: ${pageContext.request.userPrincipal.authenticated})!
            </h2>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/footer.jsp" %>
