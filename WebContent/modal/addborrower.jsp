
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Enter New Borrower Details</h4>
	</div>
	<form action="addBorrower" method="post">
		<div class="modal-body">
			<p>Enter new borrower's name:</p>
			<input type="text" name="name" placeholder="New Name" required>
			<br />

			<p>Enter new borrower's address</p>
			<input type="text" name="address" placeholder="New Address" required>
			<br />

			<p>Enter new borrower's phone</p>
			<input type="text" name="phone" placeholder="New Phone" required>

		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Borrower</button>
		</div>

	</form>
</div>