package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;

public interface ICityService extends IAdvanzErpBaseSerivce{

	
	public CityOutputMessage createCity(CityInputMessage cityInputMessage);
	
	public CityOutputMessage updateCity(CityInputMessage cityInputMessage);
	
	public CityOutputMessage deleteCity(CityInputMessage cityInputMessage);
	
	public CityOutputMessage findCityById(CityInputMessage cityInputMessage);
	
	public CityOutputMessage findAllCityes();
	
	public CityOutputMessage search(CityInputMessage cityInputMessage);
	
	public CityOutputMessage findCityStateCountryByCityId(CityInputMessage cityInputMessage);
	
	public CityOutputMessage checkBeforeRemove(CityInputMessage cityInputMessage);
	
}
