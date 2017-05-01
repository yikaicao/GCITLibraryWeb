<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%
	AdminService service = new AdminService();
	Integer itemId = Integer.parseInt(request.getParameter("borrowerId"));
	Borrower bo = service.getBorrowerByPk(itemId);
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Are you sure to delete this borrower?</h4>
	</div>
	<form action="deleteBorrower" method="post">
		<div class="modal-body">
			<p><%=bo.getName()%></p>
			<input type="hidden" name="borrowerId"
				value="<%=bo.getCardNo()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Keep</button>
			<button type="submit" class="btn btn-primary">Delete</button>
		</div>
	</form>
</div>