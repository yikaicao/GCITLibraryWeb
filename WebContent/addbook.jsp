<style>
.modal-body-left, .modal-body-right {
	width: 50%;
	float: left;
}

.modal-footer {
	clear: both;
}

.modal-body select, input{
	width: 80%;
}

</style>

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
				<select multiple="multiple" size="5" name="authors">
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
					<option value="volvo">Volvo</option>
					<option value="saab">Saab</option>
					<option value="mercedes">Mercedes</option>
					<option value="audi">Audi</option>
				</select>
			</div>

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Book</button>
		</div>
	</form>
</div>