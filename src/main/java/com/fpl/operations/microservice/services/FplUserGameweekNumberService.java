package com.fpl.operations.microservice.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fpl.operations.microservice.entities.FplUser;
import com.fpl.operations.microservice.entities.FplUserGameweekNumber;
import com.fpl.operations.microservice.filtering.FilteringInterface;
import com.fpl.operations.microservice.repository.FplUserGameweekNumberRepoInterface;

@Service
public class FplUserGameweekNumberService {
	@Autowired
	FplUserGameweekNumberRepoInterface fplUserGameweekRepo;
	@Autowired
	FilteringInterface filterInterface;
	
	public MappingJacksonValue getUserIdFromGameweekNumber() {
		FplUserGameweekNumber o = fplUserGameweekRepo.getById(1002L);
		FplUser fpluser = o.getFplUser();
		System.out.println(" "+fpluser.getId());
		MappingJacksonValue finalOp = new MappingJacksonValue(fpluser);
		SimpleFilterProvider filters = filterInterface.includeAllBut(new HashSet<>(), FplUser.class, "userPasswordFilter");
		finalOp.setFilters(filters);
		return finalOp;
	}
}
