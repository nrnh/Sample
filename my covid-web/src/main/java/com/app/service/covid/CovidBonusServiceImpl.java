package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;
import com.app.repository.covid.CovidCasesRepository;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidBonusServiceImpl implements CovidBonusService {
	
	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	@Autowired
	CovidCasesRepository covidCasesRepository;
	
	@Override
	public List<CovidCasesBonus> bonus() {
		//List<CovidCasesBonus> CovidCasesBonus = null;
		log.info("bonus() started");
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<CovidCasesBonus>();
		
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		}
		else {
			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("Entity total bonus ={}", entity.getDescription());
			}
			log.info("getCovidBonus() return size={}", covidCaseBonusEntities.size());
		}
		
		log.info("bonus() ends");
		return covidCasesBonusList;
	}

	@Override
	public CovidCasesBonus updateBonus(CovidCasesBonus covidCasesBonus) {
		// TODO Auto-generated method stub
		CovidCasesBonus savedBonus = null;
		CovidCasesBonusEntity covidAreaBonusEntity = new CovidCasesBonusEntity();
		
		covidAreaBonusEntity.setId(covidCasesBonus.getId());
		covidAreaBonusEntity.setDescription(covidCasesBonus.getDescription());
		
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		savedBonus = mapper.asResource(savedEntity);
		
		return savedBonus;
	}

	@Override
	public CovidCasesBonus postBonus(CovidCasesBonus covidCasesBonus) {
		// TODO Auto-generated method stub
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidAreaBonusEntity = mapper.asEntity(covidCasesBonus);
		covidCasesBonusRepository.save(covidAreaBonusEntity);
		
		return covidCasesBonus;
	}

	@Override
	public int deleteSoapBonus(String bonus) {
		// TODO Auto-generated method stub
		int result = 0;
		
		result = covidCasesBonusRepository.deleteSoap(bonus);
		
		return result;
	}

	@Override
	public CovidCasesBonus addBonus(String bonus) {
		// TODO Auto-generated method stub
		CovidCasesBonus covidCasesBonus = null;
		CovidCasesBonusEntity covidAreaBonusEntity = new CovidCasesBonusEntity();
		
		covidAreaBonusEntity.setDescription(bonus);

		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		covidCasesBonus = mapper.asResource(savedEntity);
		
		return covidCasesBonus;
	}

	@Override
	public int deleteBonus(long id) {
		// TODO Auto-generated method stub
		Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);
		if (entityOptional.isPresent()) {
			CovidCasesBonusEntity covidAreaBonusEntity = entityOptional.get();
			covidCasesBonusRepository.delete(covidAreaBonusEntity);
			return 1;
		}
		return 0;
	}
}
