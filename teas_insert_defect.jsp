<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 
<%@ page import="java.io.InputStream,java.io.FileInputStream,java.io.IOException" %>
<!DOCTYPE html>
<HTML >
<HEAD>
<LINK href="/css/TEASCommon.css" rel="stylesheet" type="text/css">
<LINK href="/css/defectFactory.css" rel="stylesheet" type="text/css">
<link href="/css/simplePagination.css" rel="stylesheet" type="text/css">

 <script language=JavaScript src="/js/jquery.min.js" type=text/javascript></script>
 <script language=JavaScript src="/js/jquery.simplePagination.js" type=text/javascript ></script>
 <SCRIPT language=JavaScript src="/js/teas.js" type=text/javascript></SCRIPT>
  <SCRIPT language=JavaScript src="/laydate/laydate.js" type=text/javascript></SCRIPT>

 

 <script src="ajaxfileupload.js" type="text/javascript"></script>
<TITLE>TE解析结果管理系统 - 不良解析结果录入</TITLE>
</HEAD>
 <style type="text/css">
        .img-div{
            border: 1px solid #ddd;
            border-radius: 100%;
            float: left;
            line-height: 1;
            margin-left: 2px;
            overflow: hidden;
            display: inline-block;
            position: relative;
        }
        
         .img_divg{
        
            float: left;
            line-height: 1px;
            margin-left: 1px;
            overflow: hidden;
            display: inline-block;
            position: relative;
        }
        .img-pic{
        
             width:20px; 
            height:20px;
        }
        
         .img-pic_big{
        
             width:200px; 
            height:200px;
        }
 
  .img_del{
        
             width:16px; 
            height:10px;
        }
        
          .img_del2{
        
             width:22px; 
            height:20px;
        }
   .deletepic {
                display: none;
                position: absolute;
                bottom: 0px;
                left: 0px;
                width: 20px;
                height: 14px;
                background: url('css/img/icon-delete.png') ;
            }
    </style>
 
 
 <script type="text/javascript">
    //选择图片，马上预览
    function xmTanUploadImg(obj,m) {
 
        var fl=obj.files.length;
       
        var t2="imgboxid"+m;
        
      
        for(var i=0;i<fl;i++){
        
                
            var file=obj.files[i];
            var reader = new FileReader();
 
            //读取文件过程方法
          
            reader.onload = function (e) {
                console.log("成功读取....");
                
 
        var imgid="imgid"+m+i;
         var imgstr='<img  class="img-pic"   id="'+imgid+'"  onmouseout="recover_pic(this)"  onclick="disply_big(this)"  src="'+e.target.result+'"/>';
           var imgdel=' <img class="img_del"  src="css/img/del_pic.jpg"  onclick="delete_pic(this)"  onmouseover="delepic(this)"  onmouseout="recover(this)"/> ';
                var oimgbox=document.getElementById(t2);
                var ndiv=document.createElement("div");
               var dndiv=document.createElement("div");
               dndiv.innerHTML=imgdel;
                ndiv.innerHTML=imgstr;
                ndiv.appendChild(dndiv);
                ndiv.className="img_divg";
          
                oimgbox.appendChild(ndiv);
               
            }
 
          reader.readAsDataURL(file);

        }
 
    }
 
    function delete_pic(obj){
 
       var par=obj.parentNode.parentNode; 
      par.parentNode.removeChild(par);
  }
     

 
  
  function  uploadpic(index,model_type,lot_seq_sn) {
  
    var img_obj="";
 var imgbo= document.getElementById("imgboxid"+index);
  var count=imgbo.childElementCount;
  var f =imgbo.getElementsByTagName('img');
    for(var i=0;i<count*2;i++){  
      var  imsrc= f[i].src;
      if(imsrc!=null&&imsrc!=""){
   if(img_obj==""){
     img_obj=imsrc;
   
    }
    else{
    img_obj=img_obj.concat(imsrc);
    }
    }
  }
 
  var serial=  document.getElementById("serial").value;
 	var postData = {
		"lot_seq_sn" :lot_seq_sn,
		"model_type" :model_type,
		"serial" :serial,
		 "img_obj":img_obj
	};
   	$.ajax({
		type : "post",
		url : "/insert_defect_pic",
	    data : postData,

		success : function(data) {
		if(data){
			alert("上传成功");
			}
			else{
			alert("上传失败");
			}
			 
		},
		error : function(data) {
			alert("一次不能上传太多！");
		}
	});
    } 
 
 
      
</script>

<BODY onload="doModelAdminLoad()" onkeydown="doModelAdminKeyDown()">
 
 
<!-- inputArea start -->
<div id="mainFrame">
<jsp:include page="teas_top_menu.jsp" />
 
<div id="queryArea">

<input name="pageSelected" type="hidden" value="<c:if test="${not empty pageselected}"><c:out value="${pageselected}"/></c:if>" />
<input name="itemTotal" type="hidden" value="<c:if test="${not empty listsize}"><c:out value="${listsize}"/></c:if>" />

