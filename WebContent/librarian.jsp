<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%

	AdminService service = new AdminService();

	Integer numOfPages = 0;
	Integer itemCount = service.getCount("branch");

	List<Branch> branches = new ArrayList<>();

	if (request.getAttribute("branches") != null)
		branches = (List<Branch>) request.getAttribute("branches");
	else
		branches = service.getAllBranchesOnPage(1);

%>

<head>
<link href="css/librarian.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
</head>

<div class="container">

	<!-- Jumbotron Header -->
	<header class="jumbotron hero-spacer">
		<h1>Welcome, Librarian!</h1>
		<p>Here, you can customize your branch and add copies of book in
			it.</p>
		<p>
			<a class="btn btn-primary btn-large" href="#started">Get Started</a>
		</p>
	</header>

	<hr>

	<!-- Title -->
	<div class="row">
		<div class="col-lg-12">
			<h3>All Branches</h3>
		</div>
	</div>
	<!-- /.row -->

	<!-- Page Features -->
	<div class="row text-center" id="started">

		<%
			for (Branch br: branches) {
		%>

		<div class="col-md-3 col-sm-6 hero-feature">
			<div class="thumbnail">
				<div class="caption">
					<h3><%=br.getBranchName()%></h3>
					<p><%=br.getBranchAddress()%></p>
					<p>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#modalConnector"
							href="modal/editbranch.jsp?branchId=<%=br.getBranchId()%>&redirect='librarian'"
							style="margin-right: 2%;">Update</button>
						<button type="submit" class="btn btn-success">Add</button>
					</p>
				</div>
			</div>
		</div>

		<%
			}
		%>
		<!-- Modal Connector -->
		<div class="modal fade" tabindex="-1" role="dialog"
			id="modalConnector">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="text-align: left;"></div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.Modal Connector -->

	</div>
	<!-- /.row -->

	<hr>

	<!-- Footer -->
	<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright © Gold Coast IT Solution LLC. 2003-2017</p>
			</div>
		</div>
	</footer>

</div>


<script>
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>