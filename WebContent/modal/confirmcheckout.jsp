
<div class="modal fade" id="checkoutModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 class="modal-title">Confirmation</h3>
			</div>
			<form action="checkoutBook" method="post">
			<div class="modal-body">
				<p>Are you sure to check out "<% %>"?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger">Confirm</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>