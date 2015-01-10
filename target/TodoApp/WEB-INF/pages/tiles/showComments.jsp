<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:if test="${not empty showTaskForm.task.comments}">
<c:forEach var="comment" items="${showTaskForm.task.comments}"
    varStatus="stat">
    <c:set var="arrClass" value="arrow left"/>
    <c:if test="${sessionScope.sessionUser.userId == comment.commentedBy.profilePicId}">
    <c:set var="arrClass" value="arrow right"/>
    </c:if>

    <c:if test="${arrClass == 'arrow left'}">
    <article id="chat-id-1" class="chat-item left">
        <a href="#" class="pull-left thumb-sm avatar"><img
            src="${context}/todo/file/${comment.commentedBy.profilePicId}" class="img-circle"></a>
        <section class="chat-body">
            <div class="panel text-sm m-b-none">
                <div class="panel-body">
                    <span class="arrow left"></span>${comment.comment}
                </div>
            </div>
            <small class="text-muted"><i class="icon-user"></i><a
                href="${context}/todo/user/${comment.commentedBy.userId}">${comment.commentedBy.userName}</a>
                <span class="text-flr">${comment.commentedTime}</span></small>
        </section>
    </article>
    </c:if>
    <c:if test="${arrClass == 'arrow right'}">
    <article id="chat-id-1" class="chat-item right">
        <a href="#" class="pull-right thumb-sm avatar"><img
            src="${context}/todo/file/${comment.commentedBy.profilePicId}" class="img-circle"></a> <section class="chat-body">
             <div class="panel text-sm m-b-none">
              <div class="panel-body">
              <span class="arrow right"></span>${comment.comment}
               </div>
               </div>
               <small class="text-muted"><i class="icon-user"></i><a
                               href="${context}/todo/user/${comment.commentedBy.userId}">${comment.commentedBy.userName}</a>
                               <span class="text-flr">${comment.commentedTime}</span></small>
               </section>
               </article>

    </c:if>
</c:forEach>
</c:if>