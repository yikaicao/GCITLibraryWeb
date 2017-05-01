<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@include file="header.html"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<head>
<!-- Custom styles for this template -->
<link href="css/landing-page.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
</head>

<%
	AdminService service = new AdminService();

	Integer numOfPages = 0;
	Integer itemCount = service.getCount("bookloan");

	if (itemCount % 10 > 0)
		numOfPages = itemCount / 10 + 1;
	else
		numOfPages = itemCount / 10;

	List<BookLoan> blList = new ArrayList<>();

	if (request.getAttribute("branches") != null)
		blList = (List<BookLoan>) request.getAttribute("bookloan");
	else
		blList = service.getAllBookLoansOnPage(1);

	Integer pageNo;
	if (request.getAttribute("pageNo") == null)
		pageNo = 1;
	else
		pageNo = (Integer) request.getAttribute("pageNo");
	Integer previousPageNo = pageNo == 1 ? 1 : pageNo - 1;
	Integer nextPageNo = pageNo == numOfPages ? numOfPages : pageNo + 1;
%>


<script>
	function searchItems() {
		$.ajax({
			url : "searchBorrowers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : 1
			}
		}).done(function(data) {
			var array_data = String(data).split("\n");
			$('#itemsTable').html($.trim(array_data[0]));
			$('#paginationList').html($.trim(array_data[1]));
		})
	};

	function searchItemsPage(pageNo) {
		$.ajax({
			url : "searchBorrowers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo
			}
		}).done(function(data) {
			var array_data = String(data).split("\n");
			$('#itemsTable').html($.trim(array_data[0]));
			$('#paginationList').html($.trim(array_data[1]));
		})
	};
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li><a href="admin.jsp">Overview</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="authors.jsp">All Authors </a></li>
				<li><a href="books.jsp">All Books</a></li>
				<li><a href="publishers.jsp">All Publishers</a></li>
				<li><a href="branches.jsp">All Branches</a></li>
				<li><a href="borrowers.jsp">All Borrowers</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li class="active"><a href="bookloans.jsp">Override Due Date<span
						class="sr-only">(current)</span></a></li>
			</ul>
		</div>


		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h5>${message}</h5>

			<h2 class="sub-header">Book Loan List</h2>

			<!-- Search -->
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group" style="display: inline;">
						<input type="text" class="form-control"
							placeholder="Search for Borrower" style="border-radius: 5px;"
							name="searchString" id="searchString" oninput="searchItems()">
						<span class="input-group-btn"> </span>
					</div>
					<!-- /input-group -->

				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- Search -->



			<div class="table-responsive">
				<table class="table table-striped" id="itemsTable">
					<thead>
						<tr>
							<th>#</th>
							<th>Book</th>
							<th>Branch</th>
							<th>Borrower</th>
							<th>Date Out</th>
							<th>Due Date</th>
							<th>Return</th>
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
									out.println((pageNo - 1) * 10 + (++counter));
								%>
							</td>

							<td>
								<%
									out.println(service.getBookByPk(bl.getBookId()).getTitle());
								%>
							</td>

							<td>
								<%
									out.println(service.getBranchByPk(bl.getBranchId()).getBranchName());
								%>
							</td>


							<td>
								<%
									out.println(service.getBorrowerByPk(bl.getCardNo()).getName());
								%>
							</td>

							<td>
								<%
									out.println(bl.getDateOut().toString());
								%>
							</td>


							<td>
								<%
									out.print(bl.getDueDate().toString());
								%>
							</td>

							<td>
								<%
									if (bl.getDateIn() != null) {
											out.println(bl.getDateIn().toString());
										} else {
								%>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalConnector"
									href="modal/changeduedate.jsp?bookId=<%=bl.getBookId()%>&branchId=<%=bl.getBranchId()%>&cardNo=<%=bl.getCardNo()%>">
									Update</button>
								<button type="button" class="btn btn-warning"
									data-toggle="modal" data-target="#modalConnector"
									href="modal/returnbookloan.jsp?bookId=<%=bl.getBookId()%>&branchId=<%=bl.getBranchId()%>&cardNo=<%=bl.getCardNo()%>">
									Return</button> <%
 	}
 %>
							</td>

						</tr>

						<%
							}
						%>

					</tbody>
				</table>
			</div>

			<!-- Modal Connector -->
			<div class="modal fade" tabindex="-1" role="dialog"
				id="modalConnector">
				<div class="modal-dialog" role="document">
					<div class="modal-content"></div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.Modal Connector -->



			<!-- Pagination -->
			<nav aria-label="Page navigation">
				<ul class="pagination" id="paginationList">
					<li><a href="pageBorrowers?pageNo=<%=previousPageNo%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>

					<%
						for (int i = 1; i <= numOfPages; i++) {
					%>
					<li><a href="pageBorrowers?pageNo=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<li><a href="pageBorrowers?pageNo=<%=nextPageNo%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
			<!-- End Pagination -->


		</div>
	</div>
</div>



<script>
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>