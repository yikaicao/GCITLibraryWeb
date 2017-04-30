<%@include file="header.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<head>
<!-- Custom styles for this template -->
<link href="css/landing-page.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
</head>


<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li><a href="admin.jsp">Overview</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li class="active"><a href="#">All Authors <span
						class="sr-only">(current)</span></a></li>
				<li><a href="">All Books</a></li>
				<li><a href="">All Publishers</a></li>
				<li><a href="">All Branches</a></li>
				<li><a href="">All Branches</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="">OverRide Due Date</a></li>
			</ul>
		</div>


		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">Book List</h2>

			<!-- Search -->
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group" style="display: inline;">
						<input type="text" class="form-control"
							placeholder="Search for Author" style="border-radius: 5px;"
							name="searchString" id="searchString" oninput="searchAuthors()">
						<span class="input-group-btn"> </span>
					</div>
					<!-- /input-group -->

				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- Search -->


			<!-- Table -->
			<div class="table-responsive">
				<table class="table table-striped" id="authorsTable">
					<thead>
						<tr>
							<th>#</th>
							<th>Author Name</th>
							<th>Operation</th>
						</tr>
					</thead>
					<tbody>

						<tr>

						</tr>


					</tbody>
				</table>
			</div>
			<!-- /.Table -->


			<!-- Modal Connector -->
			<div class="modal fade" tabindex="-1" role="dialog"
				id="editAuthorModal">
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
					<li><a href="pageAuthors?pageNo=<%=1%>" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
					</a></li>

					<%
						for (int i = 1; i <= 10; i++) {
					%>
					<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<li><a href="pageAuthors?pageNo=<%=1%>" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
			<!-- End Pagination -->
			<button type="button" class="btn btn-success"
				style="display: inline; float: left;" data-toggle="modal"
				data-target="#editAuthorModal" href="addauthor.jsp?">Add</button>

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