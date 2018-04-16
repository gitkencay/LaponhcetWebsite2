package com.laponhcet.util;

import java.io.Serializable;
import java.util.List;

import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class FeeStudentSpecificUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String[] getFeeStudentSpecificListArr(List<DTOBase> feeStudentSpecificList, int columnCount) {
		String[] strArr = new String[feeStudentSpecificList.size() * columnCount];
		int i=-1;
		for(DTOBase feeStudentSpecificObj:feeStudentSpecificList) {
			FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) feeStudentSpecificObj;
			strArr[++i] = WebUtil.getTableDataFormat(feeStudentSpecific.getFee().getName(), feeStudentSpecific.getRecordStatus());
			strArr[++i] = WebUtil.getTableDataFormat(StringUtil.getFormattedNum(feeStudentSpecific.getAmount(), StringUtil.NUMERIC_STANDARD_FORMAT), feeStudentSpecific.getRecordStatus());
			if(feeStudentSpecific.getRecordStatus().equalsIgnoreCase(DTOBase.RECORD_STATUS_REMOVE)) {
				strArr[++i] = "<i class='btn btn-warning fa fa-undo' onclick=\"recordAction(" + feeStudentSpecific.getId() + ",'US0152')\"></i>";
			}
			else {
				strArr[++i] = "<i class='btn btn-danger fa fa-minus-circle' onclick=\"recordAction(" + feeStudentSpecific.getId() + ",'US0152')\"></i>";
			}
		}
		return strArr;
	}
	
	public static void setFeeStudentSpecific(List<DTOBase> feeStudentSpecificList, List<DTOBase> feeList, StudentDTO student) {
		for(DTOBase feeStudentSpecificObj: feeStudentSpecificList) {
			FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) feeStudentSpecificObj;
			feeStudentSpecific.setStudent(student);
			feeStudentSpecific.setFee((FeeDTO) DTOUtil.getObjByCode(feeList, feeStudentSpecific.getFee().getCode()));
		}
	}
}
