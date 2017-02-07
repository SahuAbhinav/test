package com.advanz.erp.masters.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupFlagEntity;
import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderMasterEntity;
import com.advanz.erp.masters.entity.jpa.TransporterEntity;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.ExciseLedgerDTO;
import com.advanz.erp.masters.model.GrnDetailDTO;
import com.advanz.erp.masters.model.GrnMasterDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PurchaseOrderMasterDTO;
import com.advanz.erp.masters.model.StockLedgerDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.msg.ExciseLedgerInputMessage;
import com.advanz.erp.masters.model.msg.ExciseLedgerOutputMessage;
import com.advanz.erp.masters.model.msg.GrnMasterInputMessage;
import com.advanz.erp.masters.model.msg.GrnMasterOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StockLedgerInputMessage;
import com.advanz.erp.masters.model.msg.StockLedgerOutMessage;
import com.advanz.erp.masters.service.business.IExciseLedgerService;
import com.advanz.erp.masters.service.business.IGrnMasterService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IStockLedgerService;
import com.advanz.erp.masters.service.business.IZoneService;
import com.advanz.erp.masters.storage.IStorageGrnMasterDAO;

public class GrnMasterServiceImpl implements IGrnMasterService {

	public static final String CREATE_GRN_MASTER_MASTER = "CreateGrnMaster";
	public static final String UPDATE_GRN_MASTER_MASTER = "UpdateGrnMaster";
	public static final String DELETE_GRN_MASTER_MASTER = "DeleteGrnMaster";
	public static final String FIND_GRN_MASTER_MASTER_BY_ID = "FindGrnMasterById";
	public static final String FIND_ALL_GRN_MASTER_MASTER = "FindAllGrnMasters";
	public static final String SEARCH_GRN_MASTER_MASTER = "SearchGrnMasters";
	public static final String NEW_GRN_MASTER_MASTER_SERIES_NO = "NewGrnMastersSeriesNo";
	public static final String FIND_GRN_BY_PO_NO = "finGrnByPoNo";
	public static final String FIND_AUTO_ID_BY_PO_NO = "findAutoIdByPoNo";
	public static final String FIND_ALL_GRN_BY_ALL_PO_NO = "findAutoIdByPoNo";
	public static final String FIND_GRN_MASTER_PAGINATION = "FindGrnMasterPagination";

	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do

	@Autowired
	public IStorageGrnMasterDAO storageGrnMasterDAO;

	public GrnMasterInputMessage grnInputMessage;

	public GrnMasterOutputMessage grnOutputMessage;

	@Autowired
	public IStockLedgerService stockLedgerService;
	@Autowired
	public IItemService itemService;

	@Autowired
	public IZoneService zoneService;

	@Autowired
	public IExciseLedgerService exciseLedgerService;

	@Override
	public GrnMasterOutputMessage createGrnMaster(
			GrnMasterInputMessage grnInputMessage) {

		flowId = CREATE_GRN_MASTER_MASTER;
		// assign the message to the class level variable.
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;
	}

	@Override
	public GrnMasterOutputMessage updateGrnMaster(
			GrnMasterInputMessage grnInputMessage) {

		flowId = UPDATE_GRN_MASTER_MASTER;
		// assign the message to the class level variable.
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return grnOutputMessage;
	}

	@Override
	public GrnMasterOutputMessage deleteGrnMaster(
			GrnMasterInputMessage grnInputMessage) {
		flowId = DELETE_GRN_MASTER_MASTER;
		// assign the message to the class level variable.
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return grnOutputMessage;

	}

	@Override
	public GrnMasterOutputMessage findGrnMasterById(
			GrnMasterInputMessage grnInputMessage) {
		flowId = FIND_GRN_MASTER_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;

	}

	@Override
	public GrnMasterOutputMessage findAllGrnsMaster() {
		flowId = FIND_ALL_GRN_MASTER_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;
	}

	@Override
	public GrnMasterOutputMessage search(GrnMasterInputMessage grnInputMessage) {
		flowId = SEARCH_GRN_MASTER_MASTER;
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;

	}

	@Override
	public GrnMasterOutputMessage findGrnByPoNo(
			GrnMasterInputMessage grnMasterInputMessage) {
		flowId = FIND_GRN_BY_PO_NO;
		this.grnInputMessage = grnMasterInputMessage;
		advanzErpServiceTemplate.execute(this);

		return grnOutputMessage;

	}

