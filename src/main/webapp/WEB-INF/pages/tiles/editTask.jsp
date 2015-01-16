<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/ckeditor/ckeditor.js"></script>
<section id="content">
	<section class="vbox">
		<header class="header bg-light dker bg-gradient">
			<p>Components</p>
		</header>
		<section class="scrollable wrapper">
            <form:form class="form-horizontal" method="POST" name="updateTaskForm"
            								data-validate="parsley" action="${context}/todo/task/update/${showTaskForm.task.taskId}" commandName="showTaskForm">
			<!-- / .carousel fade -->
			<div class="col-lg-12">
				<div class="col-lg-8">
					<!-- .accordion -->
					<div class="panel-group m-b" id="accordion2">
						<div class="panel">
							<div class="panel-heading">
								<h3>
									<a class="accordion-toggle" data-parent="#accordion2"><strong>#${showTaskForm.task.taskId}
											::: ${showTaskForm.task.taskName}</strong></a>
								</h3>
							</div>
							<div id="collapseOne" class="panel-collapse in">
								<div class="panel-body text-ml">
								<form:textarea placeholder="Task Description" path="task.taskDescription" rows="10" data-trigger="keyup"
                                											 class="form-control"></form:textarea>
								</div>
								<div class="panel-body text-sm">
									<i class="icon-paper-clip"></i>
								</div>
								<div class="panel-body text-sm">

									<div id="MyPillbox" class="pillbox clearfix m-b">

                                        <ul>
                                            <c:if test="${not empty showTaskForm.task.tags}">
                                            <c:forEach var="tag" items="${showTaskForm.task.tags}">
                                            <li class="label bg-dark tag-task-cls">${tag.tagName}</li>
                                            </c:forEach>
                                            </c:if>
                                            <input type="text" placeholder="add a tag">
                                        </ul>
                                    </div>
								</div>
								<div class="panel-body text-lg">
                                    <div class="m-b">
                                        <form:input class="slider form-control" type="text" value="" path="task.percentageCompleted" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${showTaskForm.task.percentageCompleted}" data-slider-selection="after"></form:input>
                                        <i id="_tpc">${showTaskForm.task.percentageCompleted}</i><i>% Completed</i>
                                    </div>

								</div>
								<div class="panel-body text-sm">
                                    <a href="#" id="sub-href" class="btn btn-info btn-sm"><i class="icon-foursquare"></i> Update</a>
                                    <a href="${context}/todo/task/show/${showTaskForm.task.taskId}" class="btn btn-danger btn-sm"><i class="icon-remove-sign"></i> Cancel</a>
                                </div>

							</div>
						</div>

					</div>
				</div>
				<!-- / .accordion -->
                </form:form>



			</div>
			<!-- right tab -->
			<div class="col-lg-4">
				<section class="panel">
					<header class="panel-heading text-right bg-light">
						<ul class="nav nav-tabs pull-left">
							<li class="active"><a href="#messages-2" data-toggle="tab"><i
									class="icon-comments text-default"></i></a></li>
							<li><a href="#profile-2" data-toggle="tab"><i
									class="icon-user text-default"></i>Profile</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><i class="icon-cog text-default"></i>Settings
									<b class="caret"></b></a>
								<ul class="dropdown-menu text-left">
									<li><a href="#dropdown1" data-toggle="tab">@fat</a></li>
									<li><a href="#dropdown2" data-toggle="tab">@mdo</a></li>
								</ul></li>
						</ul>
						<span class="hidden-sm">Activity</span>
					</header>
					<div class="panel-body">
						<div class="tab-content">
							<div class="tab-pane fade active in" id="messages-2">
								<section class="chat-list panel-body">
								    <div id="chat-list-id">
                                    <jsp:include page="showComments.jsp"/>
									</div>
									<!-- chat form -->
									<article class="chat-item" id="chat-form">
										<a class="pull-left thumb-sm avatar"><img
											src="${context}/todo/file/${sessionScope.sessionUser.userId}" class="img-circle"></a>
										<section class="chat-body">
											<form action="#" class="m-b-none">
												<div class="input-group">
													<input id="task-comment-id" type="text" class="form-control"
														placeholder="Say something"> <span
														class="input-group-btn">
														<button id="task-comment-button" class="btn btn-info" type="button">SEND</button>
													</span>
												</div>
											</form>
										</section>
									</article>
								</section>
							</div>
							<div class="tab-pane fade" id="profile-2">profile</div>
							<div class="tab-pane fade" id="dropdown1">dropdown1</div>
							<div class="tab-pane fade" id="dropdown2">dropdown2</div>
						</div>
					</div>

				</section>
			</div>
			</div>
		</section>
	</section>
</section>
<script src="${context}/js/jquery/jquery-1.4.1.min.js"
	type="text/javascript"></script>
<script src="${context}/js/jquery/jquery.timeago.js"
	type="text/javascript"></script>
<script src="${context}/css/app.v1.js"></script>
<script type="text/javascript">
    CKEDITOR.replace( 'task.taskDescription' );
	$(document).ready(function() {

            $('#sub-href').click(function(){
                $('#MyPillbox li').each(
                function() {
                    $(this).append('<input type="hidden" name="task.tags" value="'+$(this).text()+'"/>');

                }
                );
                $('form[name=updateTaskForm]').submit();
            });

	        // enter keyd
            $('#task-comment-id').keydown(function(event){
                if(event.keyCode==13){
                   $('#task-comment-button').trigger('click');
                }
            });



		$("#task-comment-button").click(function() {

			var comment = $("#task-comment-id").val();
			$("#task-comment-id").val("");
            $('#chat-list-id').append("<div id='i-spin-div' class='text-center m-b'> <i class='icon-spinner icon-spin'></i> </div>");
			$.ajax({
				url : '${context}/todo/task/${showTaskForm.task.taskId}/addcomment', // action to be perform
				type : 'POST', //type of posting the data
				data : {
					commentContent : comment
				}, // data to set to Action Class
				dataType :   'html',
				success : function(htmlResp) {

                    $('#chat-list-id').empty();
                    $('#chat-list-id').append(htmlResp);
					$('#task-comment-id').val(jsonResponse[0].comment);
				},

				error : function(xhr, status, errorThrown) {
					$('#i-spin-div').empty;
                    $('#i-spin-div').append('<span>some thing went wrong</span>');
				},

			/* // code to run regardless of success or failure
			   complete: function( xhr, status ) {

			       $('#i-spin-div').empty;
			       $('#i-spin-div').append('<span>some thing went wrong</span>');
			   } */

			});

		});
	});
</script>