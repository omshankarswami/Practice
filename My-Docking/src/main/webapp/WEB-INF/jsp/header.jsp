
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script language="JavaScript"
	src="https://code.jquery.com/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script language="JavaScript"
	src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script language="JavaScript"
	src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js"
	type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="http://cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">

<script type="text/javascript">
	$(function() {
		$('.nav li a').filter(function() {
			return this.href == location.href
		}).parent().addClass('active').siblings().removeClass('active')
		$('.nav li a').click(
				function() {
					$(this).parent().addClass('active').siblings().removeClass(
							'active')
				})
	})
</script>

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/"><img alt="mydocking" src="https://www.mydocking.com/fileadmin/templates/images/bg_logo_header.svg" style="height: 150%;"></a>
			</div>
			<ul class="nav navbar-nav">
				<!-- <li class="active"><a href="/">Startseite</a></li> -->
				<li><a href="/web/products">Produkte</a></li>
				<li><a href="/web/files">Videos</a></li>
				<li><a href="/web/category">Kategorien</a></li>
			</ul>
			<ul class="nav navbar-nav" style="float: right">
				<li><a href="/logout">Logout</a></li>
			</ul>
		</div>
	</nav>