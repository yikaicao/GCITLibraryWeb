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
		<h4 class="modal-title">Are you sure to delete this publisher?</h4>
	</div>
	<form action="deletePublisher" method="post">
		<div class="modal-body">
			<p><%=pb.getPubName()%></p>
			<input type="hidden" name="publisherId"
				value="<%=pb.getPubId()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Keep</button>
			<button type="submit" class="btn btn-primary">Delete</button>
		</div>
	</form>
</div>