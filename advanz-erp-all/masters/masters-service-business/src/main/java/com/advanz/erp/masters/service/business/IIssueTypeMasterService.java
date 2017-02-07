package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.IssueTypeMasterInputMessage;
import com.advanz.erp.masters.model.msg.IssueTypeMasterOutputMessage;

public interface IIssueTypeMasterService extends IAdvanzErpBaseSerivce{	
public IssueTypeMasterOutputMessage findAllIssueTypeMasters();

public IssueTypeMasterOutputMessage findIssuesBySno(IssueTypeMasterInputMessage typeMasterInputMessage);
}
