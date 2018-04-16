<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mytechnopal.*"%>
<%@ page import="com.mytechnopal.dto.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@ page import="com.laponhcet.util.*"%>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	ActionResponse actionResponse = (ActionResponse) session.getAttribute(ActionResponse.SESSION_ACTION_RESPONSE);
	UserDTO user = (UserDTO)sessionInfo.getCurrentUser();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=SettingsUtil.OWNER_NAME%> | <%=sessionInfo.getCurrentLink().getDescription()%></title>

<!-- TechnoPal -->
<script src="common/common.js"></script>

<!-- Main scripts -->
<link href="/static/inspinia/css/custom.css" rel="stylesheet">
<link href="/static/inspinia/css/bootstrap.min.css" rel="stylesheet">
<script src="/static/inspinia/js/jquery-3.1.1.min.js"></script>
<script src="/static/inspinia/js/bootstrap.min.js"></script>
<link href="/static/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">
<script src="/static/webcam/webcam.js" type="text/javascript"></script>

<%
if(sessionInfo.getCurrentLink().getCode().equalsIgnoreCase(LinkDTO.LINK_CODE_FACELOG)) {
%>
	<!-- FaceKeeper -->
	<jsp:include flush="true" page="facekeeper/css.jsp"></jsp:include>
	<!-- FaceKeeper -->
<%
} 
else { 
%>
	<link href="/static/inspinia/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="/static/inspinia/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
	<link href="/static/inspinia/css/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
	<link
		href="/static/inspinia/css/plugins/colorpicker/bootstrap-colorpicker.min.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/cropper/cropper.min.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/switchery/switchery.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/jasny/jasny-bootstrap.min.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/nouslider/jquery.nouislider.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/datapicker/datepicker3.css"
		rel="stylesheet">
	
	<link
		href="/static/inspinia/css/plugins/ionRangeSlider/ion.rangeSlider.css"
		rel="stylesheet">
	<link
		href="/static/inspinia/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css"
		rel="stylesheet">
	
	<link
		href="/static/inspinia/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/clockpicker/clockpicker.css"
		rel="stylesheet">
	
	<link
		href="/static/inspinia/css/plugins/daterangepicker/daterangepicker-bs3.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/sweetalert/sweetalert.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/plugins/select2/select2.min.css"
		rel="stylesheet">
	
	<link
		href="/static/inspinia/css/plugins/touchspin/jquery.bootstrap-touchspin.min.css"
		rel="stylesheet">
	
	<link
		href="/static/inspinia/css/plugins/dualListbox/bootstrap-duallistbox.min.css"
		rel="stylesheet">
	
	<link href="/static/inspinia/css/animate.css" rel="stylesheet">
	<link href="/static/inspinia/css/style.css" rel="stylesheet">
	<link
		href="/static/inspinia/css/plugins/blueimp/css/blueimp-gallery.min.css"
		rel="stylesheet">
		
	<!-- Full Calendar -->
	<link href="/static/inspinia/css/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
	<link href="/static/inspinia/css/plugins/fullcalendar/fullcalendar.print.css" rel='stylesheet' media='print'>
	 
	<link href="/static/inspinia/css/plugin/slick/slick.css" rel="stylesheet">
	<link href="/static/inspinia/css/plugin/slick/slick-theme.css" rel="stylesheet">
	  
	<!-- footer-->
	<link rel="stylesheet" href="/static/inspinia/footer/css/Footer-with-button-logo.css">
	
	<!-- SummerNote -->
	<link href="/static/inspinia/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/static/inspinia/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	
	<!-- SUMMERNOTE -->
    <script src="/static/inspinia/js/plugins/summernote/summernote.min.js"></script>
	            
	<!-- Custom and plugin javascript -->
	<script src="/static/inspinia/js/inspinia.js"></script>
	<script src="/static/inspinia/js/plugins/pace/pace.min.js"></script>
	<script src="/static/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	
	<!-- Chosen -->
	<script src="/static/inspinia/js/plugins/chosen/chosen.jquery.js"></script>
	
	<!-- SweetAlert -->
	<script src="/static/inspinia/js/plugins/sweetalert/sweetalert.min.js"></script>
	
	<!-- JSKnob -->
	<script src="/static/inspinia/js/plugins/jsKnob/jquery.knob.js"></script>
	
	<!-- Input Mask-->
	<script src="/static/inspinia/js/plugins/jasny/jasny-bootstrap.min.js"></script>
	
	<!-- Data picker -->
	<script
		src="/static/inspinia/js/plugins/datapicker/bootstrap-datepicker.js"></script>
	
	<!-- NouSlider -->
	<script
		src="/static/inspinia/js/plugins/nouslider/jquery.nouislider.min.js"></script>
	
	<!-- Switchery -->
	<script src="/static/inspinia/js/plugins/switchery/switchery.js"></script>
	
	<!-- IonRangeSlider -->
	<script
		src="/static/inspinia/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
	
	<!-- iCheck -->
	<script src="/static/inspinia/js/plugins/iCheck/icheck.min.js"></script>
	
	<!-- MENU -->
	<script src="/static/inspinia/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	
	<!-- Color picker -->
	<script
		src="/static/inspinia/js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	
	<!-- Clock picker -->
	<script src="/static/inspinia/js/plugins/clockpicker/clockpicker.js"></script>
	
	<!-- Image cropper -->
	<script src="/static/inspinia/js/plugins/cropper/cropper.min.js"></script>
	
	<!-- Date range use moment.js same as full calendar plugin -->
	<script src="/static/inspinia/js/plugins/fullcalendar/moment.min.js"></script>
	
	<!-- Date range picker -->
	<script
		src="/static/inspinia/js/plugins/daterangepicker/daterangepicker.js"></script>
	
	<!-- Select2 -->
	<script src="/static/inspinia/js/plugins/select2/select2.full.min.js"></script>
	
	<!-- TouchSpin -->
	<script
		src="/static/inspinia/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js"></script>
	
	<!-- Tags Input -->
	<script
		src="/static/inspinia/js/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
	
	<!-- Dual Listbox -->
	<script
		src="/static/inspinia/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
	
	<!-- blueimp gallery -->
	<script
		src="/static/inspinia/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<%
}
%>
</head>
<%
if(sessionInfo.getCurrentLink().getCode().equalsIgnoreCase(LinkDTO.LINK_CODE_FACELOG)) {
%>
<body onload="refreshAt(1,0,0);">
<%
}
else {
%>
<body <%=user.getUserGroupCodes().equalsIgnoreCase(UserGroupDTO.USER_GROUP_GUEST_CODE)?"id='page-top' class='landing-page no-skin-config'":""%>>
<%	
}
%>
	<form id="frmMain" name="frmMain" method="post" action="MainController"
		onSubmit="return false" style="margin: 0; padding: 0; outline: 0">
		<input id="txtSelectedLink" name="txtSelectedLink" type="hidden" value="" />
		<%
