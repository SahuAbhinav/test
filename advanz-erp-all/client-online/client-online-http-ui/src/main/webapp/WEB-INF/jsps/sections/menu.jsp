<%@page import="com.advanz.erp.masters.model.ModuleMenuMasterDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>


<style type="text/css">
#dock ul li:hover {
	background-color: #4f8ccf; repeat-x;
	border: solid 1px #A8D8EB;
}

#dock ul li.header,#dock ul li .header:hover {
	background-color: #99caf3; repeat-x;
	border: solid 1px #F1F1F1;
	width: 123px;
}

#dock>li ul {
	background-color: #F1F1F1;
}

body {
	margin: 0px;
	font-family: Arial, Sans-Serif;
	font-size: 13px;
}
/* dock */
#dock {
	background: none repeat scroll 0 0 #6EBEF9;
	height: 558px;
	left: 0;
	list-style: none outside none;
	margin: 51px 0 0;
	padding: 0;
	position: absolute;
	top: 0;
	z-index: 100;
}

#dock>li {
	width: 31px;
	height: 68px;
	margin: 0 0 1px 0;
	background-repeat: no-repeat;
	background-position: left center;
}

#dock>li:hover {
	background-position: -30px 0;
}

#dock .master:hover {
	background-position: -30px 0;
}

#dock .master {
	background-image: url(static/images/master_btn.png);
	width: 30px;
	position: absolute;
	height: 68px;
}

#dock .sales {
	background-image: url(static/images/salesdis_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 1px;
	height: 73px;
}

#dock .sales:hover {
	background-position: -30px 0;
}

#dock .purchase {
	background-image: url(static/images/purches_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 7px;
	height: 73px;
}

#dock .purchase:hover {
	background-position: -30px 0;
}

#dock .manufacturing {
	background-image: url(static/images/manufacturing_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 13px;
	height: 84px;
}

#dock .manufacturing:hover {
	background-position: -30px 0;
}

#dock .plantmaintainance {
	background-image: url(static/images/plant_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 30px;
	height: 84px;
}

#dock .plantmaintainance:hover {
	background-position: -30px 0;
}

#dock .hr {
	background-image: url(static/images/hr_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 47px;
	height: 83px;
}

#dock .hr:hover {
	background-position: -30px 0;
}

#dock .reports {
	background-image: url(static/images/reports_btn.png);
	width: 30px;
	position: absolute;
	margin-top: 63px;
	height: 83px;
}

#dock .reports:hover {
	background-position: -30px 0;
}


.heading {
	background-color: #eaf2fd;
	border: 1px solid #F1F1F1 !important;
	font-weight: bold;
	width: 168px;
}

#flexmenu1 {
	top: 52px !important;
}
</style>

<ul id="dock">
	<li><a href="#" data-flexmenu="flexmenu1" class="master"
		data-dir="h" data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu2" class="sales"
		data-dir="h" data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu3" class="purchase"
		data-dir="h" data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu4" class="manufacturing"
		data-dir="h" data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu5"
		class="plantmaintainance" data-dir="h" data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu6" class="hr" data-dir="h"
		data-offsets="8,0"></a></li>

	<li><a href="#" data-flexmenu="flexmenu7" class="reports"
		data-dir="h" data-offsets="8,0"></a></li>

</ul>
<!--HTML for Flex Drop Down Menu 1-->
<ul id="flexmenu1" class="flexdropdownmenu">
	<li class="heading">Master</li>

<% 
List masterList=null;
try{
    masterList= (ArrayList)session.getAttribute("moduleList");
if(masterList!=null){
	for(int i=0;i<masterList.size();i++){
		ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)masterList.get(i);
		if(dto.getSubMenuName()!=null){
		%>
		<li><span><a href="#" style="text-decoration: none;"><%= dto.getMenuName()%> </a> </span>
		<%}
	if(dto.getSubMenuName()==null){
			%>
			<li><span><a href="/client-online-http-ui<%=dto.getUrlLink() %>?menuId=<%=dto.getMenuId()%>" style="text-decoration: none;">
							<%= dto.getMenuName()%> </a> </span>
			<%} %>
		<% if(dto.getSubMenuName()!=null){
			List list2= dto.getSubMlist();
			%>
			<ul>
			<%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>?menuId=<%=dto2.getMenuId()%>" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}
		%></ul>	<%} %></li>
			<%
		}}}catch(Exception e){/* System.out.println("Master"); */}
%>
</ul>


<ul id="flexmenu2" class="flexdropdownmenu">
	<li class="heading">Sales</li>


<% 
List salesList=null;
try{
    salesList= (ArrayList)session.getAttribute("salesAndDistribution");

if(salesList!=null){
	for(int i=0;i<salesList.size();i++){
		ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)salesList.get(i);
		if(dto.getSubMenuName()!=null){
		%>
		<li><span><a href="#" style="text-decoration: none;">
						<%= dto.getMenuName()%> </a> </span>
		
		<%
		}
	if(dto.getSubMenuName()==null){
			%>
			<li><span><a href="/client-online-http-ui<%=dto.getUrlLink() %>?menuId=<%=dto.getMenuId()%>" style="text-decoration: none;">
							<%= dto.getMenuName()%> </a> </span>
			
			<%} %>

		<% if(dto.getSubMenuName()!=null){
			
			List list2= dto.getSubMlist();
			
			%>
			<ul>
			<%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);
		
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>?menuId=<%=dto2.getMenuId()%>" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}
		%></ul>
			
			<%} %>
			
			</li>
			<%
		}}}catch(Exception e){/* System.out.println("Sales"); */}
