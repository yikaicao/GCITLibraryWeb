
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add New Author</h4>
	</div>
	<form action="addAuthor" method="post">
		<div class="modal-body">
			<p>Enter new author's name:</p>
			<input type="text" name="authorName"> <br /> <input
				type="hidden" name="authorId">
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Author</button>
		</div>
	</form>
</div>