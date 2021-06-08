package com.fpl.operations.microservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.fpl.operations.microservice.entities.FplUser;
import com.fpl.operations.microservice.exceptionHandling.FplUserException;

@Repository
@Transactional
public class FplUserRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public FplUser inserNewUser(String userName, String userPassword, char emailVerificationStatus) {
		FplUser fplUser = new FplUser(userName,userPassword,emailVerificationStatus);
		em.persist(fplUser);
		return fplUser;
	}
	
	public FplUser updateNewPass(FplUser fplUser) {
		System.out.println("fplUser "+fplUser.getId()+"  ko "+fplUser.getUserPassword());
		FplUser old = em.find(FplUser.class, fplUser.getId());
		if(old==null) 
			throw new FplUserException("No User with Id - " + fplUser.getId(), HttpStatus.NOT_FOUND);
		old.setUserPassword(fplUser.getUserPassword());
		return  em.merge(old);
	}
}