	@Override
	public GrnMasterOutputMessage getNewGrnMasterSeriesNo(
			GrnMasterInputMessage grnInputMessage) {
		flowId = NEW_GRN_MASTER_MASTER_SERIES_NO;
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;
	}

	@Override
	public GrnMasterOutputMessage findAllGrnForAllPoNo() {
		flowId = FIND_ALL_GRN_BY_ALL_PO_NO;
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;
	}

	@Override
	public GrnMasterOutputMessage findGrnMasterForPagination(
			GrnMasterInputMessage grnInputMessage) {
		flowId = FIND_GRN_MASTER_PAGINATION;
		this.grnInputMessage = grnInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return grnOutputMessage;
	}

	@Override
	public boolean validateInput() {

		if (CREATE_GRN_MASTER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_GRN_MASTER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_GRN_MASTER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_GRN_MASTER_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_GRN_MASTER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_GRN_MASTER_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (NEW_GRN_MASTER_MASTER_SERIES_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_GRN_BY_PO_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_AUTO_ID_BY_PO_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_GRN_BY_ALL_PO_NO.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_GRN_MASTER_PAGINATION.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

		GrnMasterEntity grnMasterEntity = new GrnMasterEntity();
		grnOutputMessage = new GrnMasterOutputMessage();

		GrnMasterDTO grnMasterDTO = null;
		List<GrnDetailDTO> grnDetailDTO = null;
		if (grnInputMessage != null) {
			grnMasterDTO = grnInputMessage.getGrnMasterDTO();
			if (grnMasterDTO != null) {
				BeanUtils.copyProperties(grnMasterDTO, grnMasterEntity);
				PartyDTO partyDTO = grnMasterDTO.getPartyDTO();
				if (partyDTO != null) {
					PartyEntity partyEntity = new PartyEntity();
					copyObject(partyDTO, partyEntity);
					grnMasterEntity.setPartyEntity(partyEntity);
				}

				BranchDTO branchDTO = grnMasterDTO.getBranchDTO();
				if (branchDTO != null) {
					BranchEntity branchEntity = new BranchEntity();
					copyObject(branchDTO, branchEntity);
					grnMasterEntity.setBranchEntity(branchEntity);
				}

				PurchaseOrderMasterDTO purchaseOrderMasterDTO = grnMasterDTO
						.getPurchaseOrderDTO();
				if (purchaseOrderMasterDTO != null
						&& purchaseOrderMasterDTO.getPurchaseOrderNumber() != null) {
					PurchaseOrderMasterEntity purchaseOrderMasterEntity = new PurchaseOrderMasterEntity();
					copyObject(purchaseOrderMasterDTO,
							purchaseOrderMasterEntity);
					grnMasterEntity
							.setPurchaseOrderEntity(purchaseOrderMasterEntity);
				}

				ItemGroupFlagDTO itemGroupFlagDTO = grnMasterDTO
						.getItemGroupFlagDTO();
				if (itemGroupFlagDTO != null) {
					ItemGroupFlagEntity itemGroupFlagEntity = new ItemGroupFlagEntity();
					copyObject(itemGroupFlagDTO, itemGroupFlagEntity);
					itemGroupFlagEntity.setItemGroupFlagId(itemGroupFlagDTO
							.getItemGroupFlagId());
					grnMasterEntity.setItemGroupFlagEntity(itemGroupFlagEntity);
				}

				TransporterDTO transporterDTO = grnMasterDTO.getTransportDTO();
				if (transporterDTO != null) {
					TransporterEntity transporterEntity = new TransporterEntity();
					copyObject(transporterDTO, transporterEntity);
					grnMasterEntity.setTransportEnttity(transporterEntity);
				}

				grnDetailDTO = grnMasterDTO.getGrnDetailDTOList();
				if (grnDetailDTO != null && grnDetailDTO.size() > 0) {
					List<GrnDetailEntity> grnDetailEntity = convertGrnDetailDtoTOEntity(
							grnDetailDTO, grnMasterDTO.getGrnNumber(),
							grnMasterDTO.getTransactionSeries(), grnMasterDTO);
					grnMasterEntity.setGrnDetailEntity(grnDetailEntity);
				}
			}
		}

		if (CREATE_GRN_MASTER_MASTER.equals(flowId)) {
			// if (grnMasterEntity.getGrnDetailEntity() != null &&
			// grnMasterEntity.getGrnDetailEntity().size() > 0) {
			List<GrnMasterEntity> list = storageGrnMasterDAO
					.findByGrnNumber(grnMasterEntity.getGrnNumber());
			if (list != null && list.size() > 0) {
				ErrorDTO errorDTO = new ErrorDTO(
						"1",
						grnMasterEntity.getGrnNumber()
								+ " Grn Number is already exist,it can't be duplicate ");
				ErrorListDTO errorListDTO = new ErrorListDTO();
				errorListDTO.addError(errorDTO);
				grnOutputMessage.setErrorListDTO(errorListDTO);
			} else {

				if (grnDetailDTO != null && grnDetailDTO.size() > 0) {
					grnOutputMessage.setErrorListDTO(null);
					storageGrnMasterDAO.create(grnMasterEntity);

					for (int i = 0; i < grnDetailDTO.size(); i++) {
						GrnDetailDTO grnDetail = (GrnDetailDTO) grnDetailDTO
								.get(i);

						StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
						ItemInputMessage itemInputMessage = new ItemInputMessage();
						ItemDTO itemDTO = new ItemDTO();
						itemDTO.setItemId(grnDetail.getItemDTO().getItemId());
						itemInputMessage.setItemDTO(itemDTO);
						ItemOutMessage itemOutMessage = itemService
								.findItemById(itemInputMessage);
						ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage
								.getItemDTOList();
						if (itemList != null && itemList.size() > 0) {
							itemDTO = itemList.get(0);
						}
						stockLedgerDTO.setSalesRate(itemDTO.getPurchaseRate());
						try {
							stockLedgerDTO.setSalesValue((itemDTO
									.getPurchaseRate() * grnDetail
									.getReceivedQty()));
						} catch (Exception e) {
						}
						stockLedgerDTO.setTransactionNumber(grnMasterDTO
								.getGrnNumber());
						stockLedgerDTO.setTransactionDate(grnMasterDTO
								.getGrnDate());

						stockLedgerDTO.setTransactionSeries(grnMasterDTO
								.getTransactionSeries());
						stockLedgerDTO
								.setTransactionId(grnMasterDTO.getGrnId());
						stockLedgerDTO.setItemId(grnDetail.getItemDTO()
								.getItemId());
						stockLedgerDTO.setBranchId(grnMasterDTO.getBranchDTO()
								.getBranchId());

						if (grnDetail.getReceivedQty() != null) {
							stockLedgerDTO.setQuantity(grnDetail
									.getReceivedQty());
						} else {
							stockLedgerDTO.setQuantity(0.0);
						}
						stockLedgerDTO.setApprovedQuantity(grnDetail
								.getApprovedQty());
						if (grnMasterDTO.getAproved() != null
								&& grnMasterDTO.getAproved() > 0) {
							stockLedgerDTO.setApprovedDate(new Date());
						}
						
						//set grn rate into stock ledger's sales rate
						stockLedgerDTO.setSalesRate(grnDetail
								.getPurchaseRate());

						StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
						stockLedgerInputMessage
								.setStockLedgerDTO(stockLedgerDTO);

						ExciseLedgerDTO exciseLedgerDTO = new ExciseLedgerDTO();

						ItemInputMessage itemInputMessage2 = new ItemInputMessage();
						ItemDTO itemDTO2 = new ItemDTO();
						itemDTO2.setItemId(grnDetail.getItemDTO().getItemId());
						itemInputMessage2.setItemDTO(itemDTO2);
						ItemOutMessage itemOutMessage2 = itemService
								.findItemById(itemInputMessage2);
						ArrayList<ItemDTO> itemList2 = (ArrayList<ItemDTO>) itemOutMessage2
								.getItemDTOList();
						if (itemList2 != null && itemList2.size() > 0) {
							itemDTO2 = itemList2.get(0);
						}

						exciseLedgerDTO.setGrnNumber(grnMasterDTO
								.getGrnNumber());
						exciseLedgerDTO.setTransactionDate(grnMasterDTO
								.getGrnDate());

						exciseLedgerDTO.setTransactionSeries(grnMasterDTO
								.getTransactionSeries());

						// exciseLedgerDTO.setTransactionId(grnMasterDTO.getGrnId());
						exciseLedgerDTO.setApprovedDate(new Date());
						exciseLedgerDTO.setItemId(grnDetail.getItemDTO()
								.getItemId());
						exciseLedgerDTO.setBranchId(grnMasterDTO.getBranchDTO()
								.getBranchId());
						exciseLedgerDTO.setPartyId(grnMasterDTO.getPartyDTO()
								.getPartyId());
						exciseLedgerDTO.setCessAmount(grnDetail
								.getItemEducationCessAmount());
						exciseLedgerDTO.sethEduCessAmount(grnDetail
								.getItemHighEducationCessAmount());
						exciseLedgerDTO.setExciseAmount(grnDetail
								.getItemBillExciseAmt());
						exciseLedgerDTO.setNarration(grnDetail
								.getContainerDescription());
						exciseLedgerDTO.setReceivedBillExciseAmount(grnDetail
								.getReceivedBillExciseAmt());
						exciseLedgerDTO
								.setReceivedEducationCessAmount(grnDetail
										.getReceivedEducationCessAmount());
						exciseLedgerDTO
								.setReceivedHighEducationCessAmount(grnDetail
										.getReceivedHighEducationCessAmount());
						if (grnDetail.getReceivedQty() != null) {
							exciseLedgerDTO.setGrnQty(grnDetail
									.getReceivedQty());
						} else {
							exciseLedgerDTO.setGrnQty(0.0);
						}
						ExciseLedgerInputMessage exciseLedgerInputMessage = new ExciseLedgerInputMessage();
						exciseLedgerInputMessage
								.setExciseLedgerDTO(exciseLedgerDTO);
						if (grnDetail.getGrnApprovalFlag() != null
								&& grnDetail.getGrnApprovalFlag() > 0) {
							exciseLedgerService
									.createExciseLedger(exciseLedgerInputMessage);
						}
						stockLedgerService
								.createStockLedger(stockLedgerInputMessage);
					}

				}
				// grnOutputMessage.setErrorListDTO(null);
				// storageGrnMasterDAO.create(grnMasterEntity);
			}
		} else if (UPDATE_GRN_MASTER_MASTER.equals(flowId)) {

			List<GrnMasterEntity> list = storageGrnMasterDAO
					.findByGrnNumber(grnMasterEntity.getGrnNumber());

			if (grnDetailDTO != null && grnDetailDTO.size() > 0) {

				// STOCK DELETE
				// StockLedgerInputMessage stockLedgerInputMessage =new
				// StockLedgerInputMessage();
				StockLedgerInputMessage sn1 = new StockLedgerInputMessage();
				StockLedgerDTO sd = new StockLedgerDTO();
				sd.setTransactionNumber(grnMasterDTO.getGrnNumber());
				sn1.setStockLedgerDTO(sd);
				StockLedgerOutMessage ledgerOutMessage = stockLedgerService
						.findStockLedgerByTransactionId(sn1);
				List<StockLedgerDTO> ledgerDTOList = ledgerOutMessage
						.getStockLedgerDTOList();
				for (int c = 0; c < ledgerDTOList.size(); c++) {
					sd = new StockLedgerDTO();
					StockLedgerInputMessage sn = new StockLedgerInputMessage();
					sd = ledgerDTOList.get(c);
					sn.setStockLedgerDTO(sd);
					// stockLedgerService.updateStockLedger(stockLedgerInputMessage);
					stockLedgerService.deleteStockLedger(sn);
				}
				// STOCK DELETE END

				// DELETE EXSICE
				ExciseLedgerInputMessage exInput = new ExciseLedgerInputMessage();
				ExciseLedgerDTO exDTO = new ExciseLedgerDTO();
				exDTO.setGrnNumber(grnMasterDTO.getGrnNumber());
				exInput.setExciseLedgerDTO(exDTO);
				ExciseLedgerOutputMessage exOut = exciseLedgerService
						.removeExciseLedgerByGrn(exInput);
				/*
				 * List<ExciseLedgerDTO> exList= exOut.getExciseLedgerDTOList();
				 * for(int j=0;j<exList.size();j++){ exDTO= new
				 * ExciseLedgerDTO(); exDTO= exList.get(j); exInput = new
				 * ExciseLedgerInputMessage();
				 * exInput.setExciseLedgerDTO(exDTO);
				 * 
				 * }
				 */
				// DELETE EXSICE END

				for (int i = 0; i < grnDetailDTO.size(); i++) {
					GrnDetailDTO grnDetail = (GrnDetailDTO) grnDetailDTO.get(i);
					StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();

					ItemInputMessage itemInputMessage = new ItemInputMessage();
					ItemDTO itemDTO = new ItemDTO();
					itemDTO.setItemId(grnDetail.getItemDTO().getItemId());
					itemInputMessage.setItemDTO(itemDTO);
					ItemOutMessage itemOutMessage = itemService
							.findItemById(itemInputMessage);
					ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) itemOutMessage
							.getItemDTOList();
					if (itemList != null && itemList.size() > 0) {
						itemDTO = itemList.get(0);
					}
					stockLedgerDTO.setSalesRate(itemDTO.getPurchaseRate());
					try {
						stockLedgerDTO
								.setSalesValue((itemDTO.getPurchaseRate() * grnDetail
										.getReceivedQty()));
					} catch (Exception e) {
					}
					stockLedgerDTO.setTransactionNumber(grnMasterDTO
							.getGrnNumber());
					stockLedgerDTO
							.setTransactionDate(grnMasterDTO.getGrnDate());
					stockLedgerDTO.setTransactionSeries(grnMasterDTO
							.getTransactionSeries());
					stockLedgerDTO.setTransactionId(grnMasterDTO.getGrnId());
					stockLedgerDTO
							.setItemId(grnDetail.getItemDTO().getItemId());
					stockLedgerDTO.setBranchId(grnMasterDTO.getBranchDTO()
							.getBranchId());
					if (grnDetail.getReceivedQty() != null) {
						stockLedgerDTO.setQuantity(grnDetail.getReceivedQty());
					} else {
						stockLedgerDTO.setQuantity(0.0);
					}
					stockLedgerDTO.setApprovedQuantity(grnDetail
							.getApprovedQty());
					if (grnMasterDTO.getAproved() != null
							&& grnMasterDTO.getAproved() > 0) {
						stockLedgerDTO.setApprovedDate(new Date());
					}

					StockLedgerInputMessage stockLedgerInputMessage = new StockLedgerInputMessage();
					stockLedgerInputMessage.setStockLedgerDTO(stockLedgerDTO);
					stockLedgerService
							.createStockLedger(stockLedgerInputMessage);
					ExciseLedgerDTO exciseLedgerDTO = new ExciseLedgerDTO();

					ItemInputMessage itemInputMessage2 = new ItemInputMessage();
					ItemDTO itemDTO2 = new ItemDTO();
					itemDTO2.setItemId(grnDetail.getItemDTO().getItemId());
					itemInputMessage2.setItemDTO(itemDTO2);
					ItemOutMessage itemOutMessage2 = itemService
							.findItemById(itemInputMessage2);
					ArrayList<ItemDTO> itemList2 = (ArrayList<ItemDTO>) itemOutMessage2
							.getItemDTOList();
					if (itemList2 != null && itemList2.size() > 0) {
						itemDTO2 = itemList2.get(0);
					}

					exciseLedgerDTO.setGrnNumber(grnMasterDTO.getGrnNumber());
					exciseLedgerDTO.setTransactionDate(grnMasterDTO
							.getGrnDate());

					exciseLedgerDTO.setTransactionSeries(grnMasterDTO
							.getTransactionSeries());

					// exciseLedgerDTO.setTransactionId(grnMasterDTO.getGrnId());
					exciseLedgerDTO.setApprovedDate(new Date());
					exciseLedgerDTO.setItemId(grnDetail.getItemDTO()
							.getItemId());
					exciseLedgerDTO.setBranchId(grnMasterDTO.getBranchDTO()
							.getBranchId());
					exciseLedgerDTO.setPartyId(grnMasterDTO.getPartyDTO()
							.getPartyId());
					exciseLedgerDTO.setCessAmount(grnDetail
							.getItemEducationCessAmount());
					exciseLedgerDTO.sethEduCessAmount(grnDetail
							.getItemHighEducationCessAmount());
					exciseLedgerDTO.setExciseAmount(grnDetail
							.getItemBillExciseAmt());
					exciseLedgerDTO.setNarration(grnDetail
							.getContainerDescription());
					exciseLedgerDTO.setReceivedBillExciseAmount(grnDetail
							.getReceivedBillExciseAmt());
					exciseLedgerDTO.setReceivedEducationCessAmount(grnDetail
							.getReceivedEducationCessAmount());
					exciseLedgerDTO
							.setReceivedHighEducationCessAmount(grnDetail
									.getReceivedHighEducationCessAmount());
					if (grnDetail.getReceivedQty() != null) {
						exciseLedgerDTO.setGrnQty(grnDetail.getReceivedQty());
					} else {
						exciseLedgerDTO.setGrnQty(0.0);
					}
					ExciseLedgerInputMessage exciseLedgerInputMessage = new ExciseLedgerInputMessage();
					exciseLedgerInputMessage
							.setExciseLedgerDTO(exciseLedgerDTO);

					if (grnDetail.getGrnApprovalFlag() != null
							&& grnDetail.getGrnApprovalFlag() > 0) {
						exciseLedgerService
								.createExciseLedger(exciseLedgerInputMessage);
					}
				}
			}
			storageGrnMasterDAO.update(grnMasterEntity);

		} else if (DELETE_GRN_MASTER_MASTER.equals(flowId)) {
			List<GrnMasterEntity> grnMasterEntityList = storageGrnMasterDAO
					.findForGrnNumber(grnMasterEntity.getGrnAutoId());

			ExciseLedgerInputMessage exciseLedgerInputMessage = new ExciseLedgerInputMessage();
			ExciseLedgerDTO exciseLedgerDTO = new ExciseLedgerDTO();
			exciseLedgerDTO.setGrnNumber(grnMasterEntityList.get(0)
					.getGrnNumber());
			exciseLedgerInputMessage.setExciseLedgerDTO(exciseLedgerDTO);
			exciseLedgerService
					.removeExciseLedgerByGrn(exciseLedgerInputMessage);

			StockLedgerInputMessage stockInputmessage = new StockLedgerInputMessage();
			StockLedgerDTO stockLedgerDTO = new StockLedgerDTO();
			stockLedgerDTO.setTransactionNumber(grnMasterEntityList.get(0)
					.getGrnNumber());
			stockInputmessage.setStockLedgerDTO(stockLedgerDTO);

			// stockLedgerService.deleteByTransactionNumber(stockInputmessage);

			StockLedgerOutMessage stockLedgerOutMessage = stockLedgerService
					.findStockLedgerByTransactionId(stockInputmessage);
			ArrayList<StockLedgerDTO> stockLedgerList = (ArrayList<StockLedgerDTO>) stockLedgerOutMessage
					.getStockLedgerDTOList();
			for (int i = 0; i < stockLedgerList.size(); i++) {
				stockLedgerDTO = stockLedgerList.get(i);
				stockInputmessage.setStockLedgerDTO(stockLedgerDTO);
				stockLedgerService.deleteStockLedger(stockInputmessage);
			}

			storageGrnMasterDAO.delete(grnMasterEntity);
		} else if (FIND_GRN_MASTER_MASTER_BY_ID.equals(flowId)) {
			List<GrnMasterEntity> list = storageGrnMasterDAO
					.findById(grnMasterEntity.getGrnAutoId());
			popUpList(list);
		} else if (FIND_ALL_GRN_MASTER_MASTER.equals(flowId)) {
			List<GrnMasterEntity> list = storageGrnMasterDAO.load();
			popUpList(list);
		} else if (FIND_GRN_BY_PO_NO.equals(flowId)) {
			List<GrnMasterEntity> list = storageGrnMasterDAO
					.findGrnByPoId(grnInputMessage.getGrnMasterDTO()
							.getPurchaseOrderDTO().getPoAutoId());
			popUpList(list);
		}

		else if (FIND_ALL_GRN_BY_ALL_PO_NO.equals(flowId)) {
			List<GrnMasterEntity> list = storageGrnMasterDAO.findAllPoNo();
			popUpList(list);
		}

		else if (SEARCH_GRN_MASTER_MASTER.equals(flowId)) {
			String date = null;
			if (grnMasterEntity.getGrnDate() != null) {
				SimpleDateFormat obj = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				date = obj.format(grnMasterEntity.getGrnDate());
			}
			grnMasterDTO = grnInputMessage.getGrnMasterDTO();
			String itemName = null;
			if (grnMasterDTO.getItemName() != null) {
				itemName = grnMasterDTO.getItemName();
			}

			Date fromDate = null;
			Date toDate = null;
			if (grnMasterDTO.getFromDate() != null) {
				fromDate = grnMasterDTO.getFromDate();

			}
			if (grnMasterDTO.getToDate() != null) {
				toDate = grnMasterDTO.getToDate();

			}
			String supplierBillNo = null;
			if (grnMasterDTO.getSupplierBillNo() != null) {
				supplierBillNo = grnMasterDTO.getSupplierBillNo();
			}

			List<GrnMasterEntity> list = storageGrnMasterDAO.search(
					grnMasterEntity.getGrnNumber(), fromDate, toDate,
					grnMasterEntity.getPartyEntity().getPartyName(), itemName,
					supplierBillNo);
			popUpList(list);
		} else if (NEW_GRN_MASTER_MASTER_SERIES_NO.equals(flowId)) {

			Integer seriesNo = 0;
			Timestamp date = zoneService.getFirstDayOfFinYear();
			List list = storageGrnMasterDAO.getNewSeriesNo(grnMasterEntity
					.getFinYear());
			if (list != null && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);

				Number n = (Number) obj[0];
				if (n != null)
					seriesNo = n.intValue();
				if (obj[1] != null && obj[1] != "")
					date = (Timestamp) obj[1];
			}
			seriesNo++;

			grnOutputMessage = new GrnMasterOutputMessage();
			grnOutputMessage.setGrnSeriesNo(seriesNo);
			grnOutputMessage.setGrnSeriesDate(date);
		} else if (FIND_GRN_MASTER_PAGINATION.equals(flowId)) {
			List<GrnMasterEntity> list = storageGrnMasterDAO
					.FindGrnMasterPagination(grnInputMessage.getNext());
			popUpList(list);
		}

	}

