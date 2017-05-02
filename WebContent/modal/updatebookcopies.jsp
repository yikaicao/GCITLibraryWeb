

<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Integer itemId = Integer.parseInt(request.getParameter("branchId"));
	Branch br = service.getBranchByPk(itemId);
	List<Book> bookList = service.getAllBooks();
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add Book Copies to Branch</h4>
	</div>
	<form action="updateBookCopies" method="post">
		<div class="modal-body">
			<div class="form-group">
				<label for="sel1">Select Book:</label> <select class="form-control"
					id="sel1" name="bookId">
					<%
						for (Book bk : bookList) {
					%>
					<option value=<%=bk.getBookId()%>><%=bk.getTitle()%></option>
					<%
						}
					%>
				</select> <label>Select Amount (0-99):</label><br /> <input type="number"
					name="quantity" min="0" max="99"> <input type="hidden"
					name="branchId" value="<%=br.getBranchId()%>">
			</div>

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Update Book
				Copies</button>
		</div>
	</form>
</div>