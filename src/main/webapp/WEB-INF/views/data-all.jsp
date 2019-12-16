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
                <th>Pos.No.</th>
                <th>Information/Data</th>
                <th>Operations</th>
            </tr>
            <c:forEach items="${datas}" var="data" varStatus="stat">
                <tr>
                    <td>${stat.count}</td>
                    <td>${data.posNo}</td>
                    <c:choose>
                        <c:when test="${data.type == 1}">
                            ${data.noteDTO.textHtml}
                        </c:when>
                        <c:when test="${data.type == 2}">
                            <pre>${data.pictureDTO.name}</pre>
                            <pre>${data.pictureDTO.description}</pre>
<%-- TODO: view bitmap on screen - demands data: ${pictureDTO.filename}, ${pictureDTO.contentType}, ${pictureDTO.fileData}--%>
                        </c:when>
                        <c:when test="${data.type == 3}">
                            <pre>${data.example.name}&emsp;${data.example.description}</pre>
                            <ul>
                                <c:forEach items="${data.example.exampleLines}" var="exampleLine">
                                    <li>${exampleLine.htmlLine}&emsp;(${exampleLine.htmlDescr})</li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>FAULT. Unknown type of data/information</c:otherwise>
                    </c:choose>
                    </td>
                        <%-- hyperlinks for update, add-up, add-down and delete (hrefs to GET of DataController methods and then to proper viewers) --%>
                    <td>
                        <c:url value="/data/update" var="updateURL">
                            <c:param name="id" value="${data.id}"/>
                        </c:url>
                        <c:url value="/data/addup" var="addupURL">
                            <c:param name="id" value="${data.id}"/>
                        </c:url>
                        <c:url value="/data/adddown" var="adddownURL">
                            <c:param name="id" value="${data.id}"/>
                        </c:url>
                        <c:url value="/data/delete" var="deleteURL">
                            <c:param name="id" value="${data.id}"/>
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
