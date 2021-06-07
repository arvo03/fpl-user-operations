package com.fpl.operations.microservice.services;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resources;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fpl.operations.microservice.controllers.UserRequestController;
import com.fpl.operations.microservice.entities.FplUser;
import com.fpl.operations.microservice.entities.FplUserGameweekRecord;
import com.fpl.operations.microservice.exceptionHandling.FplException;
import com.fpl.operations.microservice.exceptionHandling.FplUserException;
import com.fpl.operations.microservice.filtering.FilteringInterface;
import com.fpl.operations.microservice.repository.FplUserRepo;
import com.fpl.operations.microservice.repository.FplUserRepoInterface;

@Service
public class FplUserService {

	@Autowired
	FplUserRepoInterface fplUserRepo;
	@Autowired
	FplUserRepo fplUserEmRepo;
	@Autowired
	FilteringInterface filterInterface;

	public List<FplUser> getAllUsers() {
		return fplUserRepo.findAll();
	}

	public FplUser insertNewUser(String username, String password, char email) {
		FplUser fplUser = new FplUser(username, password, email);
		fplUser = fplUserRepo.save(fplUser);
		return fplUser;
	}

	public ResponseEntity<Object> insertNewUser(FplUser fplUser) {
		FplUser newlyAddedFplUser = fplUserRepo.save(fplUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{jpaId}")
				.buildAndExpand(newlyAddedFplUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	public MappingJacksonValue getDetailsOfUser(long fplUserJpaId) {
		if (fplUserRepo.findById(fplUserJpaId).isEmpty())
			throw new FplUserException("id nahi mila-" + fplUserJpaId, HttpStatus.NOT_FOUND);

		SimpleFilterProvider filter = filterInterface.includeAllBut(Set.of("userPassword"), FplUser.class,
				"userPasswordFilter");
		MappingJacksonValue fplUser = new MappingJacksonValue(fplUserRepo.findById(fplUserJpaId).get());
		fplUser.setFilters(filter);
		return fplUser;
	}

	public CollectionModel<FplUserGameweekRecord> getUserGameweekRecords(long fplUserJpaId) throws NoSuchMethodException, SecurityException {
		Optional<FplUser> fplUser = fplUserRepo.findById(fplUserJpaId);
		if (fplUser.isEmpty()) 
			throw new FplUserException("No User with Id - " + fplUserJpaId, HttpStatus.NOT_FOUND);
		
		List<FplUserGameweekRecord> recList = fplUser.get().getFplUserGameweekRecord();
		if (recList == null || recList.isEmpty()) {
			throw new FplUserException("No Gameweek data present for current user " + fplUserJpaId,
					HttpStatus.NOT_FOUND);
		}

//		Link linkBuilder = linkTo(UserRequestController.class).slash("user").slash(fplUserJpaId).withSelfRel();
		
		Link linkBuilder = linkTo(methodOn(UserRequestController.class).getUser(fplUserJpaId)).withRel("getUser-api");
							CollectionModel<FplUserGameweekRecord> model = CollectionModel.of(recList);
		model.add(linkBuilder);
		
		return model;
	}

	public ResponseEntity<Object> updateNewPassword(FplUser fplUser) {
		fplUserEmRepo.updateNewPass(fplUser);
		Map<String,String> uriVariables = Map.of("jpaId", String.valueOf(fplUser.getId()));
		URI link = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{jpaId}").build(uriVariables );
		
		return ResponseEntity.created(link).build();
	}

	public MappingJacksonValue getOldPassword(long fplUserJpaId) {
		if (fplUserRepo.findById(fplUserJpaId).isEmpty())
			throw new FplUserException("No User with Id - " + fplUserJpaId, HttpStatus.NOT_FOUND);
		MappingJacksonValue fplUser = new MappingJacksonValue(fplUserRepo.findById(fplUserJpaId).get());
		SimpleFilterProvider filters = filterInterface.excludeAllBut(Set.of("userName", "userPassword", "id"),
				FplUser.class, "userPasswordFilter");
		fplUser.setFilters(filters);
		return fplUser;
	}

}
