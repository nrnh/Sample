package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trx_covid_cases_bonus")
@Getter
@Setter
public class CovidCasesBonusEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
}
