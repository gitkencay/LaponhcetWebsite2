<%@page import="com.mytechnopal.dao.LinkDAO"%>
<%@page import="com.mytechnopal.base.WebControlBase"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import=" java.util.regex.Pattern" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	MessageDTO message = (MessageDTO) session.getAttribute(MessageDTO.SESSION_MESSAGE);
%>

<%=new CheckBoxWebControl().getCheckBoxWebControl(sessionInfo, "col-sm-12", true, "Type", "MessageTypeCodes", MessageDTO.SCHOOL_MESSAGE_TYPE_LIST, message.getMessageTypeCodes().split("~"), MessageDTO.SCHOOL_MESSAGE_TYPE_LIST, "") %>
<div class="ibox-content no-padding">
	<div class="summernote"></div>
</div>
                                        
 <script>
    $(document).ready(function(){
        $('.summernote').summernote();
        $('.note-editable.panel-body').css("height", "200px");
		$('.note-editable.panel-body').css("min-height", "200px");
   });
</script>
                        