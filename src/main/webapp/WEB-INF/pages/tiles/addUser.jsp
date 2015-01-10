<jsp:directive.include file="/WEB-INF/pages/tiles/taglibs.jsp" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<section id="content">
	<section class="vbox">
		<header class="header bg-light dker">
			<p>Add User</p>
		</header>
		<section class="scrollable wrapper">
			<div class="tab-content">
				<section class="tab-pane active" id="basic">
					<jsp:include page="resultMessages.jsp" />

					<!--
						<c:if test="${not empty resultMessages}">
                            <c:forEach var="resultMessage" items="${resultMessages}" varStatus="stat">
                                <c:if test="${resultMessage.type eq 'SUCCESS'}">
                                    <c:set var="successMessages" value="${stat.first ? '' : successMessages} <i class='icon-ok-sign'></i> ${resultMessage.content}<br/>"/>
                                </c:if>
                            </c:forEach>
						</c:if>

						<c:if test="${not empty successMessages}">
                            <div class="alert alert-success">
                                <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
                                </button>
                                ${successMessages}
                            </div>
                        </c:if>
                        -->
					<!--<c:if test="${not empty successMessage}">

                        							<div class="alert alert-success">
                                                    <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i>
                                                    </button>
                                                    <i class="icon-ok-sign"></i><strong>Thats Great!</strong> <c:out value="${successMessage}"/><br>
                                                    <i class="icon-ok-sign"></i><strong>Thats Great!</strong> <c:out value="${successMessage}"/>
                                                    </div>

                        						</c:if>-->

					<form:form class="form-horizontal" method="POST" enctype="multipart/form-data"
						data-validate="parsley" action="${context}/todo/admin/adduser"
						commandName="addUserForm">
						<div class="form-group m-t-lg">
							<label class="col-sm-3 control-label">Photo</label>
							<div class="col-sm-4 media m-t-none">
							    <img id="imgprvw" alt="Profile Photo" height="72" width="72"/>
							    <input type="file" class="btn btn-sm btn-info m-b-sm" name="profileImage" id="photo-upload-in" onchange="showimagepreview(this)" />
							    <button class="btn btn-sm btn-danger m-b-sm" id="remove-photo-bt">
							    <i class="icon-remove"></i>
							    </button>
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Email</label>
							<div class="col-sm-4">
								<form:input type="text" name="email" path="email"
									placeholder="test@example.com" data-required="true"
									class="bg-focus form-control" data-type="email" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Password</label>
							<div class="col-sm-4">
								<form:input type="password" name="password" path="password"
									placeholder="Password" data-required="true"
									class="bg-focus form-control" />
								<div class="line line-dashed m-t-lg"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Username</label>
							<div class="col-sm-4">
								<form:input type="text" name="username" path="userName"
									placeholder="Username" data-required="true"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">First Name</label>
							<div class="col-sm-4">
								<form:input type="text" name="firstName" path="firstName"
									placeholder="First Name" data-required="true"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Last Name</label>
							<div class="col-sm-4">
								<form:input type="text" name="lastName" path="lastName"
									placeholder="Last Name" data-required="true"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Account</label>
							<div class="col-sm-4">
								<form:select value="level" name="level" path="level"
									class="form-control">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${addUserForm.levels}" />
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
							<label class="col-sm-3 control-label">Profile</label>
							<div class="col-sm-5">
								<form:textarea placeholder="Profile" path="aboutUser" rows="5"
									data-trigger="keyup" data-rangelength="[20,200]"
									class="form-control"></form:textarea>
								<!--<div class="checkbox">
											<label> <input name="agree" type="checkbox">
												Agree the <a href="#">terms and policy</a>
											</label>
										</div>-->
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-3">
								<!--<button type="submit" class="btn btn-white">Cancel</button>-->
								<button type="submit" class="btn btn-primary">Add User</button>
							</div>
						</div>
					</form:form>
				</section>


			</div>
		</section>
	</section>
</section>
<script src="${context}/js/jquery/jquery-1.4.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
        	function showimagepreview(input) {
            if (input.files && input.files[0]) {
            var filerdr = new FileReader();
            filerdr.onload = function(e) {
            $('#imgprvw').attr('src', e.target.result);
            }
            filerdr.readAsDataURL(input.files[0]);
            }
            }
        </script>

<script type="text/javascript">
	$(document).ready(function() {
            $('#remove-photo-bt').click(function(event){
                $('#imgprvw').attr('src','');
                $('#photo-upload-in').attr('file','');
                $('.file-input-name').remove();
                return false;
            });
	});
</script>