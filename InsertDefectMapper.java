package com.esl.teas.mapper;

import java.util.ArrayList;

import com.esl.teas.entity.InsertDefect;
import com.esl.teas.entity.Model;
import com.esl.teas.entity.UsualQuery;

/*
 * InsertDefect映射接口。具体映射见XML配置文件。
 * 
 * @author luke
 */
public interface InsertDefectMapper {
	ArrayList< InsertDefect> list( InsertDefect  insertDefect);
	 InsertDefect select( InsertDefect  insertDefect);
	 int save_defect( InsertDefect  insertDefect);
	 int update_defect( InsertDefect  insertDefect);
	 ArrayList< InsertDefect>  select_data(String lot_seq_sn);
	 
	 

}