	void popUpList(List<GrnMasterEntity> list) {
		grnOutputMessage = new GrnMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<GrnMasterDTO> resultList = new ArrayList<GrnMasterDTO>();
			GrnMasterDTO grnMasterDTO;
			for (GrnMasterEntity grnMasterEntity : list) {
				grnMasterDTO = new GrnMasterDTO();
				// Spring

				if (grnMasterEntity != null) {
					BeanUtils.copyProperties(grnMasterEntity, grnMasterDTO);
					PartyEntity partyEntity = grnMasterEntity.getPartyEntity();
					if (partyEntity != null) {
						PartyDTO partyDTO = new PartyDTO();
						copyObject(partyEntity, partyDTO);
						grnMasterDTO.setPartyDTO(partyDTO);
					}

					PurchaseOrderMasterEntity purchaseOrderMasterEntity = grnMasterEntity
							.getPurchaseOrderEntity();
					if (purchaseOrderMasterEntity != null) {
						PurchaseOrderMasterDTO purchaseOrderMaster = new PurchaseOrderMasterDTO();
						copyObject(purchaseOrderMasterEntity,
								purchaseOrderMaster);
						grnMasterDTO.setPurchaseOrderDTO(purchaseOrderMaster);
					}

					BranchEntity branchEntity = grnMasterEntity
							.getBranchEntity();
					if (branchEntity != null) {
						BranchDTO branchDTO = new BranchDTO();
						copyObject(branchEntity, branchDTO);
						grnMasterDTO.setBranchDTO(branchDTO);
					}

					ItemGroupFlagEntity itemGroupFlagEntity = grnMasterEntity
							.getItemGroupFlagEntity();
					if (itemGroupFlagEntity != null) {
						ItemGroupFlagDTO itemGroupFlagDTO = new ItemGroupFlagDTO();
						copyObject(itemGroupFlagEntity, itemGroupFlagDTO);
						grnMasterDTO.setItemGroupFlagDTO(itemGroupFlagDTO);
					}

					TransporterEntity transporterEntity = grnMasterEntity
							.getTransportEnttity();
					if (transporterEntity != null) {
						TransporterDTO transporterDTO = new TransporterDTO();
						copyObject(transporterEntity, transporterDTO);
						grnMasterDTO.setTransportDTO(transporterDTO);
					}

					List<GrnDetailEntity> grnDetailEntity = grnMasterEntity
							.getGrnDetailEntity();
					if (grnDetailEntity != null && grnDetailEntity.size() > 0) {
						List<GrnDetailDTO> grnDetailDTO = convertGrnDetailEntityToDto(grnDetailEntity);
						grnMasterDTO.setGrnDetailDTOList(grnDetailDTO);
						grnMasterDTO.setCount(grnDetailEntity.size());
					}
				}

				resultList.add(grnMasterDTO);
			}
			grnOutputMessage.setGrnMasterDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (CREATE_GRN_MASTER_MASTER.equals(flowId)) {

		} else if (UPDATE_GRN_MASTER_MASTER.equals(flowId)) {

		} else if (DELETE_GRN_MASTER_MASTER.equals(flowId)) {

		} else if (FIND_GRN_MASTER_MASTER_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_GRN_MASTER_MASTER.equals(flowId)) {

		} else if (SEARCH_GRN_MASTER_MASTER.equals(flowId)) {

		} else if (NEW_GRN_MASTER_MASTER_SERIES_NO.equals(flowId)) {

		}

	}

