package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.AnnealingOvenInputMessage;
import com.advanz.erp.masters.model.msg.AnnealingOvenOutputMessage;

public interface IAnnealingOvenService extends IAdvanzErpBaseSerivce {

public AnnealingOvenOutputMessage createAnnealingOven(
AnnealingOvenInputMessage annealingOvenInputMessage);

public AnnealingOvenOutputMessage updateAnnealingOven(
AnnealingOvenInputMessage annealingOvenInputMessage);

public AnnealingOvenOutputMessage deleteAnnealingOven(
AnnealingOvenInputMessage annealingOvenInputMessage);

public AnnealingOvenOutputMessage findAnnealingOvenById(
AnnealingOvenInputMessage annealingOvenInputMessage);

public AnnealingOvenOutputMessage findAllAnnealingOven();



	public AnnealingOvenOutputMessage findAnnealingOven(
			AnnealingOvenInputMessage annealingOvenInputMessage);
public AnnealingOvenOutputMessage checktDuplicateEntry(
AnnealingOvenInputMessage annealingOvenInputMessage);

public AnnealingOvenOutputMessage lastAnnealingOvenDate(AnnealingOvenInputMessage annealingOvenInputMessage);

}

