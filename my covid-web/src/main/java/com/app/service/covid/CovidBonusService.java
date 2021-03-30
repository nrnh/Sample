package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {
	
	List<CovidCasesBonus> bonus();

	CovidCasesBonus updateBonus(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postBonus(CovidCasesBonus covidCasesBonus);

	int deleteSoapBonus(String bonus);

	CovidCasesBonus addBonus(String bonus);

	int deleteBonus(long id);
}
