
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Enter New Branch Details</h4>
	</div>
	<form action="addBranch" method="post">
		<div class="modal-body">
			<p>Enter new branch name:</p>
			<input type="text" name="branchName" placeholder="New Name"
				required> <br />

			<p>Enter new branch address</p>
			<input type="text" name="branchAddress" placeholder="New Address"
				required> <br />

		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Branch</button>
		</div>
		
	</form>
</div>