<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="odd" value="false" />
<c:forEach var="timeLine" items="${timeLineForm.timeLineList}"
    varStatus="stat">
    <c:choose>
        <c:when test="${odd == 'false'}">
            <article class="timeline-item">
                <div class="timeline-caption">
                    <div class="panel">
                        <div class="panel-body">
                            <span class="arrow left"></span> <span
                                class="timeline-icon"><i
                                class="icon-phone time-icon bg-primary"></i></span> <span
                                class="timeline-date"><fmt:formatDate
                                    value="${timeLine.taskDeadLineDate}"
                                    pattern="dd MMM yy" /></span>
                            <h5>
                                <span>task assigned by - <a
                                    href="${context}/todo/user/${timeLine.taskOwner.userId}">${timeLine.taskOwner.userName}</a></span>
                                <a href="${context}/todo/task/show/${timeLine.taskId}">${timeLine.taskName}</a>
                            </h5>
                            <p>
                                <c:out value="${timeLine.taskDescription}" />
                            </p>
                        </div>
                    </div>
                </div>
            </article>
            <c:set var="odd" value="true" />
        </c:when>

        <c:when test="${odd == 'true'}">
            <article class="timeline-item alt">
                <div class="timeline-caption">
                    <div class="panel">
                        <div class="panel-body">
                            <span class="arrow right"></span> <span
                                class="timeline-icon"> <i
                                class="icon-male time-icon bg-success"></i>
                            </span> <span class="timeline-date"><fmt:formatDate
                                    value="${timeLine.taskDeadLineDate}"
                                    pattern="dd MMM yy" /></span>
                            <h5>
                            <a href="${context}/todo/task/show/${timeLine.taskId}">${timeLine.taskName}</a>
                            <span>task assigned by - <a href="${context}/todo/user/${timeLine.taskOwner.userId}">${timeLine.taskOwner.userName}</a></span>

                            </h5>
                            <p>
                                <c:out value="${timeLine.taskDescription}" />
                            </p>
                        </div>
                    </div>
                </div>
            </article>
            <c:set var="odd" value="false" />
        </c:when>
    </c:choose>

</c:forEach>
<div id="add-more-but" class="timeline-footer">
    <a href="#"><i id="load-icon-id"
        class="icon-plus time-icon inline-block bg-dark"></i></a>
</div>