<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<div style="font-size: 15px">
<i class="icon-tags" style="font-size: 12px"></i>
<c:if test="${not empty showTaskForm.task.tags}">
<c:forEach var="tag" items="${showTaskForm.task.tags}">
<a href="${context}/todo/search?tag=${tag.tagName}">
<span class="label bg-light">${tag.tagName}</span>
</a>
</c:forEach>
</c:if>
</div>
