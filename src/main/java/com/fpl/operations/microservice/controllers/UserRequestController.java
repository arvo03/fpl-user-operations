package com.fpl.operations.microservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpl.operations.microservice.configuration.FplUserOperationsApplicationConfiguration;
import com.fpl.operations.microservice.entities.FplUser;
import com.fpl.operations.microservice.entities.FplUserGameweekRecord;
import com.fpl.operations.microservice.entities.Limits;
import com.fpl.operations.microservice.exceptionHandling.FplUserException;
import com.fpl.operations.microservice.services.FplUserGameweekNumberService;
import com.fpl.operations.microservice.services.FplUserService;

@RestController
@RequestMapping(value = "/user-profile")
public class UserRequestController {

	@Autowired
	private FplUserService fplUserService;
	@Autowired
	private FplUserGameweekNumberService fplUserGameweekNumberService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private FplUserOperationsApplicationConfiguration applicationConfig;

	// WELCOME MESSAGE
	@GetMapping("/common/welcome")
	public String welcomeToFpl() {
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
	}

	// GET USER INFO
	@GetMapping("/users/{fplUserJpaId}")
	public MappingJacksonValue getUser(@PathVariable long fplUserJpaId) {
		return fplUserService.getDetailsOfUser(fplUserJpaId);
	}

	// GET ALL USER INFO
	@GetMapping("/admin/all-users")
	public MappingJacksonValue getAllUsers() {
		return fplUserService.getAllUsers();
	}

	// GET GAMEWEEK RECORD FOR A USER
	@GetMapping("/users/gameweek-record/{fplUserJpaId}")
	public CollectionModel<FplUserGameweekRecord> getUserGameweekRecords(@PathVariable long fplUserJpaId)
			throws NoSuchMethodException, SecurityException {

		return fplUserService.getUserGameweekRecords(fplUserJpaId);
	}

	// GET OLD PASSWORD
	@GetMapping("/users/get-current-password/{fplUserJpaId}")
	public MappingJacksonValue getCurrentPassword(@PathVariable long fplUserJpaId) {
		MappingJacksonValue fpluser = fplUserService.getCurrentPassword(fplUserJpaId);
		return fpluser;
	}

	// SAVE A NEW USER
	@PostMapping("/users")
	public ResponseEntity<Object> insertNewUser(@Valid @RequestBody FplUser fplUser) throws FplUserException {
		fplUser.getFplUserInfo().setFplUser(fplUser);
		return fplUserService.insertNewUser(fplUser);
	}

	// PASSWORD CHANGE
	@PostMapping("/users/change-password")
	public ResponseEntity<Object> updatePassword(@Valid @RequestBody FplUser fplUser) {
		return fplUserService.updateNewPassword(fplUser);
	}
	
	//ALL EXPERIMENTS
	
	// GET OLD PASSWORD
	@GetMapping("/admin/distinct")
	public MappingJacksonValue getDistinctPassword() {
		MappingJacksonValue fpluser = fplUserService.getDistinctUserWithPassword("orac1eeuioyyr");
		return fpluser;
	}

	@GetMapping("/admin/limits")
	public Limits retrieveLimits() {
		return new Limits(applicationConfig.getMinimum(), applicationConfig.getMaximum());
	}
	
	@GetMapping("/gameweekIdGetUser")
	public MappingJacksonValue getUserId() {
		return fplUserGameweekNumberService.getUserIdFromGameweekNumber();
	}

}
