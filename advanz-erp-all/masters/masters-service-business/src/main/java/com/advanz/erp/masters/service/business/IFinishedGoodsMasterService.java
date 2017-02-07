package com.advanz.erp.masters.service.business;

import java.util.List;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.FinishedGoodsDetailDTO;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterInputMessage;
import com.advanz.erp.masters.model.msg.FinishedGoodsMasterOutputMessage;

public interface IFinishedGoodsMasterService extends IAdvanzErpBaseSerivce {

	public FinishedGoodsMasterOutputMessage createFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage updateFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage deleteFinishedGoodsMaster(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage findFinishedGoodsMasterById(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage findAllFinishedGoodsMasters();

	public FinishedGoodsMasterOutputMessage search(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage getNewFinishedGoodsSeriesNo(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public List getFinishGoodInfoToEmail(String date);

	public FinishedGoodsMasterOutputMessage findFinishedGoodsForPagination(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public FinishedGoodsMasterOutputMessage findByFinishGoodByNumberAndDate(
			FinishedGoodsMasterInputMessage finishedGoodsMasterInputMessage);

	public Integer findLastFinishedGoodDetail(
			Integer finishGoodId, Integer itemId);

}
