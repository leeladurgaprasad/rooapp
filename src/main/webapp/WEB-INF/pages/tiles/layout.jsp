<!DOCTYPE html>
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
<link rel="stylesheet" href="${context}/css/app.v1.css">
<link rel="stylesheet" href="${context}/css/font.css" cache="false">
<script src="${context}/js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="${context}/js/jquery/jquery.timeage.js" type="text/javascript"></script>
<!--[if lt IE 9]> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/html5.js" cache="false"></script> <script src="js/ie/fix.js" cache="false"></script> <![endif]-->
</head>
<body>
	<section class="hbox stretch">
		<!-- .aside -->
		<aside class="bg-dark aside-md nav-vertical" id="nav">
			<section class="vbox">
				<header class="nav-bar dker">
					<a class="btn btn-link visible-xs"
						data-toggle="class:nav-off-screen" data-target="body"> <i
						class="icon-reorder"></i>
					</a> <a href="#" class="nav-brand" data-toggle="fullscreen">todo</a> <a
						class="btn btn-link visible-xs" data-toggle="class:show"
						data-target=".nav-user"> <i class="icon-comment-alt"></i>
					</a>
				</header>
				<footer class="footer bg-gradient hidden-xs">
					<a href="${context}/todo/signout"
						class="btn btn-sm btn-link m-r-n-xs pull-right"> <i
						class="icon-off"></i>
					</a> <a href="#nav" data-toggle="class:nav-vertical"
						class="btn btn-sm btn-link m-l-n-sm"> <i class="icon-reorder"></i>
					</a>
				</footer>
				<section>
					<div class="lt nav-user hidden-xs pos-rlt">
						<div class="nav-avatar pos-rlt">
							<a href="#" class="thumb-sm avatar animated rollIn"
								data-toggle="dropdown"> <img src="${context}/todo/file/${sessionScope.sessionUser.userId}" alt=""
								class=""> <span class="caret caret-white"></span>
							</a>
							<ul class="dropdown-menu m-t-sm animated fadeInLeft">
								<span class="arrow top"></span>
								<li><a href="#">Settings</a></li>
								<li><a href="profile.html">Profile</a></li>
								<li><a href="#"> <span
										class="badge bg-danger pull-right">3</span> Notifications
								</a></li>
								<li class="divider"></li>
								<li><a href="docs.html">Help</a></li>
								<li><a href="${context}/todo/signout">Logout</a></li>
							</ul>
							<div class="visible-xs m-t m-b">
								<a href="#" class="h3">${sessionScope.sessionUser.userName}</a>
								<p>
									<i class="icon-map-marker"></i>${sessionScope.sessionUser.level}
								</p>
							</div>
						</div>
						<div class="nav-msg">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <b
								class="badge badge-white count-n">2</b>
							</a>
							<section
								class="dropdown-menu m-l-sm pull-left animated fadeInRight">
								<div class="arrow left"></div>
								<section class="panel bg-white">
									<header class="panel-heading">
										<strong>You have <span class="count-n">2</span>
											notifications
										</strong>
									</header>
									<div class="list-group">
										<a href="#" class="media list-group-item"> <span
											class="pull-left thumb-sm"> <img
												src="${context}/images/avatar.jpg" alt="John said" class="img-circle">
										</span> <span class="media-body block m-b-none"> Use awesome
												animate.css<br> <small class="text-muted">28
													Aug 13</small>
										</span>
										</a> <a href="#" class="media list-group-item"> <span
											class="media-body block m-b-none"> 1.0 initial
												released<br> <small class="text-muted">27 Aug
													13</small>
										</span>
										</a>
									</div>
									<footer class="panel-footer text-sm">
										<a href="#" class="pull-right"><i class="icon-cog"></i></a> <a
											href="#">See all the notifications</a>
									</footer>
								</section>
							</section>
						</div>
					</div>
					<nav class="nav-primary hidden-xs">
						<ul class="nav">
							<li><a href="index.html"> <i class="icon-eye-open"></i>
									<span>Discover</span>
							</a></li>
							<li class="dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown"> <i
									class="icon-beaker"></i> <span>UI kit</span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="buttons.html">Buttons</a></li>
									<li><a href="icons.html"> <b class="badge pull-right">302</b>Icons
									</a></li>
									<li><a href="grid.html">Grid</a></li>
									<li><a href="widgets.html"> <b
											class="badge bg-primary pull-right">8</b>Widgets
									</a></li>
									<li><a href="components.html"> <b
											class="badge pull-right">18</b>Components
									</a></li>
									<li><a href="list.html">List groups</a></li>
									<li><a href="table.html">Table</a></li>
									<li><a href="form.html">Form</a></li>
									<li><a href="chart.html">Chart</a></li>
									<li><a href="calendar.html">Fullcalendar</a></li>
									<li><a href="profile.html">Profile</a></li>
									<li><a href="signin.html">Signin page</a></li>
									<li><a href="signup.html">Signup page</a></li>
									<li><a href="404.html">404 page</a></li>
								</ul></li>
							<li><a href="mail.html"> <b
									class="badge bg-primary pull-right">3</b> <i
									class="icon-envelope-alt"></i> <span>Mail</span>
							</a></li>
							<li class="active"><a href="tasks.html"> <i
									class="icon-tasks"></i> <span>Tasks</span>
							</a></li>
                            <li class="dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown"> <i
									class="icon-cogs"></i> <span>Manage</span>
							</a>
								<ul class="dropdown-menu">
                                    <li><a href="${context}/todo/admin/addtask">Add Task</a></li>
									<li><a href="${context}/todo/admin/adduser">Add User</a></li>
									<li><a href="${context}/todo/task/show/1">Show Task</a></li>

								</ul></li>
							<li><a href="notes.html"> <i class="icon-pencil"></i> <span>Notes</span>
							</a></li>
							<li><a href="${context}/todo/task/timeline"> <i class="icon-time"></i> <span>Timeline</span>
							</a></li>
						</ul>
					</nav>
					<!-- note -->
					<div
						class="bg-dark lter wrapper hidden-vertical animated fadeInUp text-sm">
						<a href="#" data-dismiss="alert"
							class="pull-right m-r-n-sm m-t-n-sm"><i
							class="icon-close icon-remove "></i></a> Hey, this is an app
						built by <a href="" target="_blank"><strong>Prasad</strong></a>,
						to manage your todo use our todo!
					</div>
					<!-- / note -->
				</section>
			</section>
		</aside>
		<!-- /.aside -->
		<!-- .vbox -->
		<tiles:insertAttribute name="body" />
		<!-- /.vbox -->
	</section>
	<script src="${context}/css/app.v1.js"></script>
	<!-- Bootstrap -->
	<!-- App -->
	<!-- Tasks -->
	<script src="${context}/js/apps/tasks.js" cache="false"></script>
</body>
</html>