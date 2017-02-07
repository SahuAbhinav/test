package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.MelterLogSummaryInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogSummaryOutputMessage;

public interface IMelterLogSummaryService extends IAdvanzErpBaseSerivce
 {
  public MelterLogSummaryOutputMessage findAllMelterSummary();
  public MelterLogSummaryOutputMessage addNewMelterLogSummary(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  public MelterLogSummaryOutputMessage deleteMelterLogSummary(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  public MelterLogSummaryOutputMessage updateMelterLogSummary(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  public MelterLogSummaryOutputMessage findById(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  public MelterLogSummaryOutputMessage searchMelterSummaryByDate(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  public MelterLogSummaryOutputMessage getLastMelterLogSummaryDate(MelterLogSummaryInputMessage melterLogSummaryInputMessage);
  
 
 }
