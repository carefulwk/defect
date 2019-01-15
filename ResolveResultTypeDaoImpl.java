package com.esl.teas.dao;

/*
 * 用户Dao接口实现。用于用户权限增删改查。
 * 
 * @author Xuyh
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.User;
import com.esl.teas.form.ResolveResultTypeForm;
import com.esl.teas.form.UserForm;
import com.esl.teas.mapper.ResolveResultTypeMapper;
import com.esl.teas.mapper.UserMapper;

@Component
public class ResolveResultTypeDaoImpl implements ResolveResultTypeDao {

	// public ArrayList<ResolveResultType> list(ResolveResultTypeForm
	// resolveResultTypeForm) {
	// // TODO Auto-generated method stub
	// SqlSession session = null;
	// ResolveResultType resolveResultType =
	// this.transToResolveResultType(resolveResultTypeForm);
	// ArrayList<ResolveResultType> dbResolveInfoList = new
	// ArrayList<ResolveResultType>();
	// ArrayList<User> outputUserInfoList = new ArrayList<User>();
	// return null;
	// }

	@Override
	public ArrayList<ResolveResultType> list(ResolveResultTypeForm resolveResultTypeForm) {

		SqlSession session = null;
		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		ArrayList<ResolveResultType> dbResolveInfoList = new ArrayList<ResolveResultType>();
		ArrayList<ResolveResultType> outputResolveInfoList = new ArrayList<ResolveResultType>();

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();

			// 保存用户权限信息
			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			dbResolveInfoList = rrt_mapper.list(resolveResultType);

			// postgresql版本太低，无法使用8.4版本增加序号，只能手动添加。
			Iterator<ResolveResultType> iter = dbResolveInfoList.iterator();
			int objectNo = 1;
			while (iter.hasNext()) {
				ResolveResultType temp_resolveResultType = iter.next();
				temp_resolveResultType.setObjectNo(Integer.valueOf(objectNo).toString());
				outputResolveInfoList.add(temp_resolveResultType);
				objectNo++;
			}

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return outputResolveInfoList;

	}
	
	public ArrayList<ResolveResultType> list_re_result(ResolveResultTypeForm resolveResultTypeForm) {

		SqlSession session = null;
		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		ArrayList<ResolveResultType> dbResolveInfoList = new ArrayList<ResolveResultType>();

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();

			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			dbResolveInfoList = rrt_mapper.list_re_result(resolveResultType);

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return dbResolveInfoList;

	}
	
	
	public ArrayList<ResolveResultType> list_child_type(ResolveResultTypeForm resolveResultTypeForm) {

		SqlSession session = null;
		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		ArrayList<ResolveResultType> dbResolveInfoList = new ArrayList<ResolveResultType>();

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();

			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			dbResolveInfoList = rrt_mapper.list_child_type(resolveResultType);

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return dbResolveInfoList;

	}
	 
	public ArrayList<ResolveResultType> getParentInfo(String parent_id) {

		SqlSession session = null;
		 
		ArrayList<ResolveResultType> dbResolveInfoList = new ArrayList<ResolveResultType>();
	 

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();

		     ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			dbResolveInfoList = rrt_mapper.getParentInfo( parent_id);

			 
			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return dbResolveInfoList;

	}

	public ResolveResultType transToResolveResultType(ResolveResultTypeForm resolveResultTypeForm) {

		ResolveResultType resolveResultType = new ResolveResultType();

		if (resolveResultTypeForm != null) {

			resolveResultType.setCreate_time(resolveResultTypeForm.getCreate_time());
			resolveResultType.setUpdate_time(resolveResultTypeForm.getUpdate_time());
	        resolveResultType.setCreate_user(resolveResultTypeForm.getCreate_user());
			resolveResultType.setUpdate_user(resolveResultTypeForm.getUpdate_user());
			resolveResultType.setResolve_id(resolveResultTypeForm.getResolve_id());
			resolveResultType.setResolve_name(resolveResultTypeForm.getResolve_name());
			resolveResultType.setGrade(resolveResultTypeForm.getGrade());
			resolveResultType.setMain_title_id(resolveResultTypeForm.getMain_title_id());
			resolveResultType.setOne_type_id(resolveResultTypeForm.getOne_type_id());
			resolveResultType.setTwo_type_id(resolveResultTypeForm.getTwo_type_id());
			resolveResultType.setThree_type_id(resolveResultTypeForm.getThree_type_id());
			resolveResultType.setMain_title_name(resolveResultTypeForm.getMain_title_name());
			resolveResultType.setOne_type_name(resolveResultTypeForm.getOne_type_name());
			resolveResultType.setTwo_type_name(resolveResultTypeForm.getTwo_type_name());
			resolveResultType.setThree_type_name(resolveResultTypeForm.getThree_type_name());
			resolveResultType.setParent_id(resolveResultTypeForm.getParent_id());
			resolveResultType.setText_tile_id(resolveResultTypeForm.getText_tile_id());
			resolveResultType.setText_tile_name(resolveResultTypeForm.getText_tile_name());
			resolveResultType.setText_one_id(resolveResultTypeForm.getText_one_id());
			resolveResultType.setText_one_name(resolveResultTypeForm.getText_one_name());
			resolveResultType.setText_two_id(resolveResultTypeForm.getText_two_id());
			resolveResultType.setText_two_name(resolveResultTypeForm.getText_two_name());
			resolveResultType.setText_three_id(resolveResultTypeForm.getText_three_id());
			resolveResultType.setText_three_name(resolveResultTypeForm.getText_three_name());
			resolveResultType.setAdd_title_id(resolveResultTypeForm.getAdd_title_id());
			resolveResultType.setOne_add_id(resolveResultTypeForm.getOne_add_id());
			resolveResultType.setTwo_add_id(resolveResultTypeForm.getTwo_add_id());
		}
		return resolveResultType;
	}

	@Override
	public int insert(ResolveResultTypeForm resolveResultTypeForm) {
		SqlSession session = null;

		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		int result = -1;

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();
           	// 保存用户权限信息
			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			result = rrt_mapper.insert(resolveResultType);

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public int update(ResolveResultTypeForm resolveResultTypeForm) {
		SqlSession session = null;
		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		int result = -1;

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            session = sqlSessionFactory.openSession();
            // 保存用户权限信息
			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			result = rrt_mapper.update(resolveResultType);

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public int delete(ResolveResultTypeForm resolveResultTypeForm) {

		SqlSession session = null;
		ResolveResultType resolveResultType = this.transToResolveResultType(resolveResultTypeForm);
		int result = -1;

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();
            // 保存用户权限信息
			ResolveResultTypeMapper rrt_mapper = session.getMapper(ResolveResultTypeMapper.class);
			result = rrt_mapper.delete(resolveResultType);

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return result;

	}
	 
	

}
