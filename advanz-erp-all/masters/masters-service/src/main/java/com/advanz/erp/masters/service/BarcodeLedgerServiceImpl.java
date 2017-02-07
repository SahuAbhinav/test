package com.advanz.erp.masters.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BarcodeLedgerEntity;
import com.advanz.erp.masters.model.BarcodeLedgerDTO;
import com.advanz.erp.masters.model.criteria.BarcodeLedgerSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BarcodeLedgerInputMessage;
import com.advanz.erp.masters.model.msg.BarcodeLedgerOutputMessage;
import com.advanz.erp.masters.service.business.IBarcodeLedgerService;
import com.advanz.erp.masters.storage.IStorageBarcodeLedgerDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Abhinav Sahu
 *
 */
public class BarcodeLedgerServiceImpl implements IBarcodeLedgerService {
	private static final Logger logger = LoggerFactory
			.getLogger(BarcodeLedgerServiceImpl.class);

	public static final String CREATE_BARCODE = "CreateBarcode";
	public static final String UPDATE_BARCODE = "UpdateBarcode";
	public static final String DELETE_BARCODE = "DeleteBarcode";
	public static final String FIND_BARCODE_BY_ID = "FindBarcodeById";
	public static final String FIND_ALL_BARCODE = "FindAllBarcodes";
	public static final String SEARCH_BARCODE = "SearchBarcodes";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();

	@Autowired
	public IStorageBarcodeLedgerDAO storageBarcodeLedgerDAO;

	public BarcodeLedgerInputMessage barcodeLedgerInputMessage;

	public BarcodeLedgerOutputMessage barcodeLedgerOutputMessage;

