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
								out.println(service.getAllNumOfCopies(bk.getBookId(), Integer.valueOf(request.getParameter("branchId"))));
							%>
						</td>
						<td>
						
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
