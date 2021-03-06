package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>  {

	@Transactional
	@Modifying
	@Query("DELETE  FROM CovidCasesDescEntity d WHERE d.description = :desc")
	int deleteSoap(String desc);

	// Complete the JPQL below
	@Query(value = "SELECT description FROM trx_covid_cases_desc GROUP BY description HAVING count(*) > 1;", nativeQuery = true)
	List<String> findDuplicate();

}