<table >
	<tr>
		<td   class="label" width="5%"  align="left" ><img src="/css/img/esl_confidential_label.bmp" align="left" >
	 	</td>
	 <td  class="title" colspan="3">不良解析结果录入</td>
	</tr>
	 
 
	
	<tr>
		  
		 <td   width="5%" >&nbsp;</td>
		 <td   width="5%" >&nbsp;</td>
		 <td   width="5%" >&nbsp;</td>
		<td class="btn"     align="right" ><input type="button" value="返回菜单" onclick="gotoMenu()"  /></td> 
		 
	</tr>
</table>

</div>
</div>
<!-- inputArea end -->

<!-- resultArea start -->
<div id="mainFrame"    style="overflow-x: auto;" >
<div id="resultArea"  style="width:2200px;" >
 <form name="queryForm" action="/insert_defect_save" method="post">
<table name="mteTable" id="mteTable">
	
	<tr class="title">
	
		<td rowspan=2 class="objectNo">编号</td>	
		
		<td rowspan=2>LOT</td>
		<td rowspan=2>HEADID</td>
		 
		<td rowspan=2>不良数量</td>
	 
		<td rowspan=2>不良项目</td>
	    <td  rowspan=2>解析结果数量</td>
	    <td rowspan=2>不良列别</td>
		<td rowspan=2>不良NZ NO#</td>
	
		 <td colspan=4>不良地址位</td>
		 
		 <td  rowspan=2>不良CAV#</td>
		 <td  rowspan=2>解析结果</td>
		 <td  rowspan=2>材质分析结果</td>
		 <td  rowspan=2>再生判定</td>
		 <td  rowspan=2>激光切割</td>
		  <td  rowspan=2>备注</td>
		   <td  rowspan=2>操作</td>
		 <td  rowspan=2 colspan=2>图片详情</td>
	 	
	</tr>
	
	 <tr class="title">
     <td>np</td>
			<td>SI-CAV</td>
			<td>SHEET</td>
	  <td>actunit</td>
			
    </tr> 
	<c:if test="${empty insertDefectInfoList}">
	<tr class="resultData">
		<td colspan="22">暂无数据</td>
	</tr>
	</c:if>
<%!   
    String GetImageStr(String imgsrc)
    {   
        //String imgFile = "1.jpg" ; //待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(imgsrc);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
   	   e.printStackTrace();
        }
        //对字节数组Base64编码
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        //try{
            return encoder.encode(data); //返回Base64编码过的字节数组字符串
        //}
        //catch(Exception e){
        //   return null;
        //}
    }
