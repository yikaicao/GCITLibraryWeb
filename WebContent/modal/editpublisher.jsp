<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Integer itemId = Integer.parseInt(request.getParameter("publisherId"));
	Publisher pb = service.getPublisherByPk(itemId);
%>


<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Update Publisher Details</h4>
	</div>
	<form action="editPublisher" method="post">
		<div class="modal-body">
			<p>Enter the name of the Publisher:</p>
			<input type="text" name="publisherName" value="<%=pb.getPubName()%>"
				placeholder="New Name" required> <br />
			<p>Enter the address of the Publisher:</p>
			<input type="text" name="publisherAddress"
				value="<%=pb.getPubAddr()%>" placeholder="New Address" required>
			<br />
			<p>Enter the phone number of the Publisher:</p>
			<input type="text" name="publisherPhone"
				value="<%=pb.getPubPhone()%>" placeholder="New Phone" required>
			<br /> <input type="hidden" name="publisherId"
				value="<%=pb.getPubId()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>