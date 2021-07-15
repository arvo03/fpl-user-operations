package com.fpl.operations.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fpl.operations.microservice.entities.FplUser;
import com.fpl.operations.microservice.repository.FplUserRepoInterface;

@Service
public class FplUserOperationsUserDetailsService implements UserDetailsService{

	@Autowired
	FplUserRepoInterface fplUserRepoInterface;
	
	@Override
	public FplUserOperationsUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FplUser fplUser = fplUserRepoInterface.findByUserName(username);
		return new FplUserOperationsUserDetails(fplUser);
	}

}
