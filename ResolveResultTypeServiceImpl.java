package com.esl.teas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esl.teas.dao.ResolveResultTypeDaoImpl;

import com.esl.teas.dao.UserDaoImpl;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.User;
import com.esl.teas.form.ResolveResultTypeForm;
import com.esl.teas.form.UserForm;

/*
 * 登录服务接口实现类。
 * 
 * @author luke
 */
@Component
public class ResolveResultTypeServiceImpl implements ResolveResultTypeService {

	@Autowired
	ResolveResultTypeDaoImpl resolveResultTypeDao;

	@Override
	public ArrayList<ResolveResultType> list(ResolveResultTypeForm resolveResultTypeForm) {

		ArrayList<ResolveResultType> resolveResultTypeList = new ArrayList<ResolveResultType>();
		resolveResultTypeList = resolveResultTypeDao.list(resolveResultTypeForm);

		return resolveResultTypeList;
	}

	
	public ArrayList<ResolveResultType> list_re_result(ResolveResultTypeForm resolveResultTypeForm) {

		ArrayList<ResolveResultType> resolveResultTypeList = new ArrayList<ResolveResultType>();
		resolveResultTypeList = resolveResultTypeDao.list_re_result( resolveResultTypeForm);

		return resolveResultTypeList;
	}
	public ArrayList<ResolveResultType> list_child_type(ResolveResultTypeForm resolveResultTypeForm) {

		ArrayList<ResolveResultType> resolveResultTypeList = new ArrayList<ResolveResultType>();
		resolveResultTypeList = resolveResultTypeDao.list_child_type( resolveResultTypeForm);

		return resolveResultTypeList;
	}
	 
	@Override
	public ArrayList<ResolveResultType> getParentInfo(String parent_id) {
		// TODO Auto-generated method stub
		ArrayList<ResolveResultType> resolveResultTypeList = new ArrayList<ResolveResultType>();
		resolveResultTypeList = resolveResultTypeDao.getParentInfo(parent_id);
		return resolveResultTypeList;
	}

	@Override
	public int insert(ResolveResultTypeForm resolveResultTypeForm) {

		int result = resolveResultTypeDao.insert(resolveResultTypeForm);

		return result;
	}

	@Override
	public int update(ResolveResultTypeForm resolveResultTypeForm) {

		int result = resolveResultTypeDao.update(resolveResultTypeForm);

		return result;
	}

	@Override
	public int delete(ResolveResultTypeForm resolveResultTypeForm) {

		int result = resolveResultTypeDao.delete(resolveResultTypeForm);

		return result;
	}
	
 

}