	private List<GrnDetailEntity> convertGrnDetailDtoTOEntity(
			List<GrnDetailDTO> dtoList, String grnNumber,
			String transactionSeries, GrnMasterDTO grnMasterDTO) {
		List<GrnDetailEntity> entityList = new ArrayList<GrnDetailEntity>();
		for (GrnDetailDTO dto : dtoList) {
			GrnDetailEntity entity = new GrnDetailEntity();

			ItemDTO item = dto.getItemDTO();
			if (item != null) {
				ItemEntity itemEntity = new ItemEntity();
				copyObject(item, itemEntity);

				entity.setItemEntity(itemEntity);
			}
			copyObject(dto, entity);
			entity.setTransactionSeries(transactionSeries);
			entity.setGrnNumber(grnNumber);
			if (grnMasterDTO.getCreatedUserId() != null) {
				entity.setCreatedUserId(grnMasterDTO.getCreatedUserId());
			}
			if (grnMasterDTO.getModifiedUserId() != null) {
				entity.setModifiedUserId(grnMasterDTO.getModifiedUserId());
			}
			entityList.add(entity);
		}
		return entityList;

	}

	private List<GrnDetailDTO> convertGrnDetailEntityToDto(
			List<GrnDetailEntity> entityList) {
		List<GrnDetailDTO> dtoList = new ArrayList<GrnDetailDTO>();
		for (GrnDetailEntity entity : entityList) {
			GrnDetailDTO dto = new GrnDetailDTO();

			ItemEntity itemEntity = entity.getItemEntity();
			if (itemEntity != null) {
				ItemDTO itemDTO = new ItemDTO();
				copyObject(itemEntity, dto);
				copyObject(itemEntity, itemDTO);
				dto.setItemDTO(itemDTO);
				if (itemEntity.getMasterUnitEntity() != null) {
					dto.setMeasurementUnitId(itemEntity.getMasterUnitEntity()
							.getMastersId());
					dto.setMeasurementUnitName(itemEntity.getMasterUnitEntity()
							.getName());

				}
			}

			copyObject(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;

	}

	private void copyObject(Object source, Object target) {
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Date getMaxDate() {
		return storageGrnMasterDAO.getMaxDate();
	}

	@Override
	public List getEmailAlertData(String date) {
		List list = storageGrnMasterDAO.getEmailAlertData(date);
		return list;
	}

	@Override
	public Double getPuchaseRateByIssueItemId(Integer itemId) {
		return storageGrnMasterDAO.getPuchaseRateByIssueItemId(itemId);
	}

	@Override
	public Double getLastGrnRateForWeightedAvg(Date date, Integer itemId) {
		Double d = 0.0;
		List list = storageGrnMasterDAO.getLastGrnRateForWeightedAvg(date,
				itemId);
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				d = (Double) list.get(0);
			}
		}
		return d;
	}

}
