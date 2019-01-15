package com.esl.teas.service;

import java.util.ArrayList;

import com.esl.teas.entity.DefectProcess;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.User;
import com.esl.teas.form.DefectProcessForm;
import com.esl.teas.form.ResolveResultTypeForm;


/*
 * 用户权限服务接口。
 * 
 * @author luke
 */
public interface ResolveResultTypeService {
	public ArrayList<ResolveResultType> list(ResolveResultTypeForm resolveResultTypeForm);
	public ArrayList<ResolveResultType>  getParentInfo(String parent_id);
	public ArrayList<ResolveResultType>  list_child_type(ResolveResultTypeForm resolveResultTypeForm);
	public int insert(ResolveResultTypeForm resolveResultTypeForm);
	public int update(ResolveResultTypeForm resolveResultTypeForm);
	public int delete(ResolveResultTypeForm resolveResultTypeForm);
	 
	
 
}
