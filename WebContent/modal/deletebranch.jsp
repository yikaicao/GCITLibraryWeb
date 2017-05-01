<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Integer pk = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = service.getBranchByPk(pk);
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Are you sure to delete this branch?</h4>
	</div>
	<form action="deleteBranch" method="post">
		<div class="modal-body">
			<p><%=branch.getBranchName()%></p>
			<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Keep</button>
			<button type="submit" class="btn btn-primary">Delete</button>
		</div>
	</form>
</div>