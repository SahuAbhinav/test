package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.MelterTrollyLogInputMessage;
import com.advanz.erp.masters.model.msg.MelterTrollyLogOutputMessage;

public interface IMelterTrollyLogService extends IAdvanzErpBaseSerivce {
	public MelterTrollyLogOutputMessage findAllMelterTrolly();
	public MelterTrollyLogOutputMessage SearchByLogDateOrTrollyNo(MelterTrollyLogInputMessage melterTrollyLogInputMessage);
    public MelterTrollyLogOutputMessage saveMelterTrollyLog(MelterTrollyLogInputMessage melterTrollyLogInputMessage);
    public MelterTrollyLogOutputMessage updateMelterTrollyLog(MelterTrollyLogInputMessage melterTrollyLogInputMessage);
    public MelterTrollyLogOutputMessage findMelterTrollyById(MelterTrollyLogInputMessage melterTrollyLogInputMessage);
    public MelterTrollyLogOutputMessage deleteMelterTrolly(MelterTrollyLogInputMessage melterTrollyLogInputMessage);
}
