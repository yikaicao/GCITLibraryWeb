

<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.Date"%>

<%
	AdminService service = new AdminService();

	List<BookLoan> blList = new ArrayList<>();
	Integer branchId = Integer.valueOf(request.getParameter("branchId"));
	Integer cardNo = Integer.valueOf(request.getParameter("cardNo"));
	blList = service.getAllBookLoans(branchId, cardNo);
%>

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

			<!-- Table -->
			<div class="table-responsive">
				<table class="table table-striped" id="booksTable">
					<thead>
						<tr>
							<th>#</th>
							<th>Book</th>
							<th>Date Out</th>
							<th>Due Date</th>
							<th>Operation</th>
						</tr>
					</thead>
					<tbody>

						<%
							int counter = 0;
							for (BookLoan bl : blList) {
						%>
						<tr>
							<td>
								<%
									out.println(++counter);
								%>
							</td>

							<td>
								<%
									Book bk = service.getBookByPk(bl.getBookId());

										out.println(bk.getTitle());
								%>
							</td>


							<td>
								<%
									out.println(bl.getDateOut().toString());
								%>
							</td>
							<td>
								<%
									out.println(bl.getDueDate().toString());
								%>
							</td>
							<td>
								<form action="returnBookLoan" method="post">
									<input type="hidden" name="bookId" value="<%=bk.getBookId()%>">
									<input type="hidden" name="branchId" value="<%=branchId%>">
									<input type="hidden" name="cardNo" value="<%=cardNo%>">
									<input type="hidden" name="redirect" value="borrower">
									<%
										if (bl.getDueDate().after(new Date())) {
									%>
									<button type="submit" class="btn btn-success">Return</button>
									<%
										} else {
									%>
									<button type="submit" class="btn btn-warning">Return</button>
									<%
										}
									%>
									
								</form>
							</td>
						</tr>



						<%
							}
						%>

					</tbody>
				</table>
			</div>
			<!-- /.Table -->


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	</form>
</div>