package com.esl.teas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esl.teas.dao.InsertDefectDaoImpl;
import com.esl.teas.dao.ModelDaoImpl;
import com.esl.teas.dao.UsualQueryDaoImpl;
import com.esl.teas.entity.InsertDefect;
import com.esl.teas.entity.Model;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.UsualQuery;
import com.esl.teas.form.InsertDefectForm;
import com.esl.teas.form.ModelForm;
import com.esl.teas.form.UsualQueryForm;

/*
 * 接口实现类。
 * 
 * @author luke
 */
@Component
public class InsertDefectServiceImpl implements  InsertDefectService {

	@Autowired
	 InsertDefectDaoImpl  insertDefectDao;

	@Override
	public ArrayList< InsertDefect> list( InsertDefectForm   insertDefectForm) {

		ArrayList< InsertDefect>  insertDefectInfoLiist = new ArrayList< InsertDefect>();
		 insertDefectInfoLiist =  insertDefectDao.list(insertDefectForm);

		return insertDefectInfoLiist;
	}
	@Override
	public  int save_defect( InsertDefectForm  insertDefectForm) {
 
		 int t = insertDefectDao.save_defect(insertDefectForm);
	       return t;
	 
	}
	
	@Override
	public  int update_defect( InsertDefectForm  insertDefectForm) {
 
		 int t = insertDefectDao.update_defect(insertDefectForm);
	       return t;
	 
	}
	@Override
	public  ArrayList< InsertDefect>  select_data(String lot_seq_sn) {
		ArrayList< InsertDefect>  insertDefectInfoList = new ArrayList< InsertDefect>();
		insertDefectInfoList= insertDefectDao.select_data(lot_seq_sn);
	       return insertDefectInfoList;
	 
	}
	
	
	@Override
	public  InsertDefect select( InsertDefectForm  insertDefectForm) {
		 InsertDefect insertDefect = new  InsertDefect();
		 insertDefect = insertDefectDao.select(insertDefectForm);
		return insertDefect;
	 
	}

	 

}