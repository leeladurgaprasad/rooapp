<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${context}/css/app.v1.css">
<script src="${context}/css/app.v1.js" type="text/javascript"></script>
<section id="content">
	<section class="vbox">
		<footer class="footer bg-light dker bg-gradient">
			<p>Timeline</p>
		</footer>
		<section class="bg-light lter">
			<section class="hbox stretch">
				<!-- .aside -->
				<aside>
					<section class="vbox">
						<section id="sc-wr" class="scrollable wrapper">

							<div class="timeline">
								<article class="timeline-item active">
									<div class="timeline-caption">
										<div class="panel bg-primary lter no-borders">
											<div class="panel-body">
												<span class="timeline-icon"><i
													class="icon-user time-icon bg-primary"></i></span>
												<div class="pull-left thumb m-r">
													<img
														src="${context}/todo/file/${sessionScope.sessionUser.userId}"
														class="img-thumbnail">
												</div>
												<span class="h3 pull-left m-r-sm">${sessionScope.sessionUser.firstName}
													${sessionScope.sessionUser.lastName}</span>
												<h5>
													<span>Task DeadLine TimeLine</span>
												</h5>
												<!-- <div class="m-t-sm timeline-action">

													<button class="btn btn-sm btn-white">
														<i class="icon-pause"></i> Pause
													</button>
													<button class="btn btn-sm btn-white">
														<i class="icon-ok"></i> Confirm
													</button>
												</div>-->
											</div>
										</div>
									</div>
								</article>
								<input id="next-pixel-id" type="hidden" name="nextPixelFrom" value="${timeLineForm.nextPixelFrom}"/>
								<jsp:include page="timeLinePixels.jsp"/>

							</div>
						</section>
					</section>
				</aside>

			</section>
		</section>
	</section>
</section>
<script src="${context}/js/jquery/jquery-1.4.1.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
         $('#sc-wr').bind('scroll', function() {
                 if($(this).scrollTop() + $(this).innerHeight() >= this.scrollHeight) {
                        timelineAjax();
                 }
             });
             $('#sc-wr').click( function() {
                    timelineAjax();
             });

           function timelineAjax() {
                var nextPixelFrom = $("#next-pixel-id").val();
                                 $('#load-icon-id').removeClass('icon-plus');
                                 $('#load-icon-id').addClass('icon-spinner icon-spin');
                                     $.ajax({
                                     				url : '${context}/todo/task/getTimeLinePixels', // action to be perform
                                     				type : 'POST', //type of posting the data
                                     				data : {
                                     				    nextPixelFrom : nextPixelFrom
                                     				}, // data to set to Action Class
                                     				dataType :   'html',
                                     				success : function(htmlResp) {
                                                         $('#add-more-but').remove();
                                                         $('.timeline').append(htmlResp);
                                                         $("#next-pixel-id").val(parseInt(nextPixelFrom,10)+parseInt("${timeLineForm.numberOfPixelsLoad}",10));
                                     				},

                                     				error : function(xhr, status, errorThrown) {
                                     				    alert("some thing wrong");
                                     				},

                                     			/* // code to run regardless of success or failure
                                     			   complete: function( xhr, status ) {

                                     			       $('#i-spin-div').empty;
                                     			       $('#i-spin-div').append('<span>some thing went wrong</span>');
                                     			   } */

                                     			});
           };
    });

</script>