package com.app.mining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mining.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
@RestController
@Slf4j
public class MyCovidMiningController {

	private final static String MINING_MY_COVID = "/covid/mining/my";

	// TODO: Practical 5, move the required logic from covid-web project to here
	
	// CovidMiningApiTotalCasesImpl need to be fixed too. Refer to the file TODO remarks
	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;
	
	@GetMapping(MINING_MY_COVID)
	String mining() throws Exception {
		log.info("mining() started");
		String strReturn = null;
		try {
			covidMiningAPITotalCases.doMining();
			strReturn = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			log.error("mining() exception" + e.getMessage());
			throw new Exception(e);
		}
		log.info("return value = {}" + strReturn);
		return strReturn;
	}
	
}
