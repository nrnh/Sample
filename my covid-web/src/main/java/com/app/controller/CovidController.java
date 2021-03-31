package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.repository.covid.CovidCasesRepository;
import com.app.service.covid.CovidService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private final static String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private final static String GET_COVID = "/covid/get";

	private final static String GET_COVID_DESC = "/covid/get/desc";

	private final static String ADD_COVID = "/covid/add";

	private final static String DELETE_COVID = "/covid/delete";

	private final static String GET_HELLO_API = "/covid/hello";

	private final static String GET_LOG_API = "/covid/logging";

	private final static String PUT_API = "/covid/put";
	
	private final static String POST_COVID = "/covid/post";
	
	private final static String DELETE_COVID_SOAPUI = "/covid/delete/soap";
	
	private final static String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate";
	
	@Autowired
	private CovidService covidService;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;
	
	@Autowired
	CovidCasesRepository covidCasesRepository;
	
	// ------ Get covid record ------
	@GetMapping(GET_LATEST_COVID_FROM_DB)
	String getLatest() throws Exception {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}
	
	// ------ Get covid description record ------
	@GetMapping(GET_COVID_DESC)
	List<CovidCasesDesc> findAllDesc() throws Exception {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	// ------- get covid record ------
	@GetMapping(GET_COVID)
	List<CovidCasesArea> findAll() throws Exception {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	// TODO: Practical 1 - Complete the API below
	// It should return hello when you hit http://localhost:8081/covid/hello on
	// browser
	@GetMapping(GET_HELLO_API)
	String getHello() throws Exception {
		log.info("getHello() started");

		return "Hello API";
	}

	// TODO: Practical 2 - Capture the error message below from log file
	// It should return some error when you pass a string as parameter to the HTTP
	// get
	// Example, http://localhost:8081/covid/hello?aNumberOnly=string
	@GetMapping(GET_LOG_API)
	String getLogging(@RequestParam String aNumberOnly) throws Exception {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		if (aNumberOnly != null) {
			Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + aNumberOnly;
	}

	// TODO: Practical 4 (Add)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@GetMapping(ADD_COVID)
	CovidCasesDesc addCovid(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(ADD_COVID + ", desc is null or empty");
			}
			covidCasesDesc = covidService.addCovid(desc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return covidCasesDesc;
	}

	// TODO: Practical 4 (Delete)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@DeleteMapping(DELETE_COVID)
	int deleteCovid(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovid() started id={}", id);
		
		int result = 0;
		
		result = covidService.deleteCovid(id);
		
		return result;
	}
	
	// TODO: Angular Practical 7 - Full Stack Application for Covid Put HTTP
	@PutMapping(PUT_API)
	CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);

		CovidCasesDesc covidCasesDescSaved = null;
			
		try {
			if (covidCasesDesc == null || covidCasesDesc.equals("undefined") || covidCasesDesc.equals(""))  {
				throw new NullPointerException(PUT_API + ", desc is null or empty");
			}
			covidCasesDescSaved = covidService.updateCovid(covidCasesDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		//log.info("putCovid() ends, covidCasesDescSaved={}", null);
			
		// return should be the Saved CovidCasesDesc with values
		return covidCasesDescSaved;
	}
		
	@PostMapping(POST_COVID)
	CovidCasesDesc postCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);

		CovidCasesDesc covidCasesDescSaved = null;
		
		try {
			if (covidCasesDesc == null || covidCasesDesc.equals("undefined") || covidCasesDesc.equals(""))  {
				throw new NullPointerException(POST_COVID + ", desc is null or empty");
			}
			covidCasesDescSaved = covidService.postCovid(covidCasesDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
			
		// return should be the Saved CovidCasesDesc with values
		return covidCasesDescSaved;
	}
	
	// TODO: Performance Practical 2 - Performance and Functional Testing
	@DeleteMapping(DELETE_COVID_SOAPUI)

	int deleteCovidSoap(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidSoap() started desc={}", desc);
		
		int res = 0;
		
		try {
			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(DELETE_COVID_SOAPUI + ", desc is null or empty");
			}
			res = covidService.deleteSoap(desc);
		} catch (Exception e) {
			log.error("deleteSoap() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		log.info("deleteCovidSoap() ended");
		return res;
	}
	
	// TODO: Angular Practical 11 - Remove Duplicate values
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	List<String> findDuplicateNdelete() throws Exception {
		log.info("findDuplicateNdelete() started");
			
		// complete the implementation below
		// ensure logic related to repository move to service implementation
		covidService.findDuplicateNdelete();
			
		log.info("findDuplicateNdelete() ended");
		return null;
	}
}
