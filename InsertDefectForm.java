package com.esl.teas.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;

import com.esl.teas.common.TEASCommon;

public class InsertDefectForm {
	
  
	@Value(TEASCommon.STR_EMPTY)
	private String lot;//LOT
	
	@Value(TEASCommon.STR_EMPTY)
	private String head_id;
	
	@Value(TEASCommon.STR_EMPTY)
    private String start_date; //生产日期
	
	@Value(TEASCommon.STR_EMPTY)
    private String end_date;
	
	@Value(TEASCommon.STR_EMPTY)
    private String  model_type; //机种
	
	@Value(TEASCommon.STR_EMPTY)
    private double defect_qty;  //不良数量
	
	@Value(TEASCommon.STR_EMPTY)
	private double put_qty;//投入数
	private String put_qty_str;//投入数
	
	@Value(TEASCommon.STR_EMPTY)
    private String defect_rate;//不良率
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_item;//不良项目
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_column;//不良列别
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_nz_no;//不良NZ NO#
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_adressbit;//不良地址位
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_np;//NP
	
	@Value(TEASCommon.STR_EMPTY)
	private String si_cav;//si_cav
	
	@Value(TEASCommon.STR_EMPTY)
	private String sheet;//SHEET
	
	@Value(TEASCommon.STR_EMPTY)
	private String defect_cav;//不良CAV#
	
	@Value(TEASCommon.STR_EMPTY)
	private String resolve_result;//解析结果
	
	@Value(TEASCommon.STR_EMPTY)
	private String material_report;//材质分析结果
	
	@Value(TEASCommon.STR_EMPTY)
	private String regrow_judge;//再生判定
	
	@Value(TEASCommon.STR_EMPTY)
	private String laser_cut;//激光切割
	
	@Value(TEASCommon.STR_EMPTY)
	private String resolve_date;//解析日期
	
	@Value(TEASCommon.STR_EMPTY)
	private String remark;//备注
	
	@Value(TEASCommon.STR_EMPTY)
	private String picture;//图片详情
	private String departmentId;
	private String departmentName;
	 private String serial;
	 private String modelSerial;
	 private String modelCode;
	 private String defectColumn;
	 private String lot_seq_sn;
	 private String img_obj;
	 private String defect_actunit;
	 private double  resolve_result_qty;
	 private String defect_qty_str;  //不良数量
	 private String  resolve_result_qty_str;
	 private ArrayList<String> imgList;  
	 private String  creat_dt;
	 private String  last_maint_dt;
	 private String save_user;  
	 private String update_user;  
	  private String create_date;  
	  private String update_date;
 
	 

	public String getPut_qty_str() {
		return put_qty_str;
	}

	public void setPut_qty_str(String put_qty_str) {
		this.put_qty_str = put_qty_str;
	}

	public String getCreat_dt() {
		return creat_dt;
	}

	public void setCreat_dt(String creat_dt) {
		this.creat_dt = creat_dt;
	}

	public String getLast_maint_dt() {
		return last_maint_dt;
	}

	public void setLast_maint_dt(String last_maint_dt) {
		this.last_maint_dt = last_maint_dt;
	}

	public String getSave_user() {
		return save_user;
	}

	public void setSave_user(String save_user) {
		this.save_user = save_user;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public ArrayList<String> getImgList() {
		return imgList;
	}

	public void setImgList(ArrayList<String> imgList) {
		this.imgList = imgList;
	}

	public double getDefect_qty() {
		return defect_qty;
	}

	public void setDefect_qty(double defect_qty) {
		this.defect_qty = defect_qty;
	}

	public double getResolve_result_qty() {
		return resolve_result_qty;
	}

	public void setResolve_result_qty(double resolve_result_qty) {
		this.resolve_result_qty = resolve_result_qty;
	}

	public String getLaser_cut() {
		return laser_cut;
	}

	public void setLaser_cut(String laser_cut) {
		this.laser_cut = laser_cut;
	}

	public String getResolve_result_qty_str() {
		return resolve_result_qty_str;
	}

	public void setResolve_result_qty_str(String resolve_result_qty_str) {
		this.resolve_result_qty_str = resolve_result_qty_str;
	}

	public String getDefect_qty_str() {
		return defect_qty_str;
	}

	public void setDefect_qty_str(String defect_qty_str) {
		this.defect_qty_str = defect_qty_str;
	}

	public String getDefect_np() {
		return defect_np;
	}

	public void setDefect_np(String defect_np) {
		this.defect_np = defect_np;
	}

	public String getDefect_actunit() {
		return defect_actunit;
	}

	public void setDefect_actunit(String defect_actunit) {
		this.defect_actunit = defect_actunit;
	}

 
 

	public String getImg_obj() {
		return img_obj;
	}

	public void setImg_obj(String img_obj) {
		this.img_obj = img_obj;
	}

	public String getLot_seq_sn() {
		return lot_seq_sn;
	}

	public void setLot_seq_sn(String lot_seq_sn) {
		this.lot_seq_sn = lot_seq_sn;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getResolve_date() {
		return resolve_date;
	}

	public void setResolve_date(String resolve_date) {
		this.resolve_date = resolve_date;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModelSerial() {
		return modelSerial;
	}

	public void setModelSerial(String modelSerial) {
		this.modelSerial = modelSerial;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getDefectColumn() {
		return defectColumn;
	}

	public void setDefectColumn(String defectColumn) {
		this.defectColumn = defectColumn;
	}

	 

	 

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getHead_id() {
		return head_id;
	}

	public void setHead_id(String head_id) {
		this.head_id = head_id;
	}

	 
	public String getModel_type() {
		return model_type;
	}

	public void setModel_type(String model_type) {
		this.model_type = model_type;
	}

 

	public double getPut_qty() {
		return put_qty;
	}

	public void setPut_qty(double put_qty) {
		this.put_qty = put_qty;
	}

	public String getDefect_rate() {
		return defect_rate;
	}

	public void setDefect_rate(String defect_rate) {
		this.defect_rate = defect_rate;
	}

	public String getDefect_item() {
		return defect_item;
	}

	public void setDefect_item(String defect_item) {
		this.defect_item = defect_item;
	}

	public String getDefect_column() {
		return defect_column;
	}

	public void setDefect_column(String defect_column) {
		this.defect_column = defect_column;
	}

	public String getDefect_nz_no() {
		return defect_nz_no;
	}

	public void setDefect_nz_no(String defect_nz_no) {
		this.defect_nz_no = defect_nz_no;
	}

	public String getDefect_adressbit() {
		return defect_adressbit;
	}

	public void setDefect_adressbit(String defect_adressbit) {
		this.defect_adressbit = defect_adressbit;
	}

 

	public String getSi_cav() {
		return si_cav;
	}

	public void setSi_cav(String si_cav) {
		this.si_cav = si_cav;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getDefect_cav() {
		return defect_cav;
	}

	public void setDefect_cav(String defect_cav) {
		this.defect_cav = defect_cav;
	}

	public String getResolve_result() {
		return resolve_result;
	}

	public void setResolve_result(String resolve_result) {
		this.resolve_result = resolve_result;
	}

	public String getMaterial_report() {
		return material_report;
	}

	public void setMaterial_report(String material_report) {
		this.material_report = material_report;
	}

	public String getRegrow_judge() {
		return regrow_judge;
	}

	public void setRegrow_judge(String regrow_judge) {
		this.regrow_judge = regrow_judge;
	}

 

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	 
}
