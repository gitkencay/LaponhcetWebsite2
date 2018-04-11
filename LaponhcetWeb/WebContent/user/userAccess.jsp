<%@ page import="java.util.*"%>
<%@ page import="com.mytechnopal.*"%>
<%@ page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.dto.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@ page import="com.mytechnopal.webcontrol.*"%>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	List<DTOBase> userGroupList = (ArrayList<DTOBase>) session.getAttribute(UserGroupDTO.SESSION_USER_GROUP_LIST);
	UserDTO userAccess = (UserDTO)session.getAttribute(UserDTO.SESSION_USER + "_ACCESS");
%>
<div class="col-lg-12">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
		<input type="hidden" name="txtCheckedLinks" id="txtCheckedLinks" />
			<div class="row">
				<div class="col-sm-12">
					<strong>Name: <%=userAccess.getName(false, false, true)%></strong>
				</div>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 ", true, "Username", "Username", "UserName", userAccess.getUserName(), 16, WebControlBase.DATA_TYPE_STRING, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Password", "Password", "Password", userAccess.getPassword(), 16, WebControlBase.DATA_TYPE_STRING, "") %>
				<%//=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3 m-b", true, "Group", "UserGroup", userGroupList, userAccess.getUserGroupCodes(), "", "", "") %>
				<%=new RadioButtonWebControl().getRadioButtonWebControl(sessionInfo, "col-sm-3", true, "Status", "Status", new String[] {"Active", "Inactive"}, userAccess.isActive()?"Active":"Inactive", new String[] {"Active", "Inactive"}, "", false) %>
				<div class="col-sm-12">
					<div class="panel panel-default">
						<%
				int subLinkCtr = 0;
				for(int i=0; i<sessionInfo.getMainLinkList().size(); i++) {
					LinkDTO mainLink = (LinkDTO)sessionInfo.getMainLinkList().get(i);
					 //Login 
					LinkDTO previousMainLink = null;
					LinkDTO nextMainLink = null;
					if(i >= 1) {
						previousMainLink = (LinkDTO)sessionInfo.getMainLinkList().get(i-1);
					}
					if(i < sessionInfo.getMainLinkList().size()-1) {
						nextMainLink = (LinkDTO)sessionInfo.getMainLinkList().get(i+1);
					}
					
					String linkGroup = "";
					boolean isParentLinkChecked = false;
					for(LinkDTO link: sessionInfo.getParentMainLinkListByParentLinkGroup(mainLink.getLinkGroup())) {
						linkGroup += link.getCode() + "~";
					}
					if(sessionInfo.isLinkExist(userAccess.getUserLinkList(), mainLink)) {
						isParentLinkChecked = true;
					}
					
					if(mainLink.isMainMenu()) {	
			%>
					<div class="panel-heading">
			<%
						
						if(sessionInfo.isCurrentLinkDataEntry()) {								
			%>							
							<strong> <input type="checkbox" onclick="toggleCheckLinkGroup('<%=mainLink.getCode()%>', '<%=StringUtil.getLeft(linkGroup, linkGroup.length()-1) %>')" id="chk<%=mainLink.getCode()%>" name="chk<%=mainLink.getCode()%>" value="<%=mainLink.getCode()%>" style="vertical-align:top" <%=isParentLinkChecked?"checked":""%> >&nbsp;&nbsp;
							<%=mainLink.getLabel()%>
							</strong>
			<%
						}	
						else {
			%>
							<strong>&nbsp;<%=mainLink.getLabel()%></strong>
			<%				
						}
			%>
					</div>
					<%	
					}
					else {
						if(previousMainLink != null && previousMainLink.isMainMenu()) {
			%>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
								</thead>
								<tbody>

									<%
						}
						subLinkCtr++;
						if(subLinkCtr == 1) {
			%>
									<tr>
										<%									
						}
						boolean hasLink = false;
						if(sessionInfo.isLinkExist(userAccess.getUserLinkList(), mainLink)) {
							hasLink = true;
						}
			%>
			
			
			
			
			<%			
						
						
						if(sessionInfo.isCurrentLinkDataEntry()) {
			%>				
										<td <%=hasLink?"style='background-color: #c9fbc9;'":"" %>>&nbsp;&nbsp;&nbsp;<input type="checkbox"
											id="chk<%=mainLink.getCode()%>" name="chkUserLink" 
											 value="<%=mainLink.getCode()%>" onclick="untoggleParentLink($(this).attr('value'), '<%=StringUtil.getLeft(linkGroup, linkGroup.length()-1) %>');" <%=hasLink?"checked":""%>>&nbsp;<%=mainLink.getLabel()%></td>
										<%				
						}	
						else {
							String iconStr = hasLink?"glyphicon glyphicon-ok":"glyphicon glyphicon-remove";
			%>
										<td <%=hasLink?"style='background-color: #c9fbc9;'":"" %>><span class="<%=iconStr%>" aria-hidden="true"></span>&nbsp;<%=mainLink.getLabel()%></td>
										<%				
						}
			%>
										<%
						if(i == sessionInfo.getMainLinkList().size()-1 || (nextMainLink != null && nextMainLink.isMainMenu()) || subLinkCtr == 5){
							subLinkCtr = 0;
			%>
									</tr>
									<%			
						}
						if(i == sessionInfo.getMainLinkList().size()-1 || (nextMainLink != null && nextMainLink.isMainMenu())) {
							subLinkCtr = 0;
			%>

								</tbody>
							</table>
						</div>
					</div>
					<%		
						}
					}
				}
				%>
					</div>
				</div>
				<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %>

				<script>
					$(function(){
						$('input:checkbox').click(function() {
							getlinkGroupChecked();
					    });
					});
				</script>

				<script>
					function getlinkGroupChecked(){
				        var str = [];
			            $.each($("input:checkbox:checked"), function(){            
			            	str.push($(this).val());
			            });
			            document.getElementById("txtCheckedLinks").value = str.join("~");
					}
				</script>

				<script>
					function untoggleParentLink(childLink, linkGroupParent){
						var childrenLinkArray = linkGroupParent.split('~');
						var checkCount = 0;
						$.each(childrenLinkArray, function(index, value){
							if($("#chk"+value).is(':checked') && index!=0){
								checkCount++;
							}
				        });
						if(checkCount < childrenLinkArray.length - 1){
							$('#chk'+childrenLinkArray[0]).prop('checked', false);
						}else{
							$('#chk'+childrenLinkArray[0]).prop('checked', true);
						}
						getlinkGroupChecked();
					}	
				</script>

				<script>	
					function toggleCheckLinkGroup(linkGroupParent, linkGroupChildren) {
						var parentLink = "#chk" + linkGroupParent + ":checkbox";
						var childrenLinkArray = linkGroupChildren.split('~');
						$(parentLink).change(function() {
						    if(this.checked) {
						        $.each(childrenLinkArray, function(index, value){
						        	$('#chk'+value).prop('checked', true);
						        });
						    }else{
						    	 $.each(childrenLinkArray, function(index, value){
						        	$('#chk'+value).prop('checked', false);
						        });
						    }
						    getlinkGroupChecked();
						});
					}
				</script>
			</div>
		</div>
	</div>
</div>
