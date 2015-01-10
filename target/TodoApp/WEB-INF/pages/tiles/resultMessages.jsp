<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:if test="${not empty resultMessages}">
    <c:forEach var="resultMessage" items="${resultMessages}" varStatus="stat">
        <c:if test="${resultMessage.type eq 'ERROR'}">
            <c:set var="errorMessages" value="${stat.first ? '' : errorMessages} <i class='icon-ban-circle'></i> ${resultMessage.content}<br/>"/>
        </c:if>
        <c:if test="${resultMessage.type eq 'WARN'}">
            <c:set var="warnMessages" value="${stat.first ? '' : warnMessages} <i class='icon-bell-alt'></i></i> ${resultMessage.content}<br/>"/>
        </c:if>
        <c:if test="${resultMessage.type eq 'INFO'}">
            <c:set var="infoMessages" value="${stat.first ? '' : infoMessages} <i class='icon-info-sign'></i> ${resultMessage.content}<br/>"/>
        </c:if>
        <c:if test="${resultMessage.type eq 'SUCCESS'}">
            <c:set var="successMessages" value="${stat.first ? '' : successMessages} <i class='icon-ok-sign'></i> ${resultMessage.content}<br/>"/>
        </c:if>
    </c:forEach>
</c:if>
<c:if test="${not empty errorMessages}">
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
        </button>
        ${errorMessages}
    </div>
</c:if>
<c:if test="${not empty warnMessages}">
    <div class="alert alert-warning">
        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
        </button>
        ${warnMessages}
    </div>
</c:if>
<c:if test="${not empty infoMessages}">
    <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
        </button>
        ${infoMessages}
    </div>
</c:if>
<c:if test="${not empty successMessages}">
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
        </button>
        ${successMessages}
    </div>
</c:if>
