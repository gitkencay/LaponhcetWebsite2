package com.laponhcet.action.fee;

import java.util.List;

import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;

public class AddFeeStudentSpecificFeeSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
	protected void executeLogic() {
		List<DTOBase> feeStudentSpecificList = (List<DTOBase>) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
		FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC);
		feeStudentSpecific.setFee((FeeDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST), getRequestInt("cboFee"))); 
		feeStudentSpecific.setAmount(getRequestDouble("txtAmount"));
		feeStudentSpecific.setAddedBy(((UserDTO) getSessionAttribute(UserDTO.SESSION_USER)).getCode());
		//method to make the id negative
		feeStudentSpecific.setId(feeStudentSpecific.getFee().getId() - (feeStudentSpecific.getFee().getId()*2));
		feeStudentSpecificList.add(feeStudentSpecific.getFeeStudentSpecific());		
	}
}
