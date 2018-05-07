<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.dao.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination)session.getAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
	MessageDTO messageSelected = (MessageDTO) session.getAttribute(MessageDTO.SESSION_MESSAGE);
%>

<div class="ibox float-e-margins">
	<div class="ibox-content">
     	<div class="row">
     		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Show Entries", "RecordPerPage", new String[] {"10", "20", "50", "100"}, "10", new String[] {"10", "20", "50", "100"}, "", "", "onChange=\"showPagination('" + Pagination.PAGINATION_ACTION_lIMIT + "', this.value);\"")%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Search Criteria", "SearchCriteria",  MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST, MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0], MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST, "", "", "onChange=\"getSearchCriteria(this.value);\"")%>
			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", false, "Search Value", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"setSearchValue(this.value);\"") %>
	      	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6 m-b", false, "Message Type", "MessageType",  MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST, MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0], MessageDTO.SCHOOL_MESSAGE_TYPE_LIST, "", "", "onChange=\"setSearchValue(this.value);\"")%>
	      	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6 m-b", false, "Medium", "Medium",  MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST, MessageDTO.SCHOOL_MESSAGE_MEDIUM_LIST[0], MessageDTO.SCHOOL_MESSAGE_MEDIUM_LIST, "", "", "onChange=\"setSearchValue(this.value);\"")%>
	      	<%=new PaginationWebControl().getPaginationWebControl(sessionInfo, "col-sm-12", pagination, messageSelected.getId()) %>		
		</div>
    </div>
</div>

<script>
getSearchCriteria("<%=MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]%>");
function getSearchCriteria(searchCriteria){
	document.getElementById("txtSearchValue").value = "";
	if(searchCriteria=="<%=MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]%>"){
		hideSearchElements();
		$("div.col-sm-6:nth-child(3)").show();
		searchPagination("");
	}else if(searchCriteria=="<%=MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[1]%>"){
		hideSearchElements();
		$("div.col-sm-6:nth-child(4)").show();
		setSearchValue("<%=MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0]%>");
	}else if(searchCriteria=="<%=MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[2]%>"){
		hideSearchElements();
		$("div.col-sm-6:nth-child(5)").show();
		setSearchValue("<%=MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST[0]%>");
	}
}

function hideSearchElements(){
	$("div.col-sm-6:nth-child(3)").hide();
	$("div.col-sm-6:nth-child(4)").hide();
	$("div.col-sm-6:nth-child(5)").hide();
}

function setSearchValue(searchValue){
	document.getElementById("txtSearchValue").value = "";
	document.getElementById("txtSearchValue").value = searchValue;
	searchPagination(searchValue);
}

</script>