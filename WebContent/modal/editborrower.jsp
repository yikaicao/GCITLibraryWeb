<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.AdminService"%>
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
		<h4 class="modal-title">Update Borrower Details</h4>
	</div>
	<form action="editBorrower" method="post">
		<div class="modal-body">
			<p>Enter the name of the Borrower:</p>
			<input type="text" name="borrowerName" value="<%=bo.getName()%>"
				placeholder="New Name" required> <br />
			<p>Enter the address of the Borrower:</p>
			<input type="text" name="borrowerAddress"
				value="<%=bo.getAddress()%>" placeholder="New Address" required>
			<br />
			<p>Enter the phone number of the Borrower:</p>
			<input type="text" name="borrowerPhone"
				value="<%=bo.getPhone()%>" placeholder="New Phone" required>
			<br /> <input type="hidden" name="borrowerId"
				value="<%=bo.getCardNo()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>