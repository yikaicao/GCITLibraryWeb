<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<style>
.modal-body-left, .modal-body-right {
	width: 50%;
	float: left;
	margin-bottom: 15px;
}

.modal-footer {
	clear: both;
}

.modal-body select, input {
	width: 80%;
}
</style>
<%
	AdminService service = new AdminService();
	List<Author> authors = new ArrayList<>();
	authors = service.getAllAtuhors();
	List<Genre> genres = new ArrayList<>();
	genres = service.getAllGenres();
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add New Book</h4>
	</div>
	<form action="addBook" method="post">
		<div class="modal-body">
			<div class="modal-body-left">
				<p>Enter new book's title:</p>
				<input type="text" name="bookTitle" placeholder="New Title">
			</div>

			<div class="modal-body-right">
				<p>Select new book's author (multiple):</p>
				<select multiple="multiple" size="5" name="authors" required>
					<%
						for (Author au : authors) {
					%>
					<option value='<%=au.getAuthorId()%>'><%=au.getAuthorName()%></option>
					<%
						}
					%>
				</select>
			</div>
			
			
			<div class="modal-body-left">
				<p>Select new book's genre (multiple):</p>
				
				<select multiple="multiple" size="5" name="genre" required>
					<%
						for (Genre gn : genres) {
					%>
					<option value='<%=gn.getGenreId()%>'><%=gn.getGenreName()%></option>
					<%
						}
					%>
				</select>
				
			</div>
			
			
			
			

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Book</button>
		</div>
	</form>
</div>