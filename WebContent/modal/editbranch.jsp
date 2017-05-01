<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Integer itemId = Integer.parseInt(request.getParameter("branchId"));
	Branch br = service.getBranchByPk(itemId);
%>


<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Update Branch Details</h4>
	</div>
	<form action="editBranch" method="post">
		<div class="modal-body">
			<p>Enter new branch name:</p>
			<input type="text" name="branchName" value="<%=br.getBranchName()%>"
				placeholder="New Name" required> <br />
			<p>Enter new branch address:</p>
			<input type="text" name="branchAddress"
				value="<%=br.getBranchAddress()%>" placeholder="New Address"
				required> <br /> <input type="hidden" name="branchId"
				value="<%=br.getBranchId()%>">
				<input type="hidden" name="redirect" value="<%=request.getParameter("redirect")%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>