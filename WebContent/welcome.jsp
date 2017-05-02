<%@include file="header.html"%>
<!-- Main jumbotron for a primary marketing message or call to action -->

<!-- Custom CSS -->
<link href="css/landing-page.css" rel="stylesheet">

<!-- Homepage -->
<a name="about"></a>
<div class="intro-header">
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="intro-message">
					<h1>GCIT Library</h1>
					<h3>Your Local Luxury Lounge</h3>
					<hr class="intro-divider">
					<ul class="list-inline intro-social-buttons">
						<li><a href="#" class="btn btn-default btn-lg"
							data-toggle="modal" data-target="#login-modal"><i
								class="fa fa-user fa-fw"></i> <span class="network-name">Borrower</span></a></li>
						<li><a href="librarian.jsp" class="btn btn-default btn-lg"><i
								class="fa fa-user-md fa-fw"></i> <span class="network-name">Librarian</span></a></li>
						<li><a href="admin.jsp" class="btn btn-default btn-lg"><i
								class="fa fa-users fa-fw"></i> <span class="network-name">Administrator</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container -->

</div>
<!-- /.intro-homepage -->

<%@include file="footer.html"%>

<style type="text/css">
/* #####################################################################
   #
   #   Project       : Modal Login with jQuery Effects
   #   Author        : Rodrigo Amarante (rodrigockamarante)
   #   Version       : 1.0
   #   Created       : 07/28/2015
   #   Last Change   : 08/02/2015
   #
   ##################################################################### */
@import url(http://fonts.googleapis.com/css?family=Roboto);

#login-modal .modal-dialog {
	width: 350px;
}

#login-modal input[type=text], input[type=password] {
	margin-top: 10px;
}

#div-login-msg, #div-lost-msg, #div-register-msg {
	border: 1px solid #dadfe1;
	height: 30px;
	line-height: 28px;
	transition: all ease-in-out 500ms;
}

#div-login-msg.success, #div-lost-msg.success, #div-register-msg.success
	{
	border: 1px solid #68c3a3;
	background-color: #c8f7c5;
}

#div-login-msg.error, #div-lost-msg.error, #div-register-msg.error {
	border: 1px solid #eb575b;
	background-color: #ffcad1;
}

#icon-login-msg, #icon-lost-msg, #icon-register-msg {
	width: 30px;
	float: left;
	line-height: 28px;
	text-align: center;
	background-color: #dadfe1;
	margin-right: 5px;
	transition: all ease-in-out 500ms;
}

#icon-login-msg.success, #icon-lost-msg.success, #icon-register-msg.success
	{
	background-color: #68c3a3 !important;
}

#icon-login-msg.error, #icon-lost-msg.error, #icon-register-msg.error {
	background-color: #eb575b !important;
}

#img_logo {
	max-height: 200px;
	max-width: 200px;
}

/* #########################################
   #    override the bootstrap configs     #
   ######################################### */
.modal-backdrop.in {
	filter: alpha(opacity = 50);
	opacity: .8;
}

.modal-content {
	background-color: #ececec;
	border: 1px solid #bdc3c7;
	border-radius: 0px;
	outline: 0;
}

.modal-header {
	min-height: 16.43px;
	padding: 15px 15px 15px 15px;
	border-bottom: 0px;
}

.modal-body {
	position: relative;
	padding: 5px 15px 5px 15px;
}

.modal-footer {
	padding: 15px 15px 15px 15px;
	text-align: left;
	border-top: 0px;
}

.checkbox {
	margin-bottom: 0px;
}

.btn {
	border-radius: 0px;
}

.btn:focus, .btn:active:focus, .btn.active:focus, .btn.focus, .btn:active.focus,
	.btn.active.focus {
	outline: none;
}

.btn-lg, .btn-group-lg>.btn {
	border-radius: 0px;
}

.btn-link {
	padding: 5px 10px 0px 0px;
	color: #95a5a6;
}

.btn-link:hover, .btn-link:focus {
	color: #2c3e50;
	text-decoration: none;
}

.glyphicon {
	top: 0px;
}

.form-control {
	border-radius: 0px;
}
</style>

<script type="text/javascript">
	window.alert = function() {
	};
	var defaultCSS = document.getElementById('bootstrap-css');
	function changeCSS(css) {
		if (css)
			$('head > link')
					.filter(':first')
					.replaceWith(
							'<link rel="stylesheet" href="'+ css +'" type="text/css" />');
		else
			$('head > link').filter(':first').replaceWith(defaultCSS);
	}
</script>
</head>