%>


</ul>

<ul id="flexmenu3" class="flexdropdownmenu">
	<li class="heading">Purchase</li>
	
	
	<% 
	List purchaseList= null;
	try{
    purchaseList= (ArrayList)session.getAttribute("purchaseAndInventory");
	
if(purchaseList!=null){
	for(int i=0;i<purchaseList.size();i++){
		ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)purchaseList.get(i);
		if(dto.getSubMenuName()!=null){
		%>
		<li><span><a href="#" style="text-decoration: none;">
						<%= dto.getMenuName()%> </a> </span>
		
		<%
		}
	if(dto.getSubMenuName()==null){
			%>
			<li><span><a href="/client-online-http-ui<%=dto.getUrlLink() %>?menuId=<%=dto.getMenuId()%>" style="text-decoration: none;">
							<%= dto.getMenuName()%> </a> </span>
			
			<%} %>

		<% if(dto.getSubMenuName()!=null){
			List list2= dto.getSubMlist();
			%>
			<ul>
			<%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);
		
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>?menuId=<%=dto2.getMenuId()%>" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}
		%></ul>
			
			<%} %>
			
			</li>
			<%
		}}}catch(Exception e){/* System.out.println("Purchase"); */}
%>
	
</ul>
<ul id="flexmenu4" class="flexdropdownmenu">
	<li class="heading">Manufacturing</li>
		<% 
		List manufacturingList=null;
   try{
		manufacturingList= (ArrayList)session.getAttribute("manufacturingPlanning");
  
if(manufacturingList!=null && manufacturingList.size()>0){
	for(int i=0;i<manufacturingList.size();i++){
		ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)manufacturingList.get(i);
		if(dto.getSubMenuName()!=null){
		%>
		<li><span><a href="#" style="text-decoration: none;"><%= dto.getMenuName()%> </a> </span></li>
		<%
		}
	if(dto.getSubMenuName()==null){
			%>
			<li><span><a href="/client-online-http-ui<%=dto.getUrlLink() %>?menuId=<%=dto.getMenuId()%>" style="text-decoration: none;">
							<%= dto.getMenuName()%> </a> </span>			
			<%} %>
		<% if(dto.getSubMenuName()!=null){
			List list2= dto.getSubMlist();
			%><ul><%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);		
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>?menuId=<%=dto2.getMenuId()%>" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}%></ul>
		<%}%></li><%
		}} }catch(Exception e){/* System.out.println("Manufacturing"); */}
%>
	
</ul>

<ul id="flexmenu5" class="flexdropdownmenu">
	<li class="heading">Plant Maintenance</li>
</ul>
<ul id="flexmenu6" class="flexdropdownmenu">
	<li class="heading">Human Resource</li>
	
	<% 
		List humanResourceList=null;
   try{
	   humanResourceList= (ArrayList)session.getAttribute("humanResource");
  
if(humanResourceList!=null && humanResourceList.size()>0){
	for(int i=0;i<humanResourceList.size();i++){
		ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)humanResourceList.get(i);
		if(dto.getSubMenuName()!=null){
		%>
		<li><span><a href="#" style="text-decoration: none;"><%= dto.getMenuName()%> </a> </span></li>
		<%
		}
	if(dto.getSubMenuName()==null){
			%>
			<li><span><a href="/client-online-http-ui<%=dto.getUrlLink() %>?menuId=<%=dto.getMenuId()%>" style="text-decoration: none;">
							<%= dto.getMenuName()%> </a> </span>			
			<%} %>
		<% if(dto.getSubMenuName()!=null){
			List list2= dto.getSubMlist();
			%><ul><%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);		
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>?menuId=<%=dto2.getMenuId()%>" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}%></ul>
		<%}%></li><%
		}} }catch(Exception e){/* System.out.println("Manufacturing"); */}
%>
	

</ul>



<ul id="flexmenu7" class="flexdropdownmenu">
	<li class="heading">Reports</li>
	

<% 
List list=null;
try{
list=(ArrayList) session.getAttribute("reportList");

for(int i=0;i<list.size();i++){
ModuleMenuMasterDTO dto=(ModuleMenuMasterDTO)list.get(i);

 if(dto.getSubMenuName()!=null){%>
		
	<li><span><a href="#" style="text-decoration: none;"><%=dto.getMenuName() %></a> </span>
		<%} 
   if(dto.getSubMenuName()==null){%>
  <li><span><a href="/client-online-http-ui<%=dto.getUrlLink()%>"
					style="text-decoration:none;"><%=dto.getMenuName()%></a></span>
					
		<%} %>

		<% if(dto.getSubMenuName()!=null){
			List list2= dto.getSubMlist();
			%>
			<ul>
			<%
		for(int j=0;j<list2.size();j++){
		 ModuleMenuMasterDTO dto2=(ModuleMenuMasterDTO)list2.get(j);
		
		%>
		<li><span><a href="/client-online-http-ui<%=dto2.getUrlLink()%>" target="_blank" style="text-decoration: none;"> <%=dto2.getSubMenuName()%></a> </span>
		</li>
		<%}
		%></ul>
			<%
		  }%>
		
		</li>
	
	<%}
}catch(Exception e){/* System.out.println("Reports"); */}
%>





</ul>

