<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<h5>
<i class="icon-tags"></i>
<c:if test="${not empty showTaskForm.task.tags}">
<c:forEach var="tag" items="${showTaskForm.task.tags}">
<a href="${context}/todo/search?tag=${tag.tagName}">
<span class="label bg-light">${tag.tagName}</span>
</a>
</c:forEach>
</c:if>
</h5>
