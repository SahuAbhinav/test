package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.MelterLogBookInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogBookOutputMessage;

public interface IMelterLogBookService extends IAdvanzErpBaseSerivce 
{
  public MelterLogBookOutputMessage findAllMelterLogBook();
  public MelterLogBookOutputMessage findForSearchRecord(MelterLogBookInputMessage melterLogBookInputMessage);
  public MelterLogBookOutputMessage createMelterLogBook(MelterLogBookInputMessage melterLogBookInputMessage);
  public MelterLogBookOutputMessage findMelterById(MelterLogBookInputMessage melterLogBookInputMessage);
  public MelterLogBookOutputMessage deleteMelterRecord(MelterLogBookInputMessage melterLogBookInputMessage);
  public MelterLogBookOutputMessage updateMelterRecord(MelterLogBookInputMessage melterLogBookInputMessage);
  public MelterLogBookOutputMessage getLastRecordDate(MelterLogBookInputMessage melterLogBookInputMessage);
}
