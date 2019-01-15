package com.esl.teas.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.esl.teas.common.TEASCommon;
import com.esl.teas.entity.ResolveResultType;
import com.esl.teas.entity.User;
import com.esl.teas.form.ResolveResultTypeForm;
import com.esl.teas.form.UserForm;
import com.esl.teas.service.ResolveResultTypeServiceImpl;
import com.esl.teas.service.UserServiceImpl;

/*
 * 权限管理控制器。
 * 
 * @author luke
 */
@RestController
@EnableAutoConfiguration
public class ResolveResultTypeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ResolveResultTypeServiceImpl resolveResultTypeServiceImpl;

	@GetMapping(TEASCommon.STR_URL_RRTTYPE)
	public ModelAndView list(ResolveResultTypeForm resolveResultTypeForm, BindingResult bindingResult,HttpSession httpSession) {

		ArrayList<ResolveResultType> rrt_InfoList = new ArrayList<ResolveResultType>();
		ArrayList<ResolveResultType> re_result_List = new ArrayList<ResolveResultType>();
		ArrayList<ResolveResultType> out_InfoList = new ArrayList<ResolveResultType>();
		ArrayList<ResolveResultType> out_re_InfoList = new ArrayList<ResolveResultType>();
		// 此处进行数据库操作
		rrt_InfoList = resolveResultTypeServiceImpl.list(resolveResultTypeForm);
		re_result_List = resolveResultTypeServiceImpl.list_re_result(resolveResultTypeForm);
		String add_sign=resolveResultTypeForm.getAdd_sign();
		
		for(int i=0;i<re_result_List.size();i++){
			ResolveResultType ResolveInfo=re_result_List.get(i);
			ResolveResultType re_Info=new  ResolveResultType();
			String resolve_id=ResolveInfo.getResolve_id();
			String resolve_name=ResolveInfo.getResolve_name();
		 
				re_Info.setMain_title_id(resolve_id);
				re_Info.setMain_title_name(resolve_name);
				
				out_re_InfoList.add(re_Info);
			
		}
	 
		for(int i=0;i<rrt_InfoList.size();i++){
			ResolveResultType ResolveInfo=rrt_InfoList.get(i);
			ResolveResultType re_Info=new  ResolveResultType();
			String resolve_id=ResolveInfo.getResolve_id();
			String resolve_name=ResolveInfo.getResolve_name();
			String parent_id=ResolveInfo.getParent_id();
			String grade= ResolveInfo.getGrade();
			if(grade.equals("0")){
				re_Info.setMain_title_id(resolve_id);
				re_Info.setMain_title_name(resolve_name);
				
			}
			if(grade.equals("1")){
				re_Info.setOne_type_id(resolve_id);
				re_Info.setOne_type_name(resolve_name);
				 
				ArrayList<ResolveResultType> parent_list= resolveResultTypeServiceImpl.getParentInfo(parent_id);
				ResolveResultType parent_Info=new  ResolveResultType();
				if(parent_list!=null){
					parent_Info=parent_list.get(0);	
				}
				re_Info.setMain_title_id(parent_Info.getResolve_id());
				re_Info.setMain_title_name(parent_Info.getResolve_name());
				
			 	}
			if(grade.equals("2")){
				re_Info.setTwo_type_id(resolve_id);
				re_Info.setTwo_type_name(resolve_name);
				 
				ArrayList<ResolveResultType> parent_list= resolveResultTypeServiceImpl.getParentInfo(parent_id);
				ResolveResultType parent_Info=new  ResolveResultType();
				if(parent_list!=null){
					parent_Info=parent_list.get(0);	
				}
				re_Info.setOne_type_id(parent_Info.getResolve_id());
				re_Info.setOne_type_name(parent_Info.getResolve_name());
				String  re_par_id=parent_Info.getParent_id();
				ArrayList<ResolveResultType> re_parent_list= resolveResultTypeServiceImpl.getParentInfo(re_par_id);
				ResolveResultType re_parent_Info=new  ResolveResultType();
				if(re_parent_list!=null){
					re_parent_Info=re_parent_list.get(0);	
				}
				re_Info.setMain_title_id(re_parent_Info.getResolve_id());
				re_Info.setMain_title_name(re_parent_Info.getResolve_name());
			 	}
			if(grade.equals("3")){
				re_Info.setThree_type_id(resolve_id);
				re_Info.setThree_type_name(resolve_name);
				
			 ArrayList<ResolveResultType> parent_list= resolveResultTypeServiceImpl.getParentInfo(parent_id);
				ResolveResultType parent_Info=new  ResolveResultType();
				if(parent_list!=null){
					parent_Info=parent_list.get(0);	
				}
				re_Info.setTwo_type_id(parent_Info.getResolve_id());
				re_Info.setTwo_type_name(parent_Info.getResolve_name());
				String  re_par_id=parent_Info.getParent_id();
				ArrayList<ResolveResultType> re_parent_list= resolveResultTypeServiceImpl.getParentInfo(re_par_id);
				ResolveResultType re_parent_Info=new  ResolveResultType();
				if(re_parent_list!=null){
					re_parent_Info=re_parent_list.get(0);	
				}
				re_Info.setOne_type_id(re_parent_Info.getResolve_id());
				re_Info.setOne_type_name(re_parent_Info.getResolve_name());
				String  sec_par_id=re_parent_Info.getParent_id();
				ArrayList<ResolveResultType> sec_parent_list= resolveResultTypeServiceImpl.getParentInfo(sec_par_id);
				ResolveResultType sec_parent_Info=new  ResolveResultType();
				if(sec_parent_list!=null){
					sec_parent_Info=sec_parent_list.get(0);	
				}
				
				re_Info.setMain_title_id(sec_parent_Info.getResolve_id());
				re_Info.setMain_title_name(sec_parent_Info.getResolve_name());
			 	}
			re_Info.setCreate_time(ResolveInfo.getCreate_time());
			re_Info.setGrade(ResolveInfo.getResolve_id());
			re_Info.setCreate_user(ResolveInfo.getCreate_user());
			re_Info.setObjectNo(ResolveInfo.getObjectNo());
			re_Info.setUpdate_time(ResolveInfo.getUpdate_time());
			re_Info.setUpdate_user(ResolveInfo.getUpdate_user());
			out_InfoList.add(re_Info);
		}
		
		 
		httpSession.setAttribute(TEASCommon.STR_RRTINFOLIST, out_InfoList);
		httpSession.setAttribute(TEASCommon.STR_RERESULTLIST, out_re_InfoList);
		httpSession.setAttribute(TEASCommon.STR_ADD_SIGN, add_sign);
		
		return new ModelAndView(TEASCommon.STR_VIEW_TEAS_RRT_LIST);
	}
	
	 @PostMapping(TEASCommon.STR_URL_GET_CHILD_TYPE)
	 
	 public ArrayList<ResolveResultType> getChild_type(ResolveResultTypeForm resolveResultTypeForm, HttpSession httpSession) {
		  	ArrayList<ResolveResultType> 	list_child_type = resolveResultTypeServiceImpl.list_child_type(resolveResultTypeForm);
			 return list_child_type;
	 }
	 
	
 
	@PostMapping(TEASCommon.STR_ADD_RRTTYPE)
	public String insert(ResolveResultTypeForm resolveResultTypeForm, HttpSession httpSession, BindingResult bindingResult) {

		// 对页面提交过来的UserForm中的参数进行检查。过滤规则见UserForm类注解。
		if (bindingResult.hasErrors()) {
			ArrayList<String> errMsgList = new ArrayList<String>();
			List<FieldError> list = bindingResult.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				errMsgList.add(error.getDefaultMessage());
				logger.warn(error.getDefaultMessage());
			}

			return TEASCommon.STR_CN_INVALID_PARAM;
		}
		
		String userId=(String)httpSession.getAttribute(TEASCommon.STR_USERID);
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String create_time=df.format(new Date()); 
		resolveResultTypeForm.setCreate_user(userId);
		resolveResultTypeForm.setCreate_time(create_time);
		
		// 此处进行数据库操作
	  int  result = resolveResultTypeServiceImpl.insert(resolveResultTypeForm);

		if (result != -1) {
			return TEASCommon.STR_CN_ADDRESOLVETYPE_SUCCESS;
		} else {
			return TEASCommon.STR_CN_ADDRESOLVETYPE_FAILED;
		}

	}
 
	@PostMapping(TEASCommon.STR_UPDATE_RRTTYPE)
	public String update(ResolveResultTypeForm resolveResultTypeForm, HttpSession httpSession, BindingResult bindingResult) {

		// 对页面提交过来的UserForm中的参数进行检查。过滤规则见UserForm类注解。
		if (bindingResult.hasErrors()) {
			ArrayList<String> errMsgList = new ArrayList<String>();
			List<FieldError> list = bindingResult.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				errMsgList.add(error.getDefaultMessage());
				logger.warn(error.getDefaultMessage());
			}

			return TEASCommon.STR_CN_INVALID_PARAM;
		}
		
	 
		
		String userId=(String)httpSession.getAttribute(TEASCommon.STR_USERID);
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 String update_time=df.format(new Date()); 
		resolveResultTypeForm.setUpdate_user(userId);
		resolveResultTypeForm.setUpdate_time(update_time);
		
		// 此处进行数据库操作
	  int  result = resolveResultTypeServiceImpl.update(resolveResultTypeForm);

		if (result != -1) {
			return TEASCommon.STR_CN_MODIFYTYPE_SUCCESS;
		} else {
			return TEASCommon.STR_CN_MODIFYTYPE_FAILED;
		}

	}
	@PostMapping(TEASCommon.STR_DELETE_RRTTYPE)
	public String delete(ResolveResultTypeForm resolveResultTypeForm, HttpSession httpSession, BindingResult bindingResult) {

		// 对页面提交过来的UserForm中的参数进行检查。过滤规则见UserForm类注解。
		if (bindingResult.hasErrors()) {
			ArrayList<String> errMsgList = new ArrayList<String>();
			List<FieldError> list = bindingResult.getFieldErrors();
			FieldError error = null;
			for (int i = 0; i < list.size(); i++) {
				error = list.get(i);
				errMsgList.add(error.getDefaultMessage());
				logger.warn(error.getDefaultMessage());
			}

			return TEASCommon.STR_CN_INVALID_PARAM;
		}
		ArrayList<ResolveResultType> list_child_type = resolveResultTypeServiceImpl.list_child_type(resolveResultTypeForm);
		
		if(list_child_type.size()>0){
			return TEASCommon.STR_CN_DELE_WORING;
			
		}
		
		
	  // 此处进行数据库操作
	  int  result = resolveResultTypeServiceImpl.delete(resolveResultTypeForm);

		if (result != -1) {
			return TEASCommon.STR_CN_DELETEYTYPE_SUCCESS;
		} else {
			return TEASCommon.STR_CN_DELETETYPE_FAILED;
		}

	}

}