<!-- BEGIN # MODAL LOGIN -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" align="center">
				<img class="img-circle" id="img_logo"
					src="http://54.83.8.59/site/wp-content/uploads/2014/03/logo_goldcoast1-300x123.png">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</div>

			<!-- Begin # DIV Form -->
			<div id="div-forms">

				<!-- Begin # Login Form -->
				<form id="login-form">
					<div class="modal-body">
						<div id="div-login-msg">
							<div id="icon-login-msg"
								class="glyphicon glyphicon-chevron-right"></div>
							<span id="text-login-msg">Type your card number and
								password.</span>
						</div>
						<input id="login_username" class="form-control" type="number"
							placeholder="Card number" required=""> <input
							id="login_password" class="form-control" type="password"
							placeholder="Password" required="">
						<div class="checkbox">
							<label> <input type="checkbox"> Remember me
							</label>
						</div>
					</div>
					<div class="modal-footer">
						<div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
						</div>
						<div>
							<button id="login_lost_btn" type="button" class="btn btn-link">Lost
								Password?</button>
							<button id="login_register_btn" type="button"
								class="btn btn-link">Register</button>
						</div>
					</div>
				</form>
				<!-- End # Login Form -->

				<!-- Begin | Lost Password Form -->
				<form id="lost-form" style="display: none;">
					<div class="modal-body">
						<div id="div-lost-msg">
							<div id="icon-lost-msg" class="glyphicon glyphicon-chevron-right"></div>
							<span id="text-lost-msg">Type your e-mail.</span>
						</div>
						<input id="lost_email" class="form-control" type="text"
							placeholder="E-Mail (type ERROR for error effect)" required="">
					</div>
					<div class="modal-footer">
						<div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">Send</button>
						</div>
						<div>
							<button id="lost_login_btn" type="button" class="btn btn-link">Log
								In</button>
							<button id="lost_register_btn" type="button" class="btn btn-link">Register</button>
						</div>
					</div>
				</form>
				<!-- End | Lost Password Form -->

				<!-- Begin | Register Form -->
				<form id="register-form" style="display: none;">
					<div class="modal-body">
						<div id="div-register-msg">
							<div id="icon-register-msg"
								class="glyphicon glyphicon-chevron-right"></div>
							<span id="text-register-msg">Register an account.</span>
						</div>
						<input id="register_username" name="register_username"
							class="form-control" type="number"
							placeholder="Username (type ERROR for error effect)" required="">
						<input id="register_email" class="form-control" type="text"
							placeholder="E-Mail" required=""> <input
							id="register_password" class="form-control" type="password"
							placeholder="Password" required="">
					</div>
					<div class="modal-footer">
						<div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
						</div>
						<div>
							<button id="register_login_btn" type="button"
								class="btn btn-link">Log In</button>
							<button id="register_lost_btn" type="button" class="btn btn-link">Lost
								Password?</button>
						</div>
					</div>
				</form>
				<!-- End | Register Form -->

			</div>
			<!-- End # DIV Form -->

		</div>
	</div>
</div>
<!-- END # MODAL LOGIN -->

<script type="text/javascript">
	/* #####################################################################
	#
	#   Project       : Modal Login with jQuery Effects
	#   Author        : Rodrigo Amarante (rodrigockamarante)
	#   Version       : 1.0
	#   Created       : 07/29/2015
	#   Last Change   : 08/04/2015
	#
	##################################################################### */

	$(function() {

		var $formLogin = $('#login-form');
		var $formLost = $('#lost-form');
		var $formRegister = $('#register-form');
		var $divForms = $('#div-forms');
		var $modalAnimateTime = 300;
		var $msgAnimateTime = 150;
		var $msgShowTime = 2000;

		$("form")
				.submit(
						function() {

							var loginSucceed = false;
							var cardNo = $('#login_username').val();
							$
									.ajax(
											{
												url : "validateUser",
												data : {
													username : $(
															'#login_username')
															.val()
												}
											})
									.done(
											function(data) {
												if (data
														.localeCompare("succeed") == 0) {
													msgChange(
															$('#div-login-msg'),
															$('#icon-login-msg'),
															$('#text-login-msg'),
															"success",
															"glyphicon-ok",
															"Login OK");
													setTimeout(
															function() {
																var form =$('<form action="login_borrower" method="post"><input type="hidden" name="cardNo" value='+cardNo+'></form>');
																$(document.body).append(form);
																form.submit();
															}, 1000);
												} else {
													msgChange(
															$('#div-login-msg'),
															$('#icon-login-msg'),
															$('#text-login-msg'),
															"error",
															"glyphicon-remove",
															"Login error");
												}

											})

							return false;
						});

		$('#login_register_btn').click(function() {
			modalAnimate($formLogin, $formRegister)
		});
		$('#register_login_btn').click(function() {
			modalAnimate($formRegister, $formLogin);
		});
		$('#login_lost_btn').click(function() {
			modalAnimate($formLogin, $formLost);
		});
		$('#lost_login_btn').click(function() {
			modalAnimate($formLost, $formLogin);
		});
		$('#lost_register_btn').click(function() {
			modalAnimate($formLost, $formRegister);
		});
		$('#register_lost_btn').click(function() {
			modalAnimate($formRegister, $formLost);
		});

		function modalAnimate($oldForm, $newForm) {
			var $oldH = $oldForm.height();
			var $newH = $newForm.height();
			$divForms.css("height", $oldH);
			$oldForm.fadeToggle($modalAnimateTime, function() {
				$divForms.animate({
					height : $newH
				}, $modalAnimateTime, function() {
					$newForm.fadeToggle($modalAnimateTime);
				});
			});
		}

		function msgFade($msgId, $msgText) {
			$msgId.fadeOut($msgAnimateTime, function() {
				$(this).text($msgText).fadeIn($msgAnimateTime);
			});
		}

		function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass,
				$msgText) {
			var $msgOld = $divTag.text();
			msgFade($textTag, $msgText);
			$divTag.addClass($divClass);
			$iconTag.removeClass("glyphicon-chevron-right");
			$iconTag.addClass($iconClass + " " + $divClass);
			setTimeout(function() {
				msgFade($textTag, $msgOld);
				$divTag.removeClass($divClass);
				$iconTag.addClass("glyphicon-chevron-right");
				$iconTag.removeClass($iconClass + " " + $divClass);
			}, $msgShowTime);
		}

	});
</script>
