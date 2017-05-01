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
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book book = service.getBookByPk(bookId);
	

	List<Author> authors = new ArrayList<>();
	authors = service.getAllAtuhors();

	List<Genre> genres = new ArrayList<>();
	genres = service.getAllGenres();

	List<Publisher> pubs = new ArrayList<>();
	pubs = service.getAllPublishers();
%>


<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Update Book Details</h4>
	</div>
	<form action="editBook" method="post">

		<div class="modal-body">

			<div class="modal-body-left">
				<p>Enter the title of the Book:</p>
				<input type="text" name="bookTitle" value="<%=book.getTitle()%>"  placeholder="New Title" required>
				<br /> <input type="hidden" name="bookId"
					value="<%=book.getBookId()%>">
			</div>
			<div class="modal-body-right">
				<p>Select updated book's author (multiple):</p>
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
				<p>Select updated book's genre (multiple):</p>

				<select multiple="multiple" size="5" name="genres" required>
					<%
						for (Genre gn : genres) {
					%>
					<option value='<%=gn.getGenreId()%>'><%=gn.getGenreName()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="modal-body-right">
				<p>Select updated book's publisher:</p>
				<select size="5" name="publisher" required>
					<%
						for (Publisher pb : pubs) {
					%>
					<option value='<%=pb.getPubId()%>'><%=pb.getPubName()%></option>
					<%
						}
					%>
				</select>
			</div>
						
			
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>