	String printerName;

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	@Override
	public BarcodeLedgerOutputMessage createBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage) {

		flowId = CREATE_BARCODE;
		// assign the message to the class level variable.
		this.barcodeLedgerInputMessage = barcodeLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return barcodeLedgerOutputMessage;
	}

	@Override
	public BarcodeLedgerOutputMessage updateBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage) {

		flowId = UPDATE_BARCODE;
		// assign the message to the class level variable.
		this.barcodeLedgerInputMessage = barcodeLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return barcodeLedgerOutputMessage;
	}

	@Override
	public BarcodeLedgerOutputMessage deleteBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage) {
		flowId = DELETE_BARCODE;
		// assign the message to the class level variable.
		this.barcodeLedgerInputMessage = barcodeLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return barcodeLedgerOutputMessage;

	}

	@Override
	public BarcodeLedgerOutputMessage findBarcodeById(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage) {
		flowId = FIND_BARCODE_BY_ID;
		// assign the message to the class level variable.
		this.barcodeLedgerInputMessage = barcodeLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return barcodeLedgerOutputMessage;

	}

	@Override
	public BarcodeLedgerOutputMessage findAllBarcode() {
		flowId = FIND_ALL_BARCODE;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return barcodeLedgerOutputMessage;
	}

	@Override
	public BarcodeLedgerOutputMessage search(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage) {
		flowId = SEARCH_BARCODE;
		this.barcodeLedgerInputMessage = barcodeLedgerInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return barcodeLedgerOutputMessage;

	}

	@Override
	public boolean validateInput() {

		if (CREATE_BARCODE.equals(flowId)) {
			return true;
		} else if (UPDATE_BARCODE.equals(flowId)) {
			return true;
		} else if (DELETE_BARCODE.equals(flowId)) {
			return true;
		} else if (FIND_BARCODE_BY_ID.equals(flowId)) {
			return true;
		} else if (FIND_ALL_BARCODE.equals(flowId)) {
			return true;
		} else if (SEARCH_BARCODE.equals(flowId)) {
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

		BarcodeLedgerEntity barcodeLedgerEntity = new BarcodeLedgerEntity();
		if (barcodeLedgerInputMessage != null) {
			BarcodeLedgerDTO barcodeLedgerDTO = barcodeLedgerInputMessage
					.getBarcodeLedgerDTO();
			if (barcodeLedgerDTO != null) {
				BeanUtils.copyProperties(barcodeLedgerDTO, barcodeLedgerEntity);

			}
		}

		if (CREATE_BARCODE.equals(flowId)) {

			// storageBarcodeLedgerDAO.create(barcodeLedgerEntity);
			List<BarcodeLedgerEntity> barcodeLedgerEntityList = new ArrayList<BarcodeLedgerEntity>();
			BarcodeLedgerEntity newBarcodeLedgerEntity = null;
			for (int i = 0; i < barcodeLedgerEntity.getNoOfBarcode(); i++) {
				newBarcodeLedgerEntity = storageBarcodeLedgerDAO
						.createBarcode(barcodeLedgerEntity);
				barcodeLedgerEntityList.add(newBarcodeLedgerEntity);
			}
			logger.info("barcodeLedgerEntityList"
					+ barcodeLedgerEntityList);
			// Print Barcode Code will be here.
			logger.info(
					"barcode Entity before print the Barcode in barocde Service:",
					barcodeLedgerEntity);
			try {
				generateBarcode(barcodeLedgerEntityList);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (UPDATE_BARCODE.equals(flowId)) {

			storageBarcodeLedgerDAO.update(barcodeLedgerEntity);

		} else if (DELETE_BARCODE.equals(flowId)) {
			storageBarcodeLedgerDAO.delete(barcodeLedgerEntity);
		} else if (FIND_BARCODE_BY_ID.equals(flowId)) {
			List<BarcodeLedgerEntity> list = storageBarcodeLedgerDAO
					.findById(barcodeLedgerEntity.getId());
			popUpList(list);
		} else if (FIND_ALL_BARCODE.equals(flowId)) {
			List<BarcodeLedgerEntity> list = storageBarcodeLedgerDAO.load();
			popUpList(list);
		} else if (SEARCH_BARCODE.equals(flowId)) {
			BarcodeLedgerSearchCriteriaDTO searchCriteria = barcodeLedgerInputMessage
					.getBarcodeLedgerSearchCriteriaDTO();
			List<BarcodeLedgerEntity> list = storageBarcodeLedgerDAO.search(
					searchCriteria.getItemId(), searchCriteria.getBarcode(),
					searchCriteria.getTransactionType(),
					searchCriteria.getTransactionId());
			popUpList(list);
		}
	}

	void popUpList(List<BarcodeLedgerEntity> list) {
		barcodeLedgerOutputMessage = new BarcodeLedgerOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<BarcodeLedgerDTO> resultList = new ArrayList<BarcodeLedgerDTO>();
			BarcodeLedgerDTO barcodeLedgerDTO;
			for (BarcodeLedgerEntity entity : list) {
				barcodeLedgerDTO = new BarcodeLedgerDTO();
				// Spring
				BeanUtils.copyProperties(entity, barcodeLedgerDTO);
				resultList.add(barcodeLedgerDTO);
			}
			barcodeLedgerOutputMessage.setBarcodeLedgerDTOsList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_BARCODE.equals(flowId)) {

		} else if (UPDATE_BARCODE.equals(flowId)) {

		} else if (DELETE_BARCODE.equals(flowId)) {

		} else if (FIND_BARCODE_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_BARCODE.equals(flowId)) {

		} else if (SEARCH_BARCODE.equals(flowId)) {

		}
	}

	/**
	 * @author abhinav
	 * @see This is new implementaion for List of Records
	 * @param barcodeLedgerEntityList
	 * @throws Exception
	 */
	public void generateBarcode(
			List<BarcodeLedgerEntity> barcodeLedgerEntityList) throws Exception {

		String property = "java.io.tmpdir";
		String tempDir = System.getProperty(property);
		File directory = new File(tempDir);

		String filePath = directory.getCanonicalPath() + File.separator
				+ "ERP_BarCode_128" + System.currentTimeMillis() + ".pdf";
		logger.info("filePath in source" + filePath);
 // old 145,142 
		/**new Rectangle(135, 132)
		 * barImg1.setAbsolutePosition(40f, 50f);
		 * barImg2.setAbsolutePosition(40f, 15f);
		 */
		Document document = new Document(new Rectangle(145, 142), 1, 1, 1, 1);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(filePath));
		Barcode128 code128 = null;
		URL imagePath = this.getClass().getResource(
				File.separator + "image" + File.separator + "erp_1.PNG");
		logger.info("getClass().getResource()"+ imagePath);
		Image barImg = Image.getInstance(imagePath);
		document.open();
		// barImg.setAbsolutePosition(0f, 0f);
		/**
		 * create multiple barcode according to quantity.
		 */
		BarcodeLedgerEntity barcodeLedgerEntity = new BarcodeLedgerEntity();
		for (int i = 0; i < barcodeLedgerEntityList.size(); i++) {

			barcodeLedgerEntity = barcodeLedgerEntityList.get(i);
			barImg.setAlignment(Image.ALIGN_TOP);
			//barImg.setRotationDegrees(-90);
			barImg.scalePercent(29f, 43f);
			document.add(barImg);

			code128 = new Barcode128();
			code128.setCode(barcodeLedgerEntity.getBarcode());
			Image barImg1 = code128.createImageWithBarcode(
					writer.getDirectContent(), null, null);
			//barImg1.setRotationDegrees(-90);
			barImg1.setAbsolutePosition(42f, 58f);
			barImg1.scalePercent(80f, 90f);
			document.add(barImg1);

			code128.setCode(barcodeLedgerEntity.getBarcodeSno());
			Image barImg2 = code128.createImageWithBarcode(
					writer.getDirectContent(), null, null);
			//barImg2.setRotationDegrees(-90);
			barImg2.setAbsolutePosition(42f, 18f);
			barImg2.scalePercent(80f, 90f);
			// 100
			document.add(barImg2);
			if (barcodeLedgerEntityList.size() > 1) {
				document.newPage();
			}
		}// end of loop
		document.close();

		printDoc(filePath);

	}

	/**
	 * 
	 * @deprecated
	 * @category User for Single barcode on a single file.
	 * @param barcodeLedgerDTO
	 * @throws Exception
	 */
	public void generateBarcode(BarcodeLedgerEntity barcodeLedgerDTO)
			throws Exception {

		String property = "java.io.tmpdir";
		String tempDir = System.getProperty(property);
		File directory = new File(tempDir);

		String filePath = directory.getCanonicalPath() + File.separator
				+ "ERP_BarCode_128" + System.currentTimeMillis() + ".pdf";
		System.out.println("filePath in source" + filePath);

		Document document = new Document(new Rectangle(145, 142), 0, 0, 0, 0);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(filePath));
		Barcode128 code128 = null;
		URL imagePath = this.getClass().getResource(
				File.separator + "image" + File.separator + "erp_1.PNG");
		logger.info("getClass().getResource()", imagePath);
		Image barImg = Image.getInstance(imagePath);
		document.open();
		// barImg.setAbsolutePosition(0f, 0f);
		/**
		 * create multiple barcode according to quantity.
		 */
		for (int i = 0; i < barcodeLedgerDTO.getQuantity(); i++) {
			barImg.setAlignment(Image.ALIGN_TOP);
			barImg.setRotationDegrees(90);
			//barImg.scalePercent(29f, 43f);
			barImg.scalePercent(28f, 43f);
			document.add(barImg);

			code128 = new Barcode128();
			code128.setCode(barcodeLedgerDTO.getBarcode());
			Image barImg1 = code128.createImageWithBarcode(
					writer.getDirectContent(), null, null);
			barImg1.setRotationDegrees(90);
			barImg1.setAbsolutePosition(42f, 52f);
			barImg1.scalePercent(80f, 90f);
			document.add(barImg1);

			code128.setCode(barcodeLedgerDTO.getBarcodeSno());
			Image barImg2 = code128.createImageWithBarcode(
					writer.getDirectContent(), null, null);
			barImg2.setRotationDegrees(90);
			barImg2.setAbsolutePosition(89f, 52f);
			barImg2.scalePercent(80f, 90f);
			// 100
			document.add(barImg2);
			if (barcodeLedgerDTO.getQuantity() > 1) {
				document.newPage();
			}
		}// end of loop
		document.close();

		printDoc(filePath);

	}

	public void printDoc(String filePath) {
		
		logger.info("filePath in Generate Printout filePath: "+ filePath);
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		// patts.add(OrientationRequested.LANDSCAPE);

		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor,
				null);
		if (ps.length == 0) {
			throw new IllegalStateException("No Printer found");
		}
		logger.info("Available printers: " + Arrays.asList(ps));
		logger.info("our Printer" + printerName);
		PrintService myService = null;
		for (PrintService printService : ps) {
			if (printService.getName().equals(printerName)) {
				myService = printService;
				break;
			}
		}

		if (myService == null) {
			throw new IllegalStateException("Printer not found");
		}
		logger.info("filePath in Generate Printout : "+ filePath);
		FileInputStream fis = null;
		File file = new File(filePath);
		try {
			fis = new FileInputStream(filePath);

			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE,
					null);
			DocPrintJob printJob = myService.createPrintJob();

			PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();

			 printJob.print(pdfDoc, attributes);
			Attribute[] attrs = printJob.getAttributes().toArray();
			for (int i = 0; i < attrs.length; i++) {
				String attrName = attrs[i].getName();
				String attrValue = attrs[i].toString();
				logger.info("Attribute updated : "+ attrName+ " with value: "
						+ attrValue);
			}
			fis.close();

		} catch (FileNotFoundException e) {
			logger.error("Error in printDoc",e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error in printDoc",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in printDoc",e);
		} finally {
			if (file.exists()) {
				// file.delete();
			}
		}

	}
	public static void main(String[] args) {
		
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		//patts.add(Sides.ONE_SIDED);
		// patts.add(OrientationRequested.LANDSCAPE);

		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor,
				patts);
		if (ps.length == 0) {
			throw new IllegalStateException("No Printer found");
		}
		System.out.println("Available printers: " + Arrays.asList(ps));
		//System.out.println("our Printer" + printerName);
		PrintService myService = null;
		for (PrintService printService : ps) {
			if (printService.getName().equals("")) {
				myService = printService;
				break;
			}
		}
	}

}
