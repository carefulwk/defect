package com.esl.teas.dao;

import java.util.ArrayList;

import com.esl.teas.entity.InsertDefect;
import com.esl.teas.entity.UsualQuery;
import com.esl.teas.form.InsertDefectForm;
 

/*
 * Dao接口。
 * 
 * @author luke
 */
public interface InsertDefectDao {

	public ArrayList<InsertDefect> list(InsertDefectForm insertDefectForm);
	public int save_defect(InsertDefectForm insertDefectForm);
	public int  update_defect(InsertDefectForm insertDefectForm);
	public ArrayList<InsertDefect>   select_data(String lot_seq_sn);
	public InsertDefect select(InsertDefectForm insertDefectForm);

 
}