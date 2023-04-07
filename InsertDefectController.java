package com.esl.teas.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.io.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.entity.InsertDefect;
import com.esl.teas.form.DefectFactoryForm;
import com.esl.teas.form.InsertDefectForm;
import com.esl.teas.service.InsertDefectServiceImpl;

import sun.misc.BASE64Decoder; 
/*
 * 不良解析结果录入
 * 
 * @author LUKE
 */
@RestController
@EnableAutoConfiguration
public class InsertDefectController {

	Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialvid = 1L;
	@Autowired
	InsertDefectServiceImpl insertDefectServiceImpl;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@PostMapping(TEASCommon.STR_URL_INSERTDEFECT)
	public ModelAndView list(DefectFactoryForm defectFactoryForm, HttpSession httpSession) {

		ArrayList<InsertDefect> insertDefectInfoList = new ArrayList<InsertDefect>();
	
		InsertDefectForm insertDefectForm=geDefectForm(defectFactoryForm);
		ArrayList<InsertDefect> defect_InfoList = new ArrayList<InsertDefect>();
		ArrayList<InsertDefect> insert_InfoList = new ArrayList<InsertDefect>();
		 String real_Path=httpSession.getServletContext().getRealPath("/");
		 real_Path=real_Path.replace("\\", "//");
             String realPath=real_Path+"defect_image"+"//"+defectFactoryForm.getSerial();//项目绝对路径
      	 
		// 此处进行数据库操作
		 insertDefectInfoList =insertDefectServiceImpl.list(insertDefectForm);
		 
		
		 
		 for(int i=0;i<insertDefectInfoList.size();i++){
			 InsertDefect insertDefect= insertDefectInfoList.get(i);
			 String lot_seq_sn=insertDefect.getLot_seq_sn();
			 
			  String rootpath=realPath+"//"+insertDefect.getModel_type()+"//"+lot_seq_sn;
			      //  insertDefect.setPicture(rootpath);
		      ArrayList<String>  imgList= getImgs(rootpath);
			  
			  insertDefect.setImgList(imgList);
			  defect_InfoList=insertDefectServiceImpl.select_data(lot_seq_sn);
	    		if(defect_InfoList==null||defect_InfoList.size()<=0){
	    			insert_InfoList.add(insertDefect);
			         continue;
		           }
	    		
	    		 InsertDefect insert_Defect=defect_InfoList.get(0);
	    		insertDefect.setDefect_item(insert_Defect.getDefect_item());
	    		insertDefect.setResolve_result_qty(insert_Defect.getResolve_result_qty());
	    		insertDefect.setDefect_column(insert_Defect.getDefect_column());
	    		insertDefect.setDefect_nz_no(insert_Defect.getDefect_nz_no());
	    		insertDefect.setDefect_np(insert_Defect.getDefect_np());
	    		insertDefect.setSi_cav(insert_Defect.getSi_cav());
	    		insertDefect.setSheet(insert_Defect.getSheet());
	    		insertDefect.setDefect_actunit(insert_Defect.getDefect_actunit());
	    		insertDefect.setDefect_cav(insert_Defect.getDefect_cav());
	    		insertDefect.setResolve_result(insert_Defect.getResolve_result());
	    		insertDefect.setMaterial_report(insert_Defect.getMaterial_report());
	    		insertDefect.setRegrow_judge(insert_Defect.getRegrow_judge());
	    		insertDefect.setLaser_cut(insert_Defect.getLaser_cut());
	    		insertDefect.setRemark(insert_Defect.getRemark());
	    		 
	    		insert_InfoList.add(insertDefect);
	    		
		 }
		 httpSession.setAttribute("serial", defectFactoryForm.getSerial());
		httpSession.setAttribute(TEASCommon.STR_INSERTDEFECTINFOLIST, insert_InfoList);
          return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
	}
	
	@RequestMapping("/showImg")
	 //显示图片
    public ResponseEntity showImg(String imginfo){
	 
        try
		{
 
        	return ResponseEntity.ok(resourceLoader.getResource("file:" + imginfo));
		} catch (Exception e) {
   	 		return ResponseEntity.notFound().build();
		}
    }

	@PostMapping(TEASCommon.STR_URL_INSERTDEFECTSAVE)
	public ModelAndView saveDefect(@Valid InsertDefectForm insertDefectForm, HttpSession httpSession,BindingResult bindingResult) {
		 ArrayList<InsertDefect> dbInsertDefectList = new ArrayList<InsertDefect>();
		 
	
		// 对页面提交过来的insertDefectForm中的参数进行检查。过滤规则见insertDefectForm类注解。
		if (bindingResult.hasErrors()) {
			ArrayList<String> errMsgList = new ArrayList<String>();
			List<FieldError> list = bindingResult.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				errMsgList.add(error.getDefaultMessage());
				logger.warn(error.getDefaultMessage());
			}

			return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
		}
		
		
		int result = 1;
       String lots=insertDefectForm.getLot();
       if(lots==null||lots.equals("")){
    	   return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
    	   
    	   
       }
       
