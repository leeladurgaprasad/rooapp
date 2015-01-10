<!DOCTYPE html>
<%@ page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${context}/css/app.v2.css">
<link rel="stylesheet" href="${context}/css/font.css" cache="false">
<link rel="stylesheet" href="${context}/css/app.v1.css">
<!--[if lt IE 9]> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/html5.js" cache="false"></script> <script src="js/ie/fix.js" cache="false"></script> <![endif]-->
</head>
<body>
	<div class="modal in" id="ajaxModals" style="display: block;"
    		aria-hidden="false">
    		<div class="modal-over">
    			<div class="modal-center animated flipInX"
    				style="width: 300px; margin: -30px 0 0 -150px;">
    				<div class="pull-left thumb m-r">
    					<img src="${context}/todo/file/${signInForm.userId}"
    						class="img-thumbnail">
    				</div>
    				<div class="clear">
    					<p class="text-white">${signInForm.userName}</p>

    					<form:form action="${context}/todo/signin" method="post" commandName="signInForm">
    					<form:hidden path="userName" />
    					<form:hidden path="keepMeSignIn" value="true"/>
    					<form:hidden path="fromPage" value="LoginUserPage"/>
    					<form:hidden path="continuePage" />
    					<div class="input-group input-s">
    						<form:password path="password" class="form-control text-sm"
    							placeholder="Enter pwd to continue" /> <span
    							class="input-group-btn">
    							<button type="submit" class="btn btn-primary" type="button"><i class="fa fa-arrow-right"></i>
    							</button>
    						</span>
    					</div>
    					<c:set var="loginUrl" value="${context}/todo/signin?fromPage=LoginUserPage"/>
    					<c:if test="${not empty signInForm.continuePage}">
    					    <c:url var="loginUrl" value="${loginUrl}&continuePage=${signInForm.continuePage}"/>
    					</c:if>
    					<a href="${loginUrl}"/>
    					<p class="text-blue" align="right">Not ${signInForm.userName} ?</p>
    					</a>
                    </form:form>
    				</div>
    			</div>
    		</div>
    	</div>
    	<div class="modal-backdrop  in"></div>
</body>
</html>