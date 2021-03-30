package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc);

	int deleteCovid(long id) throws Exception;

	CovidCasesDesc updateCovid(CovidCasesDesc covidDescCase);

	CovidCasesDesc postCovid(CovidCasesDesc covidDescCase);

	int deleteSoap(String desc) throws Exception;

}
