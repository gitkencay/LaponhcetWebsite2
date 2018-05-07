<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.LinkDTO" %>
<%@ page import="com.mytechnopal.dto.UserDTO" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination)session.getAttribute(RegisterDTO.SESSION_REGISTER_PAGINATION);
	RegisterDTO registerSelected = (RegisterDTO)session.getAttribute(RegisterDTO.SESSION_REGISTER);
%>

<div class="ibox float-e-margins">
	<div class="ibox-content">
     	<div class="row">
      		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Show Entries", "RecordPerPage", new String[] {"10", "20", "50", "100"}, "10", new String[] {"10", "20", "50", "100"}, "", "", "onChange=\"showPagination('" + Pagination.PAGINATION_ACTION_lIMIT + "', this.value);\"")%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Search Criteria", "SearchCriteria",  RegisterDTO.PAGINATION_SEARCH_CRITERIA_LIST, "Name", RegisterDTO.PAGINATION_SEARCH_CRITERIA_LIST, "", "", "")%>
			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", false, "Search Value", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value);\"") %>
	      	<%=new PaginationWebControl().getPaginationWebControlList(sessionInfo, "col-sm-12", pagination, registerSelected.getId(), "", false) %>
		</div>
     </div>
 </div>  
	      	