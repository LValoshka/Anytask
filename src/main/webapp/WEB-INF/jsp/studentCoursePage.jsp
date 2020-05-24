<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${course.courseName}</title>
    <jsp:include page="common.jsp"></jsp:include>
    <style>
        .table tr {
            cursor: pointer;
        }

        .hiddenRow {
            padding: 0 4px !important;
            background-color: #eeeeee;
            font-size: 13px;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="row mt-3">
        <div class="col-lg-3 col-md-12">
            <div class="card">
                <h5 class="card-header">${course.courseName}</h5>
                <div class="card-body">
                    <p class="card-text"><b>Teacher</b>: ${course.teacher.name} ${course.teacher.surname} </p>
                </div>
            </div>
            <div class="card" style="margin-top: 20px">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active" id="list-tasks-list" data-toggle="list"
                       href="#tasks" role="tab" aria-controls="tasks">Tasks</a>
                    <a class="list-group-item list-group-item-action" id="list-desc-list" data-toggle="list"
                       href="#desc" role="tab" aria-controls="desc">Description</a>
                </div>
            </div>
        </div>

        <div class="col-lg-9 col-md-12">
            <div class="card">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade" id="desc" role="tabpanel"
                         aria-labelledby="list-desc-list">
                        <p class="card-header"><b>Description</b></p>
                        <div class="card-body">
                            <p>${course.courseDescription}</p>
                        </div>
                    </div>
                    <div class="tab-pane fade show active" id="tasks" role="tabpanel" aria-labelledby="list-tasks-list">
                        <p class="card-header">
                            <b>Tasks</b>
                        </p>

                        <table class="table display table-condensed" id="myTable" style="border-collapse:collapse;">
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty taskStatusList}">
                                    <c:forEach items="${taskStatusList}" var="listItem">
                                        <tr data-toggle="collapse" data-target="#id${listItem.id}"
                                            class="accordion-toggle">
                                            <td>${listItem.task.taskName}</td>
                                            <td>${listItem.mark}</td>
                                            <td><span class="badge badge-success">${listItem.label}</span></td>
                                            <td>${listItem.task.dueDate}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty listItem.startDate}">
                                                        ${listItem.startDate}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form method="get">
                                                            <a href="/${listItem.task.course.id}/task/${listItem.task.id}/setStartDate">
                                                                <button type="button" value="${listItem.id}"
                                                                        name="course" class="badge badge-primary">
                                                                    Set start date
                                                                </button>
                                                            </a>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty listItem.endDate}">
                                                        ${listItem.endDate}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form method="get">
                                                            <a href="/${listItem.task.course.id}/task/${listItem.task.id}/setEndDate">
                                                                <button type="button" value="${listItem.id}"
                                                                        name="course" class="badge badge-primary">
                                                                    Set end date
                                                                </button>
                                                            </a>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                    <c:url value="/${listItem.task.course.id}/task/${listItem.task.id}/upload?${_csrf.parameterName}=${_csrf.token}"
                                                           var="var"/>
                                                <form method="post"
                                                      action="${var}"
                                                      enctype="multipart/form-data">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                           value="${_csrf.token}"/>
                                                    <input type="file" name="file"/>
                                                    <br/><br/>
                                                    <button type="submit" value="Submit" class="badge badge-primary">
                                                        Submit
                                                    </button>
                                                </form>
                                        </tr>
                                        <tr>
                                            <td colspan="5" class="hiddenRow">
                                                <div id="id${listItem.id}" class="accordion-body collapse">
                                                    <div class="card card-body">
                                                        <p>${listItem.task.taskDescription}</p>
                                                        <c:choose>
                                                            <c:when test="${not empty listItem.url}">
                                                                Your solution, ${user}:
                                                                <img src="${listItem.url}" alt="not found" height="400"
                                                                     width="500">
                                                            </c:when>
                                                            <c:otherwise>
                                                                No solution yet.
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-primary">
                                        There're no tasks=)
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('.accordian-body').on('show.bs.collapse', function () {
        $(this).closest("table")
            .find(".collapse.in")
            .not(this)
            .collapse('toggle')
    })
</script>
</body>
</html>
