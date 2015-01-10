<!DOCTYPE html>
<%@page contentType="text/html;charset=UTF-8"%>
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

				<p class=" bg-dark lter text-center wrapper ">
					<em class="h4">todo</em><br>
				</p>
				<div class="pull-left thumb m-r">
					<img src="${context}/todo/file/${signInForm.userId}" class="img-thumbnail">
				</div>



				<div class="clear">





					<form:form action="${context}/todo/signin" method="post"
						commandName="signInForm">
						<form:hidden path="keepMeSignIn" value="true" />
						<form:hidden path="continuePage" />
						<form:hidden path="fromPage" value="LoginUserPage" />
						<div class="input-group-u input-s">
							<form:input path="userName" class="form-control text-sm"
								placeholder="User Name" />
						</div>
						<p class="text-red">
							<span class="input-group"><form:errors path="userName"
									cssClass="error-v2" type="span" /> </span>
						</p>
						<div class="input-group input-s">
							<form:password path="password" class="form-control text-sm"
								placeholder="Password" />
							<span class="input-group-btn">
								<button type="submit" class="btn btn-primary" type="button">
									<i class="fa fa-arrow-right"></i>
								</button>
							</span>

						</div>
						<p class="text-red">
							<span class="input-group"><form:errors path="password"
									cssClass="error-v2" type="span" /> <form:errors
									cssClass="error-v2" /> </span>
						</p>
						<a href="${context}/todo/signin?targetPage=LoginUserPage">
							<p class="text-blue" align="right">Forgot Password ?</p>
						</a>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop  in"></div>
</body>
</html>