       String u_name=(String) httpSession.getAttribute(TEASCommon.STR_LOGINUSERNAME);
		String u_id=(String) httpSession.getAttribute(TEASCommon.STR_USERID);
	  	 Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat(TEASCommon.STR_YYYYMMDD);
			String creat_date = df.format(date);
		
       if(lots.contains(",")){
    	   
    	String lot_id[]= lots.split(",");
      	 
    	String head_ids=insertDefectForm.getHead_id();
    	String  head_id[]=(head_ids!=null&&!head_ids.equals(""))?head_ids.split(","):null;
    	String lot_seq_sns=insertDefectForm.getLot_seq_sn();
    	String  lot_seq_sn[]=(lot_seq_sns!=null&&!lot_seq_sns.equals(""))?lot_seq_sns.split(","):null;   
    	String model_types=insertDefectForm.getModel_type();
    	String  model_type[]=(model_types!=null&&!model_types.equals(""))?model_types.split(","):null;   
    	String defect_qty_strs=insertDefectForm.getDefect_qty_str();
    	String  defect_qty_str[]=(defect_qty_strs!=null&&!defect_qty_strs.equals(""))?defect_qty_strs.split(","):null;   
    	
    	String put_qty_strs=insertDefectForm.getPut_qty_str();
    	String  put_qty_str[]=(put_qty_strs!=null&&!put_qty_strs.equals(""))?put_qty_strs.split(","):null;   
    	String defect_items=insertDefectForm.getDefect_item();
    	String  defect_item[]=(defect_items!=null&&!defect_items.equals(""))?defect_items.split(","):null;   
    	String  resolve_result_qty_strs=insertDefectForm.getResolve_result_qty_str();
    	String  resolve_result_qty_str[]=(resolve_result_qty_strs!=null&&!resolve_result_qty_strs.equals(""))?resolve_result_qty_strs.split(","):null;  
    	String defect_columns=insertDefectForm.getDefect_column();
    	String  defect_column[]=(defect_columns!=null&&!defect_columns.equals(""))?defect_columns.split(","):null;  
    	String defect_nz_nos=insertDefectForm.getDefect_nz_no();
    	String  defect_nz_no[]=(defect_nz_nos!=null&&!defect_nz_nos.equals(""))?defect_nz_nos.split(","):null;  
    	String defect_nps=insertDefectForm.getDefect_np();
    	String  defect_np[]=(defect_nps!=null&&!defect_nps.equals(""))?defect_nps.split(","):null;  
    	String si_cavs=insertDefectForm.getSi_cav();
    	String  si_cav[]=(si_cavs!=null&&!si_cavs.equals(""))?si_cavs.split(","):null;  
    	String sheets=insertDefectForm.getSheet();
    	String  sheet[]=(sheets!=null&&!sheets.equals(""))?sheets.split(","):null;  
    	String defect_actunits=insertDefectForm.getDefect_actunit();
    	String  defect_actunit[]=(defect_actunits!=null&&!defect_actunits.equals(""))?defect_actunits.split(","):null;  
    	String defect_cavs=insertDefectForm.getDefect_cav();
    	String  defect_cav[]=(defect_cavs!=null&&!defect_cavs.equals(""))?defect_cavs.split(","):null;  
    	String  resolve_results=insertDefectForm.getResolve_result();
    	String  resolve_result[]=(resolve_results!=null&&!resolve_results.equals(""))?resolve_results.split(","):null;  
    	String material_reports=insertDefectForm.getMaterial_report();
    	String  material_report[]=(material_reports!=null&&!material_reports.equals(""))?material_reports.split(","):null;  
    	String regrow_judges=insertDefectForm.getRegrow_judge();
    	String  regrow_judge[]=(regrow_judges!=null&&!regrow_judges.equals(""))?regrow_judges.split(","):null;  
    	String laser_cuts=insertDefectForm.getLaser_cut();
    	String  laser_cut[]=(laser_cuts!=null&&!laser_cuts.equals(""))?laser_cuts.split(","):null;  
    	String remarks=insertDefectForm.getRemark();
    	String  remark[]=(remarks!=null&&!remarks.equals(""))?remarks.split(","):null;  
    	String creat_dts=insertDefectForm.getCreat_dt();
    	String  creat_dt[]=(creat_dts!=null&&!creat_dts.equals(""))?creat_dts.split(","):null;  
    	String last_maint_dts=insertDefectForm.getLast_maint_dt();
    	String  last_maint_dt[]=(last_maint_dts!=null&&!last_maint_dts.equals(""))?last_maint_dts.split(","):null;  
    	String serials=insertDefectForm.getSerial();
    	String  serial[]=(serials!=null&&!serials.equals(""))?serials.split(","):null;  
    	 	for(int i=0;i<lot_id.length;i++){
    	 	InsertDefectForm insertDefect_form=new InsertDefectForm();
    		insertDefect_form.setLot(lot_id[i]);
    		insertDefect_form.setHead_id(head_id!=null&&head_id.length>i&&!head_id[i].equals("")?head_id[i]:null);
    		insertDefect_form.setLot_seq_sn(lot_seq_sn!=null&&lot_seq_sn.length>i&&!lot_seq_sn[i].equals("")?lot_seq_sn[i]:null);
    		insertDefect_form.setModel_type(model_type!=null&&model_type.length>i&&!model_type[i].equals("")?model_type[i]:null);
    		insertDefect_form.setDefect_qty(defect_qty_str!=null&&defect_qty_str.length>i&&defect_qty_str[i]!=null&&!defect_qty_str[i].equals("")?Double.parseDouble(defect_qty_str[i]) :0);
    		insertDefect_form.setDefect_item(defect_item!=null&&defect_item.length>i?defect_item[i]:null);
    		insertDefect_form.setResolve_result_qty(resolve_result_qty_str!=null&&resolve_result_qty_str.length>i&&resolve_result_qty_str[i]!=null&&!resolve_result_qty_str[i].equals("")?Double.parseDouble(resolve_result_qty_str[i]):0);
    		insertDefect_form.setDefect_column(defect_column!=null&&defect_column.length>i?defect_column[i]:null);
    		insertDefect_form.setDefect_nz_no(defect_nz_no!=null&&defect_nz_no.length>i?defect_nz_no[i]:null);
    		insertDefect_form.setDefect_np(defect_np!=null&&defect_np.length>i?defect_np[i]:null);
    		insertDefect_form.setSi_cav(si_cav!=null&&si_cav.length>i?si_cav[i]:null);
    		insertDefect_form.setSheet(sheet!=null&&sheet.length>i?sheet[i]:null);
    		insertDefect_form.setDefect_actunit(defect_actunit!=null&&defect_actunit.length>i?defect_actunit[i]:null);
    		insertDefect_form.setDefect_cav(defect_cav!=null&&defect_cav.length>i&&!defect_cav[i].equals("")?defect_cav[i]:null);
    		insertDefect_form.setResolve_result(resolve_result!=null&&resolve_result.length>i?resolve_result[i]:null);
    		insertDefect_form.setMaterial_report(material_report!=null&&material_report.length>i?material_report[i]:null);
    		insertDefect_form.setRegrow_judge(regrow_judge!=null&&regrow_judge.length>i?regrow_judge[i]:null);
    		insertDefect_form.setLaser_cut(laser_cut!=null&&laser_cut.length>i?laser_cut[i]:null);
    		insertDefect_form.setRemark(remark!=null&&remark.length>i?remark[i]:null);
    		insertDefect_form.setCreat_dt(creat_dt!=null&&creat_dt.length>i?creat_dt[i]:null);
    		insertDefect_form.setLast_maint_dt(last_maint_dt!=null&&last_maint_dt.length>i?last_maint_dt[i]:null);
    		insertDefect_form.setCreate_date(creat_date);
    		insertDefect_form.setSave_user(u_id);
    		insertDefect_form.setUpdate_user(u_id);
    		insertDefect_form.setUpdate_date(creat_date);
    		insertDefect_form.setSerial( serial!=null&&serial.length>i?serial[i]:null);
    		insertDefect_form.setPut_qty(put_qty_str!=null&&put_qty_str.length>i&&put_qty_str[i]!=null&&!put_qty_str[i].equals("")?Double.parseDouble(put_qty_str[i]) :0);
    		dbInsertDefectList=insertDefectServiceImpl.select_data(insertDefect_form.getLot_seq_sn());
    		
    		
    		
    		if(dbInsertDefectList==null||dbInsertDefectList.size()<=0){
    		  result=	insertDefectServiceImpl.save_defect(insertDefect_form);
    		}
    		else{
    			 result=	insertDefectServiceImpl.update_defect(insertDefect_form);
    		}
    		  if (result== -1) {
    			   return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
    			} 
    		  
    		  
    	}
    	   
       }
       else{
    	   
    	   
    	   dbInsertDefectList=insertDefectServiceImpl.select_data(insertDefectForm.getLot_seq_sn());
   		if(dbInsertDefectList==null||dbInsertDefectList.size()<=0){
   		  result=	insertDefectServiceImpl.save_defect(insertDefectForm);
   		}
   		else{
   			 result=	insertDefectServiceImpl.update_defect(insertDefectForm);
   		}
      
		
       }

