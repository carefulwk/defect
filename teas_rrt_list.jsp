<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="edge" />
<LINK href="css/TEASCommon.css" rel="stylesheet" type="text/css">
<LINK href="css/adminRrt.css" rel="stylesheet" type="text/css">
 
<script language=JavaScript src="js/jquery.min.js" type=text/javascript></script>
<script language=JavaScript src="js/jquery.simplePagination.js" type=text/javascript ></script>
<SCRIPT language=JavaScript src="js/teas.js" type=text/javascript></SCRIPT>
<TITLE>TE解析结果管理系统 - 解析结果类别管理</TITLE>
</HEAD>
  
<BODY onload="doRrtLoad()" onkeydown="doRrtKeyDown()" >

<!-- inputArea start -->
<div id="mainFrame">
<jsp:include page="teas_top_menu.jsp" />
<div id="queryArea">
<form name="queryForm" action="/rrt_type" method="get">
<input name="pageSelected" type="hidden" value="<c:if test="${not empty pageselected}"><c:out value="${pageselected}"/></c:if>" />
<input name="itemTotal" type="hidden" value="<c:if test="${not empty listsize}"><c:out value="${listsize}"/></c:if>" />
 <script type="text/javascript">
		 
		 window.onload=function(){
		 
		var add= document.getElementById("add_sign").value;
		 
		   if(add=="add"){
		   showAddRrtDialog();
		   }
		 
		 }
	</script>
<table>
	<tr>
		<td rowspan="3" class="label"><img src="css/img/esl_confidential_label.bmp"></td>
		<td colspan="9" class="title">解析结果类别管理</td>
		
		 <input id="add_sign"  name="add_sign"  type="hidden" value="${add_sign}"  />
	 </tr>
	<tr>
		<td>主（标题）： <select id="main_title_id" name="main_title_id"   onchange="getOneType();">
                        <option value="">--请选择--</option>
								<c:if test="${not empty re_result_List}">
			
 							<c:forEach var="result_info" items="${re_result_List}">
										<option value="${result_info.main_title_id}"  
									<c:if test="${result_info.main_title_id==main_title_id}">selected='true'</c:if> 
										
										 >${result_info.main_title_name}</option>

                                    </c:forEach>
								</c:if>
						</select>

						</td>
		<td>日期：<input id="create_time" name="create_time" type="datetime" maxlength="10" value="<c:if test="${not empty create_time}"><c:out value="${create_time}" /></c:if>" /></td>
        <td> 
		</td>
		<td > </td>
		<td  > </td>
		<td  > </td>
	</tr>

					<tr>
						<td>一级分类： <select id="one_type_id" name="one_type_id"
							onchange="getTwoType();">
                 	</select>
 
						</td>


						<td>二级分类： <select id="two_type_id" name="two_type_id"
							onchange="getThreeType();">


						</select>

						</td>
						<td>三级分类： <select id="three_type_id" name="three_type_id">


						</select>




						</td>
						<td class="btn"><input type="button" value="查询"
							onclick="queryRrtInfo()" /></td>
						<td class="btn"><input type="button" value="新增"
							onclick="showAddRrtDialog()" /></td>
						<td class="btn"><input type="button" value="返回菜单"
							onclick="gotoMenu()" /></td>
					</tr>
				</table>
</form>
</div>
</div>
<!-- inputArea end -->

<!-- resultArea start -->
<div id="mainFrame">
<div id="resultArea">
<table name="mteTable" id="mteTable">
	<tr class="title">
		<td class="objectNo">编号</td>	
		<td>主(标题)</td>
		<td>一级分类</td>
		<td>二级分类</td>
		<td>三级分类</td>
		<td>创建者</td>
		<td>创建时间</td>
		<td>更新者</td>
		<td>更新时间</td>
	</tr>
	<c:if test="${empty rrt_InfoList}">
	<tr class="resultData">
		<td colspan="16">暂无数据</td>
	</tr>
	</c:if>
	<c:if test="${not empty rrt_InfoList}">
	<c:forEach var="rrtInfo"   items="${rrt_InfoList}" >
	
	
		<tr class="resultData">
			<td class="objectNo">${rrtInfo.objectNo}</td>
			<input type="hidden" id="main_title${rrtInfo.objectNo}" value="${rrtInfo.main_title_id}"/>
			<input type="hidden" id="one_type${rrtInfo.objectNo}" value="${rrtInfo.one_type_id}"/>
			<input type="hidden" id="two_type${rrtInfo.objectNo}" value="${rrtInfo.two_type_id}"/>
			<input type="hidden" id="three_type${rrtInfo.objectNo}" value="${rrtInfo.three_type_id}"/>
			<td><a href="#" onclick="showModifyRrtDialog(this,${rrtInfo.objectNo})">${rrtInfo.main_title_name}</a></td>
			<td>${rrtInfo.one_type_name}</td>
			 <td>${rrtInfo.two_type_name}</td>
			 <td>${rrtInfo.three_type_name}</td>
			<td>${rrtInfo.create_user}</td>
			 <td>${rrtInfo.create_time}</td>
			<td>${rrtInfo.update_user}</td>
			 <td>${rrtInfo.update_time}</td>
					
		 
		</tr>
	</c:forEach>
	</c:if>
