package com.advanz.erp.client.http.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class MailReport {
	private static final String[] sendTo = { "ltsjasperserver@gmail.com" };
	@Autowired
	DataSource dataSource;
	public static void main(String[] args) throws Exception {
		MailReport m = new MailReport();
		m.mailSend();
	}

	/*
	 * public ReportMail(){
	 * 
	 * }
	 */
	
	
	public void mailSend() {
		File pdffile = null;
		
		Date fromDate=null;
		Date toDate=null;
		String itemName=null;
		String pendingStatus=null;
		String itemcategory=null;
		String activeStatus=null;
		
		
		try {
			Connection conn = dataSource.getConnection();
			// Get the jasper report object
			// Load it
			InputStream reportStream = this.getClass().getResourceAsStream(
					"/reports/Pending_Indent_Report.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

			
			File directory = new File(".");
			String reportName="ABC.pdf";
			URL reportFileURL = getClass().getResource(
					File.separator + "Reports" + File.separator + reportName
							+ "_schedule.jasper");

			pdffile = new File(directory.getCanonicalPath() + File.separator
					+ reportName + ".pdf");
			
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("FromDatePrompt", fromDate);
			parameterMap.put("ToDatePrompt", toDate);
			if(itemName!=null && !itemName.equals("All")){
				parameterMap.put("ItemNamePrompt", itemName);
				}
				if(pendingStatus!=null && !pendingStatus.equals("All")){
					parameterMap.put("PendingPrompt ", pendingStatus);
					}
				
					if(activeStatus!=null && !activeStatus.equals("All")){
					parameterMap.put("Item_Status_Prompt", activeStatus);
					}
						
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameterMap, conn);
			// Create report exporter to be in Html
			JRExporter exporter;
			exporter = new JRPdfExporter();
			FileOutputStream fos = new FileOutputStream(pdffile);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.exportReport();
			// Export the report, store to sb
			exporter.exportReport();

			conn.close();
			 String []emailIds=new String[1]; 
			 emailIds[0]="anildhkd26@gmail.com";
			//MailPdf mail = new MailPdf(pdffile.getName());
			 MailPdf mail = new MailPdf(pdffile,reportName);
			 
			 System.out.println("SSSSSSSSSSSSSS........."+pdffile.length());
			mail.sendSSLMessage(emailIds, "ABC.pdf", "","abc@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return result;
	}
}
