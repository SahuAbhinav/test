package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BarcodeLedgerInputMessage;
import com.advanz.erp.masters.model.msg.BarcodeLedgerOutputMessage;

public interface IBarcodeLedgerService extends IAdvanzErpBaseSerivce {

	public BarcodeLedgerOutputMessage createBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage);

	public BarcodeLedgerOutputMessage updateBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage);

	public BarcodeLedgerOutputMessage deleteBarcode(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage);

	public BarcodeLedgerOutputMessage findBarcodeById(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage);

	public BarcodeLedgerOutputMessage findAllBarcode();

	public BarcodeLedgerOutputMessage search(
			BarcodeLedgerInputMessage barcodeLedgerInputMessage);

}
