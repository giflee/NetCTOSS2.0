<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteFee() {
                var r = window.confirm("确定要删除此资费吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
             function deleteFee1() {
               
                document.getElementById("operate_result_info").style.display = "block";
            }
            window.onload = function(){
            	var btnDel = document.getElementsByClassName("btn_delete");
            	for (var i = 0;i<btnDel.length;i++){
            	
            		btnDel[i].onclick = function(){
            			var r = window.confirm("确定要删除此资费吗？");
            			if(r){
            				var deleteId = this.getAttribute("data-del");
            			var that = this;
            			var xhr = new XMLHttpRequest();
            			xhr.open("GET","deleteCost.do?id="+deleteId,true);
            			xhr.send(null);
            			xhr.onreadystatechange = function(){
            				if(xhr.readyState==4){
            					
            					that.parentNode.parentNode.parentNode.removeChild(that.parentNode.parentNode);
            					document.getElementById("operate_result_info").style.display = "block";
            				}
            			}
            			}
            		}
            	}
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../login/toIndex.do" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>                      
                       
                       <c:forEach items="${cost}" var="c"> 
                       		<tr>
                       		<td>${c.costId}</td>
                       		<td><a href="fee_detail.html">${c.name}</a></td>
                            <td>${c.baseDuration}</td>
                            <td>${c.baseCost}</td>
                            <td>${c.unitCost}</td>
                            <td><fmt:formatDate value="${c.creatime}" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${c.startime}" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
                            <td>
                            	<c:if test="${c.status==0}">开通</c:if>
                            	<c:if test="${c.status==1}">暂停</c:if>
                            </td>
                            <td>                                
                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='fee_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" data-del="${c.costId}"  />
                            </td>
                       		</tr>
                       </c:forEach>
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                	<c:if test="${CostPage.currentPage==1}">
                	<a href="#">上一页</a>
                	</c:if>
                	<c:if test="${CostPage.currentPage!=1}">
                	<a href="find.do?currentPage=${CostPage.currentPage-1}">上一页</a>
                	</c:if>
                	
                    <c:forEach begin="1" end="${CostPage.totalPage}" var="i">
                    	<c:if test="${CostPage.currentPage==i}">
                    		 <a href="find.do?currentPage=${i}" class="current_page">${i}</a>
                    	</c:if>
                    	<c:if test="${CostPage.currentPage!=i}">
                    		 <a href="find.do?currentPage=${i}">${i}</a>
                    	</c:if>
                    	 
                    </c:forEach>
                  <c:if test="${CostPage.currentPage==CostPage.totalPage}">
                  	<a href="#">下一页</a>
                  </c:if>
                  <c:if test="${CostPage.currentPage!=CostPage.totalPage}">
                  	 <a href="find.do?currentPage=${CostPage.currentPage+1}">下一页</a>
                  </c:if>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
