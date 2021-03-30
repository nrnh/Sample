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

import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {
	
	private final static String GET_MY_BONUS = "/covid/get/bonus";

	private final static String ADD_BONUS = "/covid/add/bonus";
	
	private final static String DELETE_BONUS = "/covid/delete/bonus";
	
	private final static String PUT_BONUS = "/covid/put/bonus";
	
	private final static String POST_BONUS = "/covid/post/bonus";
	
	private final static String DELETE_SOAPUI_BONUS = "/covid/delete/bonus/soap";
	
	@Autowired
	CovidBonusService covidBonusService;

	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	// TODO: Practical Bonus Desc Final
	// Objective: to create a set of spring and hibernate services to retrieve data from a new table call "trx_covid_cases_bonus"
	
	// 1. Complete the CovidCasesBonusEntity.java and auto generate a table on DB
	// Enable the line below from application.properties to create new bonus table
	// # spring.jpa.hibernate.ddl-auto=update
	// Then restart application and table being created on the log
	// CREATE TABLE / PRIMARY KEY will create implicit index "trx_covid_cases_bonus_pkey" for table "trx_covid_cases_bonus"
	
	// 2. Insert the dummy data into trx_covid_cases_bonus using PGAdmin
	
	// 3. Complete the method below to return list of CovidCasesBonus from table trx_covid_cases_bonus
	// Files to be modified as below
	
	// CovidCasesBonus - Java POJO (done)
	// CovidCasesBonusEntity - DB Entity File (done)
	// CovidAreaBonusMapper - Mapper from Java Entity file above to POJO (done)
	// CovidCasesBonusRepository - Spring JPA Repository or library to query DB. i.e. FindAll() method (done)
	// CovidBonusService - Interface for the service below
	// CovidBonusServiceImpl - Implementation of the service between controller and repository
	
	
	@GetMapping(GET_MY_BONUS)
	List<CovidCasesBonus> bonus() throws Exception {
		log.info("bonus() started");
		List<CovidCasesBonus> covidCasesBonus = null;	
		
		try {
			covidCasesBonus = covidBonusService.bonus();
			for (CovidCasesBonus b :covidCasesBonus) {
				log.info("b--->" + b.getDescription());
			}
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
		} catch (Exception e) {
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}
		log.info("bonus() ended");
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_BONUS)
	CovidCasesBonus addBonus(@RequestParam(required = true) String bonus) throws Exception {
		CovidCasesBonus covidCasesBonus = null;
		
		try {
			if (bonus == null || bonus.equals("undefined") || bonus.equals("")) {
				throw new NullPointerException(ADD_BONUS + ", bonus is null or empty");
			}
			covidCasesBonus = covidBonusService.addBonus(bonus);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return covidCasesBonus;
	}
	
	@DeleteMapping(DELETE_BONUS)
	int deleteBonus(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteBonus() started id={}", id);
		int result = 0;
		
		result = covidBonusService.deleteBonus(id);
		
		return result;
	}
	
	@PutMapping(PUT_BONUS)
	CovidCasesBonus putBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("putBonus() started");
		CovidCasesBonus covidCasesBonusSaved = null;
		
		try {
			if (covidCasesBonus == null || covidCasesBonus.equals("undefined") || covidCasesBonus.equals("")) {
				throw new NullPointerException(PUT_BONUS + ", bonus is null or empty");
			}
			covidCasesBonusSaved = covidBonusService.updateBonus(covidCasesBonus);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("putBonus() ended");
		return covidCasesBonusSaved;
	}
	
	@PostMapping(POST_BONUS)
	CovidCasesBonus postBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		CovidCasesBonus covidCasesBonusSaved = null;
		
		try {
			if (covidCasesBonus == null || covidCasesBonus.equals("undefined") || covidCasesBonus.equals("")) {
				throw new NullPointerException(POST_BONUS + ", bonus is null or empty");
			}
			covidCasesBonusSaved = covidBonusService.postBonus(covidCasesBonus);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return covidCasesBonusSaved;
	}
	
	@DeleteMapping(DELETE_SOAPUI_BONUS)
	int deleteSoapBonus(@RequestParam(required = true) String bonus) throws Exception {
		int result = 0;
		
		try {
			if (bonus == null || bonus.equals("undefined") || bonus.equals("")) {
				throw new NullPointerException(DELETE_SOAPUI_BONUS + ", bonus is null or empty");
			}
			result = covidBonusService.deleteSoapBonus(bonus);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}
}
