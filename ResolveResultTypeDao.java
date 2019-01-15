package com.esl.teas.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.User;
import com.esl.teas.form.ResolveResultTypeForm;
import com.esl.teas.form.UserForm;
import com.esl.teas.mapper.UserMapper;

/*
 * 用户Dao接口。
 * 
 * @author luke
 */
public interface ResolveResultTypeDao {
	 
  	public ArrayList<ResolveResultType> list(ResolveResultTypeForm resolveResultTypeForm);

	public  ArrayList<ResolveResultType> getParentInfo(String parent_id);
	
	public ArrayList<ResolveResultType>  list_child_type(ResolveResultTypeForm resolveResultTypeForm);

	public int insert(ResolveResultTypeForm resolveResultTypeForm);

	public int update(ResolveResultTypeForm resolveResultTypeForm);

	public int delete(ResolveResultTypeForm resolveResultTypeForm);
	
	 
}
