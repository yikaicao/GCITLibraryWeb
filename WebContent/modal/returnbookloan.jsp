

<%@page import="java.util.Date"%>

<script>
	$("#theDate").val(getFormattedDate(today()));

	function today() {
		return new Date();
	}
</script>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Book Loan Return</h4>
	</div>
	<form action="returnBookLoan" method="post">
		<div class="modal-body">
			<p>Are you sure to return this precious book?</p>
			<input type="hidden" name="bookId"
				value="<%=request.getParameter("bookId")%>"> <input
				type="hidden" name="branchId"
				value="<%=request.getParameter("branchId")%>"> <input
				type="hidden" name="cardNo"
				value="<%=request.getParameter("cardNo")%>">

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>