</table>
</div>
</div>
<!-- resultArea end -->
<c:if test="${not empty listsize}"><div id="pagerDiv"><div id="pager"></div></div></c:if>
</div>

 


<div id="addRrt">
	<form name="addRrtForm" action="/rrt_add_type" method="post">
	<div id="eaInputTitle">
		 解析结果类别信息录入 
	</div>
	<div id="eaInputContent">
		<ul> 
		    <li>主(标题)　　　 ：<u id="u_title"  > <select id="add_title_id" name="add_title_id"   onchange="getOneAdd();">
                        <option value="">--请选择--</option>
								<c:if test="${not empty re_result_List}">
			
 							<c:forEach var="result_info" items="${re_result_List}">
										<option value="${result_info.main_title_id}"  
								         >${result_info.main_title_name}</option>

                                    </c:forEach>
								</c:if>
  </select> 
 
 

   &nbsp;     <u onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)"  onclick="addMain_title()">添加</u></u> </li>
		    <li>一级分类　　　：<u id="u_one"  ><select id="one_add_id" name="one_add_id"
							onchange="getTwoAdd();"></select> &nbsp;
							 <u onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)"  onclick="addOne_title()">添加</u></u>
							 
							</li>
		    <li>二级分类　　　：<u id="u_two"  ><select id="two_add_id" name="two_add_id"
							onchange="getThreeAdd();"></select>&nbsp;
						 <u onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)"  onclick="addTwo_title()">添加</u></u>
							</li>
		    <li>三级分类　　　：<u id="u_three"  ><select id="three_add_id" name="three_add_id"></select>&nbsp;
		       <u onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)"  onclick="addThree_title()">添加</u></u>
							 </li>
		    
			    			 
		</ul>
	</div>
	<div id="eaInputButton">
		<input type="button" value="提交" onclick="submitAddRrt()" />
		<input type="reset" value="重置" onclick="resetRrtInfo()"/>
		<input type="button" value="取消" onclick="hideAddRrtDialog()"/>
	</div>
	</form>
</div> 

 
<div id="modifyRrt">
	<form name="modifyRrtForm" action="/rrt_type" method="post">
  <div id="eaInputTitle">
		<tr><td>解析结果类别修改</td></tr>
	</div>
	<div id="eaInputContent2">
		<ul> 
		
	        <input type="hidden" id="text_tile_id"  />
	        <input type="hidden" id="text_one_id"  />
	        <input type="hidden" id="text_two_id"  />
	        <input type="hidden" id="text_three_id"  />
		    <li>主(标题)　　　 ：<input name="text_tile_name" id="text_tile_name" type="text" maxlength="30" /></li>
		    <li>一级分类　　　：<input name="text_one_name"  id="text_one_name"  type="text" maxlength="30" /></li>
		    <li>二级分类　　　：<input name="text_two_name"  id="text_two_name"  type="text" maxlength="30" /></li>
		    <li>三级分类　　　：<input name="text_three_name" id="text_three_name"  type="text" maxlength="30" /></li>
		  
			    			 
		</ul>
	</div>
	<div id="eaInputButton">
		<input type="button" value="修改" onclick="submitModifyRrt()" />
		<input type="button" value="删除" onclick="submitDelRrt()"/>
		<input type="button" value="取消" onclick="hideModifyRrtDialog()"/>
	</div>
	</form>	
</div> 

<!-- hidden div end -->
</BODY>

</HTML>
