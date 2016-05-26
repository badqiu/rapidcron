<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<html>
<head>
	<!-- jquery -->
	<script src="${ctx}/js/jquery.min.js"></script>
	<script src="${ctx}/js/jquery.cookie.js"></script>
	
	<!-- simpletable -->
	<link href="${ctx}/widgets/simpletable/simpletable.css" type="text/css" rel="stylesheet">
	<script  src="${ctx}/widgets/simpletable/simpletable.js" type="text/javascript"></script>
	
	<!-- bootstrap -->
	<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet"  media="screen"/>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	
	<!-- multiple-select -->
	<script src="${ctx}/js/multiple-select/jquery.multiple.select.js"></script>
	<link href="${ctx}/js/multiple-select/multiple-select.css" rel="stylesheet"  media="screen"/>
	
	<script src="${ctx}/js/metisMenu/metisMenu.js"></script>
	<link href="${ctx}/js/metisMenu/metisMenu.css" rel="stylesheet"  media="screen"/>
	
	<!-- fileinput -->
	<script src="${ctx}/js/fileinput.js"></script>
	<script src="${ctx}/js/fileinput_locale_zh.js"></script>
	<link href="${ctx}/css/fileinput.css" rel="stylesheet"  media="screen"/>
	
	<!-- jquery.validate doc: http://jqueryvalidation.org/ -->
	<script src="${ctx}/js/jquery.validate/jquery.validate.js"></script>
	<script src="${ctx}/js/jquery.validate/additional-methods.js"></script>
	<script src="${ctx}/js/jquery.validate/localization/messages_zh.js"></script>
	
	<script src="${ctx}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	
	<!-- app -->
	<script src="${ctx}/js/rest.js" ></script>
	<script src="${ctx}/js/app.js" ></script>
	<link href="${ctx}/css/global.css" type="text/css" rel="stylesheet">
	
	<rapid:block name="head">
	</rapid:block>
	
	<style type="text/css">
	    body,html{
			height: 100%;
		}
	
		/* remove outer padding */
		.main .row{
			padding: 0px;
			margin: 0px;
		}
	
		/*Remove rounded coners*/
	
		nav.sidebar.navbar {
			border-radius: 0px;
		}
	
		nav.sidebar, .main{
			-webkit-transition: margin 200ms ease-out;
		    -moz-transition: margin 200ms ease-out;
		    -o-transition: margin 200ms ease-out;
		    transition: margin 200ms ease-out;
		}
	
		/* Add gap to nav and right windows.*/
		.main{
			padding: 10px 10px 0 10px;
		}
	
		/* .....NavBar: Icon only with coloring/layout.....*/
	
		/*small/medium side display*/
		@media (min-width: 768px) {
	
			/*Allow main to be next to Nav*/
			.main{
				position: absolute;
				width: calc(100% - 40px); /*keeps 100% minus nav size*/
				margin-left: 40px;
				float: right;
			}
	
			/*lets nav bar to be showed on mouseover*/
			nav.sidebar:hover + .main{
				margin-left: 200px;
			}
	
			/*Center Brand*/
			nav.sidebar.navbar.sidebar>.container .navbar-brand, .navbar>.container-fluid .navbar-brand {
				margin-left: 0px;
			}
			/*Center Brand*/
			nav.sidebar .navbar-brand, nav.sidebar .navbar-header{
				text-align: center;
				width: 100%;
				margin-left: 0px;
			}
	
			/*Center Icons*/
			nav.sidebar a{
				padding-right: 13px;
			}
	
			/*adds border top to first nav box */
			nav.sidebar .navbar-nav > li:first-child{
				border-top: 1px #e5e5e5 solid;
			}
	
			/*adds border to bottom nav boxes*/
			nav.sidebar .navbar-nav > li{
				border-bottom: 1px #e5e5e5 solid;
			}
	
			/* Colors/style dropdown box*/
			nav.sidebar .navbar-nav .open .dropdown-menu {
				position: static;
				float: none;
				width: auto;
				margin-top: 0;
				background-color: transparent;
				border: 0;
				-webkit-box-shadow: none;
				box-shadow: none;
			}
	
			/*allows nav box to use 100% width*/
			nav.sidebar .navbar-collapse, nav.sidebar .container-fluid{
				padding: 0 0px 0 0px;
			}
	
			/*colors dropdown box text */
			.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
				color: #777;
			}
	
			/*gives sidebar width/height*/
			nav.sidebar{
				width: 200px;
				height: 100%;
				margin-left: -160px;
				float: left;
				z-index: 8000;
				margin-bottom: 0px;
			}
	
			/*give sidebar 100% width;*/
			nav.sidebar li {
				width: 100%;
			}
	
			/* Move nav to full on mouse over*/
			nav.sidebar:hover{
				margin-left: 0px;
			}
			/*for hiden things when navbar hidden*/
			.forAnimate{
				opacity: 0;
			}
		}
	
		/* .....NavBar: Fully showing nav bar..... */
	
		@media (min-width: 1330px) {
	
			/*Allow main to be next to Nav*/
			.main{
				width: calc(100% - 200px); /*keeps 100% minus nav size*/
				margin-left: 200px;
			}
	
			/*Show all nav*/
			nav.sidebar{
				margin-left: 0px;
				float: left;
			}
			/*Show hidden items on nav*/
			nav.sidebar .forAnimate{
				opacity: 1;
			}
		}
	
		nav.sidebar .navbar-nav .open .dropdown-menu>li>a:hover, nav.sidebar .navbar-nav .open .dropdown-menu>li>a:focus {
			color: #CCC;
			background-color: transparent;
		}
	
		nav:hover .forAnimate{
			opacity: 1;
		}
		section{
			padding-left: 15px;
		}	
	</style>
	
	<script type="text/javascript">
	
	    function htmlbodyHeightUpdate(){
			var windowHeight = $( window ).height()
			var navHeight = $('.nav').height()+50
			mainHeight = $('.main').height()
			if(mainHeight > windowHeight){
				$('html').height(Math.max(navHeight,windowHeight,mainHeight)+10);
				$('body').height(Math.max(navHeight,windowHeight,mainHeight)+10);
			}
			else
			{
				$('html').height(Math.max(navHeight,windowHeight,mainHeight));
				$('body').height(Math.max(navHeight,windowHeight,mainHeight));
			}
		}
	    
		$(document).ready(function () {
			htmlbodyHeightUpdate()
			$( window ).resize(function() {
				htmlbodyHeightUpdate()
			});
			$( window ).scroll(function() {
				mainHeight = $('.main').height()
	  			htmlbodyHeightUpdate()
			});
		});
		
	</script>
</head>

<body>
	
	
    
	<nav class="navbar navbar-inverse sidebar" role="navigation">
    	<div class="container-fluid">
    		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Cron任务平台</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li ><a class="active" href="/rapidcron/cronclient/index.do">CronClient<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
					<li ><a href="/rapidcron/crontasklog/index.do">CronTaskLog<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				</ul>
			</div>
		</div>
	</nav>
		
    <div class="main">
		<%@ include file="/commons/messages.jsp"  %>
		<rapid:block name="content"/>
    </div>
	
</body>

</html>