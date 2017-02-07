package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.CountryInputMessage;
import com.advanz.erp.masters.model.msg.CountryOutMessage;

public interface ICountryService extends IAdvanzErpBaseSerivce{

	
	public CountryOutMessage createCountry(CountryInputMessage countryInputMessage);
	
	public CountryOutMessage updateCountry(CountryInputMessage countryInputMessage);
	
	public CountryOutMessage deleteCountry(CountryInputMessage countryInputMessage);
	
	public CountryOutMessage findCountryById(CountryInputMessage countryInputMessage);
	
	public CountryOutMessage findAllCountrys();
	
	//public CountryOutMessage findDupicateCountry(String countryName ,String countryCode);
	
	public CountryOutMessage checkBeforeRemove(CountryInputMessage countryInputMessage);
	
	public CountryOutMessage search(CountryInputMessage countryInputMessage);
	
	
}
