<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/ckeditor/ckeditor.js"></script>
<section id="content">
			<section class="vbox">
            <header class="header bg-light dker"><p>Add Task</p></header>
				<section class="scrollable wrapper">
					<div class="tab-content">
						<section class="tab-pane active" id="basic">
                        <jsp:include page="resultMessages.jsp"/>
						<!--<c:if test="${not empty successMessage}">

							<div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
                            </button>
                            <i class="icon-ok-sign"></i><strong>Thats Great!</strong> <c:out value="${successMessage}"/>
                            </div>

						</c:if>-->
                            <spring:bind path="addTaskForm.*">
							<form:form class="form-horizontal" method="POST"
								data-validate="parsley" action="${context}/todo/admin/addtask" commandName="addTaskForm">
								<div class="form-group">
									<label class="col-sm-3 control-label">Task Name</label>
									<div class="col-sm-4">
										<form:input type="text" name="taskName" path="taskName" placeholder="Task Name"
											data-required="true" class="bg-focus form-control"/>
									</div>
								</div>
                                <div class="form-group">
									<label class="col-sm-3 control-label">Task Description</label>
									<div class="col-sm-5">
										<form:textarea placeholder="Task Description" path="taskDescription" rows="10" data-trigger="keyup"
											data-rangelength="[20,2000]" class="form-control"></form:textarea>
									</div>
								</div>
                                <div class="form-group">
                                	<label class="col-sm-3 control-label">Add a tag</label>
                                        <div class="col-lg-9">
                                        <div class="m-b" id="tag-id"><form:input type="hidden" id="select2-tags" style="width:42%;max-width:100%;" value="java" path="tags"/>
                                        </div>
                                    </div>
                                </div>

								<div class="form-group">
									<label class="col-sm-3 control-label">Assign to</label>
									<div class="col-sm-4">
										<form:select value="${addTaskForm.users[0].userId}" path="users[0].userId" class="form-control">
										    <form:option value="0" label="--- Select ---"/>
											<form:options items="${addTaskForm.usersList}"/>
										</form:select>
									</div>
								</div>
								<!-- <div class="form-group">
									<label class="col-sm-3 control-label">Registered</label>
									<div class="col-lg-9">
										<input type="text" class="combodate form-control"
											data-format="DD-MM-YYYY HH:mm"
											data-template="D MMM YYYY - HH : mm" name="datetime"
											value="21-12-2012 20:30">
									</div>
								</div> -->

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Task Dead Line Date</label>
                                    <div class="col-lg-5">
                                    <div class="m-b"><form:input type="text" class="input-sm input-s datepicker form-control" size="16"
                                                                                             value="${taskDeadLineDate}" path="taskDeadLineDate" data-date-format="dd-mm-yyyy"/>
                                                                    </div>
                                    </div>
                                </div>

								<div class="form-group">
									<div class="col-sm-4 col-sm-offset-3">
										<button id="submit-but" type="submit" class="btn btn-primary">Assin Task</button>
									</div>
								</div>
							</form:form>
							</spring:bind>
						</section>

					</div>
				</section>
			</section>
		</section>
<script src="${context}/js/jquery/jquery-1.4.1.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
    CKEDITOR.replace( 'taskDescription' );
	});

</script>
