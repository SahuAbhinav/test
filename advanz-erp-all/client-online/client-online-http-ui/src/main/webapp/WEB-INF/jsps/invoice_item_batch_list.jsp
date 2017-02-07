<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<script type="text/javascript" charset="utf-8">

$(document).ready(function() {
    /* Initialise datatables */
    var oTable = $('#example').dataTable({
    	 
		       "aLengthMenu": [['',10, 25, 50, -1], ['',10, 25, 50, "All"]],
           "iDisplayLength":10000,
           bInfo:""
		   // "bStateSave": true
	       });
    
    
} );            </script>

  <title> Batch  List</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	 <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/maxlength.js"></script>
	<script type="text/javascript" src="../jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.1/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.1/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.1/themes/default/easyui.css"> 
    <link rel="stylesheet" href="../css/validationEngine.jquery.css" type="text/css"/>
	<link rel="stylesheet" href="../css/template.css" type="text/css"/>
	   <link rel="stylesheet" href="../jquery-easyui-1.3.1/themes/base/jquery-ui.css" />
	   <link rel="stylesheet" href="../css/style/style.css"/>
	<script src="../js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8">	</script>
	<script src="../js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/ui/1.9.0/jquery-ui.js"></script>
	<script type="text/javascript">
			
			$(document).ready(function() {  	
				
			 
				$('.fixmyheader-8').fixheadertable({
					caption		: 'My header is fixed !',
					height		:450,
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
	
th{font-size:10px;}
 td{font-size:12px;}  
 
 	
	div.t_fixed_header_main_wrapper.ui div.t_fixed_header_caption {
		display:none;
		
		}
 	
</style>
	<script>
		jQuery(document).ready(function(){
			
			$('.sr').click(function() {
				$('.sr').css("background-color","white");
				$(this).css("background-color","yellow");
				 // $('#itemId').val($(this).attr('id'));
				
				  $('#batchNoId').val($(this).attr('id'));
			});
			
			
	    // binds form submission and fields to the validation engine
			jQuery("#formID").validationEngine();
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "HELLO") {
				// this allows to use i18 for the error msgs
				return options.allrules.validate2fields.alertText;
			}
		}
	</script>
    
    <script type="text/javascript">


    
$(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
  $(document).ready(function(){ 
    //called when key is pressed in textbox
	$(".quantity1").keypress(function (e)  
	{ 
	  //if the letter is not digit then display error and don't type anything
	  if( e.which!=8 && e.which!=46 && e.which!=0 && (e.which<48 || e.which>57))
	  {
		//display error message
		$(".errmsg1").html("Digits Only").show().fadeOut("slow"); 
	    return false;
      }	
	});

  });
   
  </script>

  
 <script type="text/javascript">
      $(document).ready(function()
       {
       $("button").button();
    	  //$('input:text, input:password').button()   
	    $(".datepicker" ).datepicker();
  //      $(":submit").button()
      });
  </script>
     
<style type="text/css">


 
  p { color:blue; }
  .errmsg { color:red;
 
 }
	/*input {
	width:87%;
	margin-bottom:6px;
	}*/	
    .bn{width:68px !important; border:none !important;}
   .md{width:78px !important; border:none !important;}
   .ed{width:78px !important; border:none !important;}
   .stock{width:78px !important; border:none !important;}
   .br{width:78px !important; border:none !important;}
   .mrp{width:72px !important; border:none !important;}
 
</style>

</head>
<body>
  	<form:form name="input" action="submitInvoiceItemBatchList"  method="GET" modelAttribute="batchForm">
  	<form:hidden path="indexNo" />
    <form:hidden path="batchDTO.itemDTO.itemId" value="${batch.itemDTO.itemId}" id="itemId"/>
    
    <form:hidden path="batchDTO.batchNo" value="${batch.batchNo}" id="batchNoId"/>
  <div class="panel-header">
	<div class="panel-title">  Batch  List</div>
	<div class="panel-tool"></div>
  </div>
  
 
     
 <div class="headingdiv" >
	  <table width="453" height="31" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <!-- 
          <td width="162"><div align="center">Batch No</div></td>
          <td width="228">
          <input type="text" name="" size="10" id="" /></td>
          <td width="75"><input class="searchbtn" style="font-size: 11px; font-weight: bold;  padding: 0 0 0 20px;" type="submit" value=" "/></td>
           -->
          
          <td width="747">
          <input name="operation" type="submit" class="okbtn"  style="font-size: 11px; font-weight: bold; border:0px; float:right !important;  padding: 0 0 0  ;" value=" " id="okId" onclick="this.value='OK';"/>   
           </td>
	       <td width="45"><input name="operation" type="submit" class="cancelbtn"  style="font-size: 11px; font-weight: bold; border:0px;  padding: 0 0 0 10px;" value=" " onclick="this.value='Cancel';"/></td>
        </tr>
      </table>
	</div>
   <div class="gridheadingdiv" style="float:left">
  <table width="972" class="display fixmyheader-8" id="example">
  <thead>
   <tr>

        <td class="bn" width="68"><div align="center"><strong>Batch No.</strong></div></td>
        <td class="md" width="78"><div align="center"><strong>Mfg Date</strong></div></td>
        <td class="ed" width="78"><div align="center"><strong>Exp Date</strong></div></td>
        <td class="stock" width="78"><div align="center"><strong>Stock</strong></div></td>
        <td class="br" width="78"><div align="center"><strong>Basic Rate</strong></div></td>
        <td class="mrp" width="78"><div align="center"><strong>M. R. P.</strong></div></td>
       </tr>
  </thead>
  <tbody>       <c:forEach items="${batchList}" var="batch">
        <tr class="sr" style="cursor: pointer;" id="${batch.batchNo}">
        <td width="58" >&nbsp;<c:out value="${batch.batchNo}"/></td>
        <td width="68">&nbsp;<fmt:formatDate pattern="dd-MMM-yyyy"
								value="${batch.mfgDate}" /></td>
        <td width="68">&nbsp; <fmt:formatDate pattern="dd-MMM-yyyy"
								value="${batch.expiryDate}" />
        </td>
        <td width="68" style="text-align: right;" align="right">&nbsp;
        <fmt:formatNumber value="${batch.stockTotal}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
        </td>
        <td width="68" style="text-align: right;" align="right">&nbsp;
        <fmt:formatNumber value="${batch.invoiceRate}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
        </td>
        <td width="39" style="text-align: right; padding-right: 37px;" align="right">&nbsp;
        <fmt:formatNumber value="${batch.mrp}" type="number"  minFractionDigits="2" maxFractionDigits="2"/>
        </td>
        </tr>
        </c:forEach>
  
  </tbody>
</table>
  </div>

  
  </form:form>
   </body>
</html>