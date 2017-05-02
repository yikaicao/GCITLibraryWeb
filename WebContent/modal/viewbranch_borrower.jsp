<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<link href="css/modal.css" rel="stylesheet">

<%
	AdminService service = new AdminService();
	List<Book> books = new ArrayList<>();

	books = service.getAllBooksAtBranch(Integer.valueOf(request.getParameter("branchId")));
%>


<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Book Details</h4>
	</div>

	<div class="modal-body">
		<!-- Table -->
		<div class="table-responsive">
			<table class="table table-striped" id="booksTable">
				<thead>
					<tr>
						<th>#</th>
						<th>Title</th>
						<th>Copies</th>
						<th>Operation</th>
					</tr>
				</thead>
				<tbody>

					<%
						int counter = 0;
						for (Book bk : books) {
					%>
					<tr>
						<td>
							<%
								out.println(++counter);
							%>
						</td>

						<td>
							<%
								out.println(bk.getTitle());
							%>
						</td>


						<td>
							<%
								Integer noOfCopies = service.getAllNumOfCopies(bk.getBookId(),
											Integer.valueOf(request.getParameter("branchId")));
									out.println(noOfCopies);
							%>
						</td>
						<td>
							<form action="checkoutBook" method="post">
								<input type="hidden" name="bookId" value="<%=bk.getBookId()%>">
								<input type="hidden" name="branchId"
									value="<%=Integer.valueOf(request.getParameter("branchId"))%>">
								<input type="hidden" name="cardNo"
									value="<%=request.getParameter("cardNo")%>">
								<%
									if (noOfCopies > 0) {
								%>
								<button type="submit" class="btn btn-warning"
									data-toggle="modal">Check Out</button>
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


</div>

<!-- Modal Connector -->
<div class="modal fade" tabindex="-1" role="dialog" id="modalConnector">
	<div class="modal-dialog" role="document">
		<div class="modal-content" style="text-align: left;"></div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.Modal Connector -->


