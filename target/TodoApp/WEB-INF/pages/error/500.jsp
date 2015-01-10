<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8">
<title>Web Application | todo</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${context}/css/app.v1.css">
<link rel="stylesheet" href="${context}/css/font.css" cache="false">
<!--[if lt IE 9]> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/html5.js" cache="false"></script> <script src="js/ie/fix.js" cache="false"></script> <![endif]-->
</head>
<body>
	<section id="content">
		<div class="row m-n">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="text-center m-b-lg">
					<h1 class="h text-white animated bounceInDown">500</h1>
				</div>
				<div class="test-center m-b-lg">
					<h3 class="text-bb animated bounceInDown">we are sorry for the inconvenience, but some thing went seriorsly wrong
		our side. we are working so hard to fix it.</h3>
				</div>
				<div class="list-group m-b-sm bg-white m-b-lg">
					<a href="${context}/todo/signin" class="list-group-item"> <i
						class="icon-chevron-right"></i> <i class="icon-home"></i> Goto
						homepage
					</a> <a href="form.html" class="list-group-item"> <i
						class="icon-chevron-right"></i> <i class="icon-question"></i> Send
						us a tip
					</a> <a href="#" class="list-group-item"> <i
						class="icon-chevron-right"></i> <span class="badge">+91 970-36-88-224</span>
						<i class="icon-phone"></i> Call us
					</a>
				</div>
			</div>
		</div>
	</section>
	<!-- footer -->
	<footer id="footer">
		<div class="text-center padder clearfix">
			<p>
				<small>Dev by Leeladurga Prasad Gunti - leeladurgaprasad@aol.com<br>&copy;
					2015
				</small>
			</p>
		</div>
	</footer>
	<!-- / footer -->
	<script src="${context}/css/app.v1.js"></script>
	<!-- Bootstrap -->
	<!-- app -->
</body>
</html>