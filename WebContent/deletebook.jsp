<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book book = service.getBookByPk(bookId);
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Are you sure to delete this book?</h4>
	</div>
	<form action="deleteBook" method="post">
		<div class="modal-body">
			<p><%=book.getTitle()%></p>
			<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Keep</button>
			<button type="submit" class="btn btn-primary">Delete</button>
		</div>
	</form>
</div>