<%@include file="header.html"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>

<head>
<!-- Custom styles for this template -->
<link href="css/landing-page.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
</head>

<%
	AdminService service = new AdminService();

	Integer numOfPages = 0;
	Integer authorsCount = service.getAuthorsCount();
	if (authorsCount % 10 > 0)
		numOfPages = authorsCount / 10 + 1;
	else
		numOfPages = authorsCount / 10;

	List<Author> authors = new ArrayList<>();

	if (request.getAttribute("authors") != null)
		authors = (List<Author>) request.getAttribute("authors");
	else
		authors = service.getAllAuthorsOnPage(1);

	Integer pageNo;
	if (request.getAttribute("pageNo") == null)
		pageNo = 1;
	else
		pageNo = (Integer) request.getAttribute("pageNo");
	Integer previousPageNo = pageNo == 1 ? 1 : pageNo - 1;
	Integer nextPageNo = pageNo == numOfPages ? numOfPages : pageNo + 1;
%>


<script>
	function searchAuthors() {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val(),
				pageNo : 1
			}
		}).done(function(data) {
			var array_data = String(data).split("\n");
			$('#authorsTable').html($.trim(array_data[0]));
			$('#paginationList').html($.trim(array_data[1]));
		})
	};

	function searchAuthorsPage(pageNo) {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo
			}
		}).done(function(data) {
			var array_data = String(data).split("\n");
			$('#authorsTable').html($.trim(array_data[0]));
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
				<li class="active"><a href="#">All Authors <span
						class="sr-only">(current)</span></a></li>
				<li><a href="books.jsp">All Books</a></li>
				<li><a href="">All Publishers</a></li>
				<li><a href="">All Branches</a></li>
				<li><a href="">All Branches</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="">Override Due Date</a></li>
			</ul>
		</div>


		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">Author List</h2>

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

						<%
							int counter = 0;
							for (Author a : authors) {
						%>
						<tr>
							<td>
								<%
									out.println((pageNo - 1) * 10 + (++counter));
								%>
							</td>

							<td>
								<%
									out.println(a.getAuthorName());
								%>
							</td>


							<td><button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalConnector"
									href="editauthor.jsp?authorId=<%=a.getAuthorId()%>"
									style="margin-right: 2%;">Update</button>
								<button type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#modalConnector"
									href="deleteauthor.jsp?authorId=<%=a.getAuthorId()%>">Delete</button>
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
					<li><a href="pageAuthors?pageNo=<%=previousPageNo%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>

					<%
						for (int i = 1; i <= numOfPages; i++) {
					%>
					<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<li><a href="pageAuthors?pageNo=<%=nextPageNo%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
			<!-- End Pagination -->


			<!-- Add Button -->
			<button type="button" class="btn btn-success"
				style="display: inline; float: left;" data-toggle="modal"
				data-target="#modalConnector" href="addauthor.jsp?">Add</button>

			<!-- /.Add Button -->
			
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