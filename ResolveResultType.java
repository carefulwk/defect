package com.esl.teas.entity;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.form.ResolveResultTypeForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 用户权限类。
 * 
 * @author luke
 */
public class ResolveResultType {

	 
	private String objectNo;
	private String resolve_id;
	private String resolve_name;
	private String grade;
    private String create_time;
    private String update_time;
    private String create_user;
	private String update_user;
    private String  main_title_id;
	  private String  one_type_id;
	  private String  two_type_id;
	  private String  three_type_id;
	  private String  main_title_name;
	  private String  one_type_name;
	  private String  two_type_name;
	  private String  three_type_name;
	  private String  parent_id;
	  private String  add_sign;
	  private String  add_title_id;
	  private String  one_add_id ;
	  private String  two_add_id;
	  private String  text_tile_id;
	  private String  text_tile_name;
	  private String  text_one_id ;
	  private String  text_one_name;
	  private String  text_two_id ;
	  private String  text_two_name ;
	  private String  text_three_id ;
	  private String  text_three_name;
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getAdd_title_id() {
		return add_title_id;
	}
	public void setAdd_title_id(String add_title_id) {
		this.add_title_id = add_title_id;
	}
	public String getOne_add_id() {
		return one_add_id;
	}
	public void setOne_add_id(String one_add_id) {
		this.one_add_id = one_add_id;
	}
	public String getTwo_add_id() {
		return two_add_id;
	}
	public void setTwo_add_id(String two_add_id) {
		this.two_add_id = two_add_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getText_tile_id() {
		return text_tile_id;
	}
	public void setText_tile_id(String text_tile_id) {
		this.text_tile_id = text_tile_id;
	}
	public String getText_tile_name() {
		return text_tile_name;
	}
	public void setText_tile_name(String text_tile_name) {
		this.text_tile_name = text_tile_name;
	}
	public String getText_one_id() {
		return text_one_id;
	}
	public void setText_one_id(String text_one_id) {
		this.text_one_id = text_one_id;
	}
	public String getText_one_name() {
		return text_one_name;
	}
	public void setText_one_name(String text_one_name) {
		this.text_one_name = text_one_name;
	}
	public String getText_two_id() {
		return text_two_id;
	}
	public void setText_two_id(String text_two_id) {
		this.text_two_id = text_two_id;
	}
	public String getText_two_name() {
		return text_two_name;
	}
	public void setText_two_name(String text_two_name) {
		this.text_two_name = text_two_name;
	}
	public String getText_three_id() {
		return text_three_id;
	}
	public void setText_three_id(String text_three_id) {
		this.text_three_id = text_three_id;
	}
	public String getText_three_name() {
		return text_three_name;
	}
	public void setText_three_name(String text_three_name) {
		this.text_three_name = text_three_name;
	}
	public String getAdd_sign() {
		return add_sign;
	}
	public void setAdd_sign(String add_sign) {
		this.add_sign = add_sign;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getResolve_id() {
		return resolve_id;
	}
	public void setResolve_id(String resolve_id) {
		this.resolve_id = resolve_id;
	}
	public String getResolve_name() {
		return resolve_name;
	}
	public void setResolve_name(String resolve_name) {
		this.resolve_name = resolve_name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	 
 
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public String getMain_title_id() {
		return main_title_id;
	}
	public void setMain_title_id(String main_title_id) {
		this.main_title_id = main_title_id;
	}
	public String getOne_type_id() {
		return one_type_id;
	}
	public void setOne_type_id(String one_type_id) {
		this.one_type_id = one_type_id;
	}
	public String getTwo_type_id() {
		return two_type_id;
	}
	public void setTwo_type_id(String two_type_id) {
		this.two_type_id = two_type_id;
	}
	public String getThree_type_id() {
		return three_type_id;
	}
	public void setThree_type_id(String three_type_id) {
		this.three_type_id = three_type_id;
	}
	public String getMain_title_name() {
		return main_title_name;
	}
	public void setMain_title_name(String main_title_name) {
		this.main_title_name = main_title_name;
	}
	public String getOne_type_name() {
		return one_type_name;
	}
	public void setOne_type_name(String one_type_name) {
		this.one_type_name = one_type_name;
	}
	public String getTwo_type_name() {
		return two_type_name;
	}
	public void setTwo_type_name(String two_type_name) {
		this.two_type_name = two_type_name;
	}
	public String getThree_type_name() {
		return three_type_name;
	}
	public void setThree_type_name(String three_type_name) {
		this.three_type_name = three_type_name;
	}
 
	 
	 

	 

}