%>
	<c:if test="${not empty insertDefectInfoList}">
	<c:forEach var="insertDefectInfo"   items="${insertDefectInfoList}" >
	
 
	<input name="serial" id="serial"  type="hidden" value="${serial}"  />
	 
	
 
  <input name="lot_seq_sn"   type="hidden" value="${insertDefectInfo.lot_seq_sn}"  />
  <input name="lot" id="lot"  type="hidden" value="${insertDefectInfo.lot}"  />
  <input name="head_id" id="head_id"  type="hidden" value="${insertDefectInfo.head_id}"  />
  <input name="defect_qty_str" id="defect_qty_str"  type="hidden" value="${insertDefectInfo.defect_qty}"  />
  <input name="model_type" id="model_type"  type="hidden" value="${insertDefectInfo.model_type}"  />
  <input name="picture" id="picture"  type="hidden" value="${insertDefectInfo.picture}"  />
  <input name="creat_dt" id="creat_dt"  type="hidden" value="${insertDefectInfo.creat_dt}"  />
  <input name="last_maint_dt" id="last_maint_dt"  type="hidden" value="${insertDefectInfo.last_maint_dt}"  />
     	<input name="put_qty_str" id="put_qty_str"  type="hidden" value="${insertDefectInfo.put_qty}"  />
     
     <input name="imgList" id="imgList"  type="hidden" value="${insertDefectInfo.imgList}"  /> 	
		<tr class="resultData">
		 <td class="objectNo">${insertDefectInfo.objectNo}</td>
		
		 <td>${insertDefectInfo.lot}  </td>
		 	 
			<td>${insertDefectInfo.head_id}</td>
			 
		 <td>${insertDefectInfo.defect_qty}</td>
							<td><input name="defect_item"  id="defect_item${insertDefectInfo.objectNo}"  type="text" 
							
						value="<c:if test="${not empty insertDefectInfo.defect_item}"><c:out value="${insertDefectInfo.defect_item}" /></c:if>" 	 size="5" /></td>

							<td><input name="resolve_result_qty_str" id="resolve_result_qty_str${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.resolve_result_qty}"><c:out value="${insertDefectInfo.resolve_result_qty}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="defect_column" id="defect_column${insertDefectInfo.objectNo}" 
							value="<c:if test="${not empty insertDefectInfo.defect_column}"><c:out value="${insertDefectInfo.defect_column}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="defect_nz_no"  id="defect_nz_no${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.defect_nz_no}"><c:out value="${insertDefectInfo.defect_nz_no}" /></c:if>"  type="text" size="5" /></td>
                         	<td><input name="defect_np" id="defect_np${insertDefectInfo.objectNo}" 
                         	 value="<c:if test="${not empty insertDefectInfo.defect_np}"><c:out value="${insertDefectInfo.defect_np}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="si_cav" id="si_cav${insertDefectInfo.objectNo}"   
							value="<c:if test="${not empty insertDefectInfo.si_cav}"><c:out value="${insertDefectInfo.si_cav}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="sheet" id="sheet${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.sheet}"><c:out value="${insertDefectInfo.sheet}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="defect_actunit" id="defect_actunit${insertDefectInfo.objectNo}"   
							value="<c:if test="${not empty insertDefectInfo.defect_actunit}"><c:out value="${insertDefectInfo.defect_actunit}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="defect_cav" id="defect_cav${insertDefectInfo.objectNo}"   
							value="<c:if test="${not empty insertDefectInfo.defect_cav}"><c:out value="${insertDefectInfo.defect_cav}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="resolve_result" id="resolve_result${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.resolve_result}"><c:out value="${insertDefectInfo.resolve_result}" /></c:if>"  type="text" size="5" /></td>
                            <td><input name="material_report" id="material_report${insertDefectInfo.objectNo}"  
                            value="<c:if test="${not empty insertDefectInfo.material_report}"><c:out value="${insertDefectInfo.material_report}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="regrow_judge" id="regrow_judge${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.regrow_judge}"><c:out value="${insertDefectInfo.regrow_judge}" /></c:if>"  type="text" size="5" /></td>
							<td><input name="laser_cut" id="laser_cut${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.laser_cut}"><c:out value="${insertDefectInfo.laser_cut}" /></c:if>"   type="text" size="5" /></td>
							<td><input name="remark" id="remark${insertDefectInfo.objectNo}"  
							value="<c:if test="${not empty insertDefectInfo.remark}"><c:out value="${insertDefectInfo.remark}" /></c:if>"  type="text" size="5" /></td>
							<td ><u  onclick="copycontent(${insertDefectInfo.objectNo})"  onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)">复制</u> 
	                     |<u   onclick="pastcontent(${insertDefectInfo.objectNo})"   onmouseover="modifycolor(this)"   onmouseout="recovercolor(this)">粘贴</u></td>
							<td width="5">
							
						 
							
	     <input type="file" id="xdaTanFileImg"  multiple="multiple"  name="fileAttach" onchange="xmTanUploadImg(this,${insertDefectInfo.objectNo})"/>
	     
	       <div class="img-box" id="imgboxid${insertDefectInfo.objectNo}"  >
               </div>
          <div  id="imgboxidz${insertDefectInfo.objectNo}"  >
           
  <c:if test="${not empty insertDefectInfo.imgList}">
	<c:forEach var="imginfo"   items="${insertDefectInfo.imgList}" >
	<div class="img_divg">
	<div>
    <img   class="img-pic"  src="/showImg?imginfo=${imginfo}"   onmouseout="recover_pic(this)"  onclick="disply_big(this)" /> 
    </div>
    <div>
    <img   class="img_del"  src="css/img/del_pic.jpg"  onmouseover="delepic(this)"  onmouseout="recover(this)"  onclick="delete_img(this,'${imginfo}')"/> 
    </div>
    </div>
   
	 </c:forEach>
	</c:if>
 
 
	
          </div>
          	</td>
         	<td width="5">
	            <div id="errordiv"   style="margin-top:15px;width:100%;text-align:center;">
	          <input id="btpic" type="button" onclick="uploadpic(${insertDefectInfo.objectNo},'${insertDefectInfo.model_type}','${insertDefectInfo.lot_seq_sn}')" value="提交" /> 
	            
	          
            </div>
							</td>
	
  
		  
		</tr>
	</c:forEach>
	</c:if>
	
	
	<tr class="title" >
	<td colspan=21  style="height:50px" align="center">
	<input type="button"  value="保存" onclick="saveInDefectInfo()" style="width: 80px"/>&nbsp;&nbsp;&nbsp;&nbsp;
	 
    <input type="reset"  value="重置" onclick="clearInsert()"  style="width: 80px"/>
	
	</td>
	</tr>
	
 <script type="text/javascript">
 function  clearInsert(){
   document。queryForm.reset();
   }
 
 function disply_big(obj){
 obj.className='img-pic_big';
  }
  function recover_pic(obj){
 
 obj.className='img-pic';
 
 }
 
function getBase64Image(img) {
        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, img.width, img.height);
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();
        var dataURL = canvas.toDataURL("image/"+ext);
        return dataURL;
}

 

 </script>
</table>
 </form>
</div>
</div>
<!-- resultArea end -->
<c:if test="${not empty listsize}"><div id="pagerDiv"><div id="pager"></div></div></c:if>
 


 
 

 
<!-- hidden div end -->
</BODY>

</HTML>
