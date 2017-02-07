<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" %>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
			$(document).ready(function() {  	
				
				
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:412,
					addTitles	: true,
					colratio	: ['10%', '10%', '8%', '50px', 'auto', 'auto', '30%', 'auto']
				});
			});
		</script>
  
     
<style type="text/css">


body {
	font-family:Arial, Helvetica, sans-serif;
	}
code, pre {				
				padding		: 10px;	
				background	: #F5F5F5;
				border		: 1px solid #D4D4D4;
				overflow-x	: auto;
				font-size	: 12px;
			}	
	.ui-widget-content {
overflow-x: hidden !important;
 
}
th{font-size:10px;}
 td{font-size:12px;}  
  .View	{	 width: 34px !important; border:none !important;		}
 	   .Ac {width:51px !important;}
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
	 .rn{width:230px !important; border:none !important }
   .rc{width:154px !important; border:none !important }
  .static{width:154px !important; border:none !important }
  .desp{width:195px !important; border:none !important }
  .ac{width:62px !important; border:none !important } 
</style>

  

<form:form name="input" action="getReportLinkList" class="formdiv" method="get" commandName="masterDTO" >

	<div class="header">List view of Reports</div>
	<div class="headingdiv">
		<table width="880" height="31" border="0" cellpadding="0"
			cellspacing="0">
	<tr>
	<td>
	 Group Name
	</td>
	<td>
	<form:input path="menuName" />
	</td>
	<td>
	Report Name
	</td>
	<td>
	<form:input type="text" path="subMenuName" />
	</td>
	<td>
	Description
	</td>
	<td>
	<form:input type="text" path="description" />
	</td><td width="68"><input class="searchbtn" type="submit"
					name="operation" value="" onclick="this.value='Search';" /></td>
          <td width="71"> <a href="getReportLinkList" class="cancelbtn" > </a></td>
	</tr>
		</table>
	</div>
	<div id="demo">
	<div class="gridheadingdiv">
	<table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>	
    <td  width="27"><div align="center">S. No.</div></td>   
     <td class="rn" width="230"><div align="center">Group Name</div></td>
          <td class="rn" width="230"><div align="center">Report Name</div></td>
          <td class="desp" width="195"><div align="center">Description</div></td>
        </tr>
  </thead>
  <tbody> 
        
			<c:forEach items="${reportList}" var="report" varStatus="s">
				<tr >
				<td width="20"><c:out value="${s.count}" />
				</td>
				<td width="230">
				<c:out value="${report.menuName}" />
				</td>
			
				<td width="230">
				<a href="/client-online-http-ui<c:out value="${report.urlLink}" />" style="text-decoration: none;" target="_blank"> <c:out value="${report.subMenuName}" /></a>
				</td>
				<td width="230">
				<c:out value="${report.description}" />
				</td>
				</tr>
			</c:forEach>
  
  </tbody>
</table>
  </div>
  </div>
  
 </form:form>