		if (result != -1) {
			// ArrayList<InsertDefect> insertDefectInfoList =insertDefectServiceImpl.list(insertDefectForm);
			// httpSession.setAttribute(TEASCommon.STR_INSERTDEFECTINFOLIST, insertDefectInfoList);
			 return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
		 
			//return	TEASCommon.STR_CN_SAVEDEFECT_SUCCESS;
		} else {
	
			 return new ModelAndView(TEASCommon.STR_VIEW_TEAS_INSERT_DEFECT);
			
		}
 
		 
	}
    //获取图片
	private ArrayList<String> getImgs(String path) {
		File file = new File(path);
		File[] array = file.listFiles();
		 if(array==null){
			 return null;
		 }
		ArrayList imgList = new ArrayList();
		InputStream in = null;
		byte[] data = null;
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				String imgName = array[i].getName();
				if(imgName==null||imgName.equals("")){
					continue;
				}
				String imgPath = path + "//" + imgName;
				 
				imgList.add(imgPath);
			 
			}
 
		}

		return imgList;
 }
	  
	

//删除图片
	
	@PostMapping(TEASCommon.STR_URL_DELETEIMG)
	public boolean  deleteImg(InsertDefectForm insertDefectForm, HttpSession httpSession) {
	 
		
		 String picture = insertDefectForm.getPicture();
	 
		 File file=new File(picture);
		  if(file.exists() && file.isFile())
		    file.delete();
	        return true;
	}
	
	
	//上传图片
	@PostMapping(TEASCommon.STR_URL_INSERTDEFECTPIC)
	public boolean uploadpic(InsertDefectForm insertDefectForm, HttpSession httpSession) {
	 
	
		 String img_obj = insertDefectForm.getImg_obj();
		 String lot_seq_sn=insertDefectForm.getLot_seq_sn();
		 String model_type=insertDefectForm.getModel_type();
		 String serial=insertDefectForm.getSerial();
		
		 if(img_obj==null||img_obj.equals("")){
			 
			 return false;
		 }
		 
		 
		 String img_list[]=img_obj.split("data:image/jpeg;base64,");
		 String header ="data:image/jpeg;base64,";
		 BASE64Decoder decoder = new BASE64Decoder();
		 String realPath=httpSession.getServletContext().getRealPath("/");//项目绝对路径
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
		 String time_name=df.format(new Date()); 
		 String rootpath=realPath+"defect_image"+"\\"+serial+"\\"+model_type+"\\"+lot_seq_sn;
		 File file=new File(rootpath);
		 for(int i=0;i<img_list.length;i++){
			 String img_o=img_list[i];
			 if(img_o==null||img_o.equals("")){
				 
				 continue;
			 }
			// img_o = img_o.substring(header.length());
			 try {
					
				 	byte[] decodedBytes = decoder.decodeBuffer(img_o);
				  
				 	 	if(!file.exists())    
				 	{    
				 	  file.mkdirs();    
				 	  
				 	}
				 	 String	fname=time_name+i+".jpg";
					String imgFilePath =rootpath+ "//"+fname;
					FileOutputStream out = new FileOutputStream(imgFilePath);
					out.write(decodedBytes);
					out.close();
					 
				} catch (Exception e) {
					 
					e.printStackTrace();
				}
				
			 
		 }
		 
          	
		  	
		  	 
		
          return true;
	}
	
	
	private InsertDefectForm geDefectForm(DefectFactoryForm defectFactoryForm) {

		InsertDefectForm insertDefectForm = new InsertDefectForm();
		insertDefectForm.setStart_date(defectFactoryForm.getStart_date());
		insertDefectForm.setEnd_date(defectFactoryForm.getEnd_date());
		insertDefectForm.setDepartmentId(defectFactoryForm.getDepartmentId());
		insertDefectForm.setSerial(defectFactoryForm.getSerial());
		insertDefectForm.setModelSerial(defectFactoryForm.getModelSerial());
		insertDefectForm.setModelCode(defectFactoryForm.getModelCode());
		insertDefectForm.setDefect_column(defectFactoryForm.getDefectColumn());
		insertDefectForm.setLot(defectFactoryForm.getLot());
		return insertDefectForm;
	}



	 
	 

}