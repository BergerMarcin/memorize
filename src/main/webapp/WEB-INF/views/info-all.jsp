<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<section class="hero is-bold is-medium">
    <div class="container">
        <div class="hero-body">
            <h1 class="title">Notes</h1>

            <table>
            <tr>
                <th>No.</th>
                <th>ID</th>
                <th>Information</th>
                <th>Operations</th>
            </tr>
            <c:forEach items="${infos}" var="info" varStatus="stat">
                <tr>
                    <td>${stat.count}</td>
                    <td>${info.id}</td>
                    <c:choose>
                        <c:when test="${info.type == 1}">
                            ${info.note.textHtml}
                        </c:when>
                        <c:when test="${info.type == 2}">
                            <pre>${info.picture.name} {info.picture.description}</pre>
                            ${info.pictureAdress}
                        </c:when>
                        <c:when test="${info.type == 3}">
                            <pre>${info.example.name} {info.example.description}</pre>
                            <ul>
                                <c:forEach items="${info.example.exampleinfos}" var="exampleinfo">
                                    <li>${exampleinfo.htmlinfo}&emsp;(${exampleinfo.htmlDescr})</li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>FAULT. Unknown type of information</c:otherwise>
                    </c:choose>
                    </td>
                        <%-- hyperlinks for update, add-up, add-down and delete (hrefs to GET of InfoController methods and then to proper viewers) --%>
                    <td>
                        <c:url value="/infos/update" var="updateURL">
                            <c:param name="id" value="${info.id}"/>
                        </c:url>
                        <c:url value="/infos/addup" var="addupURL">
                            <c:param name="id" value="${info.id}"/>
                        </c:url>
                        <c:url value="/infos/adddown" var="adddownURL">
                            <c:param name="id" value="${info.id}"/>
                        </c:url>
                        <c:url value="/infos/delete" var="deleteURL">
                            <c:param name="id" value="${info.id}"/>
                        </c:url>
                        <a href="${updateURL}">Update</a>
                        <a href="${addupURL}">Add-up</a>
                        <a href="${adddownURL}">Add-down</a>
                        <a href="${deleteURL}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </table>

        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/footer.jsp" %>
