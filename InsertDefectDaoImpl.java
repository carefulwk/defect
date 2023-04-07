package com.esl.teas.dao;

/*
 * 用户Dao接口实现。用于用户权限增删改查。
 * 
 * @author Luke
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.entity.Department;
import com.esl.teas.entity.InsertDefect;
 
import com.esl.teas.form.InsertDefectForm;
import com.esl.teas.form.ModelForm;
import com.esl.teas.mapper.DepartmentMapper;
import com.esl.teas.mapper.InsertDefectMapper;
 

@Component
public class InsertDefectDaoImpl implements InsertDefectDao {
	Logger logger = LoggerFactory.getLogger(getClass());
   public ArrayList<InsertDefect> list(InsertDefectForm  insertDefectForm) {

		SqlSession session = null;
		InsertDefect insertDefect = this.transToinsertDefect(insertDefectForm);
		ArrayList<InsertDefect> dbInsertDefectList = new ArrayList<InsertDefect>();
		ArrayList<InsertDefect> outputInsertDefectInfoList = new ArrayList<InsertDefect>();

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();
	 
			
			InsertDefectMapper mapper = session.getMapper(InsertDefectMapper.class);
			dbInsertDefectList = mapper.list(insertDefect);

	
			Iterator<InsertDefect> iter = dbInsertDefectList.iterator();
			int objectNo = 1;
			if(iter!=null){
			while (iter.hasNext()) {
				InsertDefect tempInsertDefect= iter.next();
				if(tempInsertDefect==null) continue;
				tempInsertDefect.setObjectNo(Integer.valueOf(objectNo).toString());
				outputInsertDefectInfoList.add(tempInsertDefect);
				objectNo++;
			}
			}

			session.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return outputInsertDefectInfoList;

	}

   
   public int save_defect(InsertDefectForm insertDefectForm) {
	   SqlSession session = null;
	   InsertDefect insertDefect = this.transToinsertDefect(insertDefectForm);
		int result = -1;

		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

			session = sqlSessionFactory.openSession();
 
			InsertDefectMapper mapper = session.getMapper(InsertDefectMapper.class);
			result = mapper.save_defect(insertDefect);

			session.commit();
		} catch (IOException ex) {
			logger.error(TEASCommon.STR_CN_DATABASE_EXCEPTION,ex);
		} catch (Exception ex) {
			logger.error(TEASCommon.STR_CN_DATABASE_EXCEPTION,ex);
		} finally {
			session.close();
		}

		return result;
		 
	}
   
   
   public  ArrayList<InsertDefect>  select_data(String lot_seq_sn) {
	   SqlSession session = null;
	  
		 ArrayList<InsertDefect> dbInsertDefectList = new ArrayList<InsertDefect>();
	 	ArrayList<InsertDefect> outputInsertDefectInfoList = new ArrayList<InsertDefect>();
		try {
			String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
			InputStream in = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
           	session = sqlSessionFactory.openSession();
	 
			
			InsertDefectMapper mapper = session.getMapper(InsertDefectMapper.class);
			dbInsertDefectList= mapper.select_data(lot_seq_sn);
		
			Iterator<InsertDefect> iter = dbInsertDefectList.iterator();
			int objectNo = 1;
			if(iter!=null){
			while (iter.hasNext()) {
				InsertDefect tempInsertDefect= iter.next();
				if(tempInsertDefect==null) continue;
				tempInsertDefect.setObjectNo(Integer.valueOf(objectNo).toString());
				outputInsertDefectInfoList.add(tempInsertDefect);
				objectNo++;
			}
			}
					
             
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		 
		return outputInsertDefectInfoList;

   }
   
	@Override
	public int update_defect(InsertDefectForm insertDefectForm) {
		   SqlSession session = null;
		   InsertDefect insertDefect = this.transToinsertDefect(insertDefectForm);
			int result = -1;

			try {
				String resource = TEASCommon.STR_XML_MYBATIS_CONFIG;
				InputStream in = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

				session = sqlSessionFactory.openSession();
	 
				InsertDefectMapper mapper = session.getMapper(InsertDefectMapper.class);
				result = mapper.update_defect(insertDefect);

				session.commit();
			} catch (IOException ex) {
				logger.error(TEASCommon.STR_CN_DATABASE_EXCEPTION,ex);
			} catch (Exception ex) {
				logger.error(TEASCommon.STR_CN_DATABASE_EXCEPTION,ex);
			} finally {
				session.close();
			}

			return result;
			 
		}
	public InsertDefect select(InsertDefectForm insertDefectForm) {
		// TODO Auto-generated method stub
		return null;
	}

	 

	public InsertDefect transToinsertDefect(InsertDefectForm insertDefectForm) {

		InsertDefect insertDefect = new InsertDefect();

		if (insertDefectForm != null) {
			
			insertDefect.setStart_date(insertDefectForm.getStart_date());
			insertDefect.setDefect_adressbit(insertDefectForm.getDefect_adressbit());
			insertDefect.setDefect_cav(insertDefectForm.getDefect_cav());
			insertDefect.setSerial(insertDefectForm.getSerial());
			insertDefect.setDefect_column(insertDefectForm.getDefect_column());
			insertDefect.setDefect_item(insertDefectForm.getDefect_item());
			insertDefect.setDefect_nz_no(insertDefectForm.getDefect_nz_no());
			insertDefect.setDefect_qty(insertDefectForm.getDefect_qty());
			insertDefect.setDefect_rate(insertDefectForm.getDefect_rate());
			insertDefect.setHead_id(insertDefectForm.getHead_id());
			insertDefect.setLaser_cut(insertDefectForm.getLaser_cut());
			insertDefect.setLot(insertDefectForm.getLot());
			insertDefect.setMaterial_report(insertDefectForm.getMaterial_report());
			insertDefect.setModel_type(insertDefectForm.getModel_type());
			insertDefect.setDefect_np(insertDefectForm.getDefect_np());
			insertDefect.setDefect_actunit(insertDefectForm.getDefect_actunit());
			insertDefect.setPicture(insertDefectForm.getPicture());
			insertDefect.setPut_qty(insertDefectForm.getPut_qty());
			insertDefect.setRegrow_judge(insertDefectForm.getRegrow_judge());
			insertDefect.setRemark(insertDefectForm.getRemark());
			insertDefect.setResolve_date(insertDefectForm.getResolve_date());
			insertDefect.setResolve_result(insertDefectForm.getResolve_result());
			insertDefect.setSheet(insertDefectForm.getSheet());
			insertDefect.setSi_cav(insertDefectForm.getSi_cav());
			insertDefect.setEnd_date(insertDefectForm.getEnd_date());
			insertDefect.setDefect_qty_str(insertDefectForm.getDefect_qty_str());
	
			insertDefect.setResolve_result_qty_str(insertDefectForm.getResolve_result_qty_str());
			insertDefect.setResolve_result_qty(insertDefectForm.getResolve_result_qty());
			insertDefect.setLot_seq_sn(insertDefectForm.getLot_seq_sn());
			insertDefect.setImgList(insertDefectForm.getImgList());
			insertDefect.setCreat_dt(insertDefectForm.getCreat_dt());
			insertDefect.setPut_qty_str(insertDefectForm.getPut_qty_str());
			insertDefect.setLast_maint_dt(insertDefectForm.getLast_maint_dt());
			insertDefect.setSave_user(insertDefectForm.getSave_user());
			insertDefect.setUpdate_user(insertDefectForm.getUpdate_user());
			insertDefect.setCreate_date(insertDefectForm.getCreate_date());
			insertDefect.setUpdate_date(insertDefectForm.getUpdate_date());
			  
		 
	
	}
		return insertDefect;
}
}