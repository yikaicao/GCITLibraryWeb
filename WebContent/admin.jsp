<%@include file="header.html"%>

<head>
<!-- Custom styles for this template -->
<link href="css/landing-page.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
</head>

<div class="row">
	<div class="col-lg-12">


		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-3 col-md-2 sidebar">
					<ul class="nav nav-sidebar">
						<li class="active"><a href="#">Overview <span
								class="sr-only">(current)</span></a></li>
					</ul>
					<ul class="nav nav-sidebar">
						<li><a href="authors.jsp">All Authors</a></li>
						<li><a href="books.jsp">All Books</a></li>
						<li><a href="publishers.jsp">All Publishers</a></li>
						<li><a href="branches.jsp">All Branches</a></li>
						<li><a href="borrowers.jsp">All Borrowers</a></li>
					</ul>
					<ul class="nav nav-sidebar">
						<li><a href="bookloans.jsp">Override Due Date</a></li>
					</ul>
				</div>
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<h1 class="page-header">Dashboard</h1>

					<div class="row placeholders">
						<div class="col-xs-6 col-sm-3 placeholder">
							<a href="authors.jsp"><img src="img/author.png" width="150" height="150">
								<h4>Author</h4> <span class="text-muted">Add/Change/Delete</span></a>
						</div>
						<div class="col-xs-6 col-sm-3 placeholder">
							<a href="books.jsp"><img src="img/book.png" width="150" height="150">
							<h4>Book</h4>
							<span class="text-muted">Add/Change/Delete</span></a>
						</div>
						<div class="col-xs-6 col-sm-3 placeholder">
							<a href="publishers.jsp"><img src="img/publisher.png" width="150" height="150">
								<h4>Publisher</h4> <span class="text-muted">Add/Change/Delete</span></a>
						</div>
						<div class="col-xs-6 col-sm-3 placeholder">
							<a href="branches.jsp"><img src="img/library.png" width="150" height="150">
								<h4>Branch</h4> <span class="text-muted">Add/Change/Delete</span></a>
						</div>

						<div class="col-xs-6 col-sm-3 placeholder">
							<a href="borrowers.jsp"><img src="img/borrower.png" width="150" height="150">
								<h4>Borrower</h4> <span class="text-muted">Add/Change/Delete</span></a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.html"%>