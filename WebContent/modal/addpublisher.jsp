
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Update Publisher Details</h4>
	</div>
	<form action="addPublisher" method="post">
		<div class="modal-body">
			<p>Enter new publisher's name:</p>
			<input type="text" name="publisherName" placeholder="New Name"
				required> <br />

			<p>Enter new publisher's address</p>
			<input type="text" name="publisherAddress" placeholder="New Address"
				required> <br />

			<p>Enter new publisher's phone</p>
			<input type="text" name="publisherPhone" placeholder="New Phone"
				required>

		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Publisher</button>
		</div>
		
	</form>
</div>