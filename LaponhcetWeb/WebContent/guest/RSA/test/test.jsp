<%@ page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	QuestionnaireGroupDTO questionnaireGroup = (QuestionnaireGroupDTO) session.getAttribute(QuestionnaireGroupDTO.SESSION_QUESTIONNAIRE_GROUP);
	List<DTOBase> questionnaireList = (ArrayList<DTOBase>) session.getAttribute(QuestionnaireDTO.SESSION_QUESTIONNAIRE_LIST);
%>

<div class="col-sm-12"><h1><%=questionnaireGroup.getName()%></h1></div>
<div class="col-sm-12"><h2><%=questionnaireGroup.getRemarks()%></h2></div>
<div class="col-sm-12"><font color="red">*</font>Mandatory</div>
<%
for(int i=0; i<questionnaireList.size(); i++) {
	QuestionnaireDTO questionnaire = (QuestionnaireDTO) questionnaireList.get(i);
%>
	<div class="col-sm-12">
	    <div class="widget navy-bg no-padding">
	        <div class="p-m">
	            <h3 class="m-xs"><%=questionnaire.isMandatory()?"<font color='red'>*&nbsp;</font>":""%><%=i+1%>.)&nbsp;<%=questionnaire.getQuestion()%></h3>
	        </div>
	    </div>
	</div>
	<div class="ibox">
	<%
	if(questionnaire.getOptionType().equalsIgnoreCase(QuestionnaireDTO.QUESTIONNAIRE_OPTION_TYPE_FREEFORMAT)) {
	%>
		<div class="col-sm-12">
	<%
		if(sessionInfo.isCurrentLinkDataEntry()) {
	%>
			<input type="text" class="form-control" name="<%=questionnaire.getCode()%>" id="<%=questionnaire.getCode()%>" value="<%=questionnaire.getAnswer()%>" />
	<%		
		}
		else {
	%>
			<%=questionnaire.getAnswer()%>
	<%			
		}
	%>
		</div>
	<%	
	}
	else {
		for(int j=0; j<questionnaire.getQuestionnaireOptionList().size(); j++) {
			QuestionnaireOptionDTO questionnaireOption = (QuestionnaireOptionDTO) questionnaire.getQuestionnaireOptionList().get(j);
	%>
    	<div class="col-lg-2 text-justify">
    	<%
    		if(!sessionInfo.isCurrentLinkDataEntry() && StringUtil.isStrExistInStrArr(questionnaire.getAnswer().split("~"), questionnaireOption.getCode())) {
    	%>
    		<div class="widget style1 yellow-bg">
    	<%	
    		}
    		else {
    	%>
    		<div class="widget style1">
    	<%	
    		}
    	%>
             	<div class="row vertical-align">
             		<h3>
        <%
            if(sessionInfo.isCurrentLinkDataEntry()) {
        %>
        				<div>
        <%    	
            	if(questionnaire.getOptionType().equalsIgnoreCase(QuestionnaireDTO.QUESTIONNAIRE_OPTION_TYPE_UNI)) {
       	%>
                 			
                 			<input name="<%=questionnaire.getCode()%>" id="<%=questionnaire.getCode()%>" value="<%=questionnaireOption.getCode()%>" <%=StringUtil.isStrExistInStrArr(questionnaire.getAnswer().split("~"), questionnaireOption.getCode())?"checked":""%> type="radio">
       	<%
             	}
             	else if(questionnaire.getOptionType().equalsIgnoreCase(QuestionnaireDTO.QUESTIONNAIRE_OPTION_TYPE_MULTI)) {
       	%>
                    		<input name="<%=questionnaireOption.getCode()%>" id="<%=questionnaireOption.getCode()%>" value="<%=questionnaireOption.getCode()%>" <%=StringUtil.isStrExistInStrArr(questionnaire.getAnswer().split("~"), questionnaireOption.getCode())?"checked":""%> type="checkbox">
   		<%
           		}
			}
            else {
    	%>
    					<div class="col-md-2 col-md-offset-5">
    	<%
            }
    	%>
                			<%=questionnaireOption.getDescription()%>   		
                		</div>
                	</h3>     
               	</div>
     		</div>
    	</div>
	<%	
		}
%>
	</div>
<%		
	}
}
%>
<div class="col-sm-12">
	<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary", "align='center'") %>
</div>