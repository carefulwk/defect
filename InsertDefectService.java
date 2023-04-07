package com.esl.teas.service;

import java.util.ArrayList;

import com.esl.teas.entity.InsertDefect;
import com.esl.teas.form.InsertDefectForm;
 

/*
 * 服务接口。
 * 
 * @author luke
 */
public interface InsertDefectService {
	public ArrayList<InsertDefect> list(InsertDefectForm insertDefectForm);
	
	public int save_defect(InsertDefectForm insertDefectForm);
	public ArrayList<InsertDefect>  select_data(String lot_seq_sn);
	public int update_defect(InsertDefectForm insertDefectForm);
	
	public InsertDefect select(InsertDefectForm insertDefectForm);
 
}