if(user.getUserGroupCodes().equalsIgnoreCase(UserGroupDTO.USER_GROUP_GUEST_CODE)) {
	if(sessionInfo.getCurrentLink().getCode().equalsIgnoreCase(LinkDTO.LINK_CODE_FACELOG)) { //FaceKeeper
%>
		<div class="row">
			<jsp:include flush="true" page="<%=sessionInfo.getCurrentLink().getPage()%>"></jsp:include>
		</div>
		<%		
	}
	else { 
		String guestHomePage = "guest/" + SettingsUtil.OWNER_CODE + "/home.jsp";
%>
		<jsp:include flush="true" page="<%=guestHomePage%>"></jsp:include>
<%
	}
}
else {
%>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <img alt="image" class="img-circle"
								src="/static/inspinia/img/profile_small.jpg" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear"> <span class="block m-t-xs">
										<strong class="font-bold"><%=user.getName(true, true, true) %></strong>
								</span> <span class="text-muted text-xs block">Administrator <b
										class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="profile.html">Profile</a></li>
								<li><a href="contacts.html">Contacts</a></li>
								<li><a href="mailbox.html">Mailbox</a></li>
								<li class="divider"></li>
								<li><a href="#" onClick="openLink('U00002')">Logout</a></li>
							</ul>
						</div>
						<div class="logo-element"><%=SettingsUtil.OWNER_CODE%></div>
					</li>
				<%
            	for(int i=0; i<sessionInfo.getCurrentUserMainMenuLinkList().size(); i++) {
					LinkDTO mainMenuLink = (LinkDTO) sessionInfo.getCurrentUserMainMenuLinkList().get(i);
					List subMenuLinkList = sessionInfo.getCurrentUserSubMenuLinkListByMainGroup(mainMenuLink);
				%>
					<li class="<%=mainMenuLink.getMainGroup().equalsIgnoreCase(sessionInfo.getCurrentLink().getMainGroup())?"active":""%>">
						<a href="#"><i class="<%=mainMenuLink.getImgSource()%>"></i>
							<span class="nav-label"><%=mainMenuLink.getLabel()%></span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
				<%
               		String subMenu = "";
	        		for(int j=0; j<subMenuLinkList.size(); j++) {
						LinkDTO subMenuLink = (LinkDTO)subMenuLinkList.get(j);
						subMenu = subMenuLink.getLabel();
               %>
							<li class="<%=subMenuLink.getCode().equalsIgnoreCase(sessionInfo.getCurrentLink().getCode())?"active":""%>">
								<a href="#" onclick="openLink('<%=subMenuLink.getCode()%>')">
								<i class="<%=subMenuLink.getCode().equalsIgnoreCase(sessionInfo.getCurrentLink().getCode())?"fa fa-square":"fa fa-square-o"%>"></i><%=subMenu%></a>
							</li>
				<%		
	        		}
	        	%>
						</ul>
					</li>
				<%	
            	}
            	%>
				</ul>
			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">Welcome
								to <%=SettingsUtil.OWNER_NAME%></span></li>
						<li><a href="#" onClick="openLink('U00002')"> <i
								class="fa fa-sign-out"></i> Log out
						</a></li>
					</ul>

				</nav>
			</div>
			<div class="row white-bg">
				<div class="col-sm-12">
					<h1 class="m-b-xs"><%=sessionInfo.getCurrentLink().getDescription()%></h1>
					<jsp:include flush="true" page="<%=sessionInfo.getCurrentLink().getPage()%>"></jsp:include>
				</div>	
			</div>
			<div class="row footer">
	        	<div class="pull-right">
	                  Build <strong>1.0</strong>
	            </div>
	            <div>
	            	<%=SettingsUtil.FOOTER%>
	            </div>
	      	</div>
		</div>
	</div>
	<%
	}
	%>
	</form>
	<%
	if(!StringUtil.isEmpty(actionResponse.getMessage())) {
	%>
		<script>
			swal("<%=actionResponse.getMessageSWALTitle()%>", "<%=actionResponse.getMessage()%>", "<%=actionResponse.getMessageSWALType()%>");
		</script>
		<%	
	}
	%>
	<div class="modal fade" id="divDialogLoading" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" align="center" style="position:relative; top:30%; transform:translate(0,30%);">
	        <%=SettingsUtil.OWNER_NAME %> <br>
	        <img height="100px" width="100px" src='/static/common/images/loading.gif'></img>	
	        <br>B Y T E C H N O P A L
	    </div>
	</div>
</body>
</html>